package com.dgsystems.myexpenses;

import com.dgsystems.myexpenses.application.expense.Category;
import com.dgsystems.myexpenses.application.expense.Expense;
import com.dgsystems.myexpenses.application.expense.ExpenseApplicationService;
import com.dgsystems.myexpenses.application.expense.ExpenseRepository;
import com.dgsystems.myexpenses.application.expense.NewExpenseCommand;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.reactivex.rxjava3.observers.TestObserver;

@ExtendWith(MockitoExtension.class)
class ExpensesAppServiceTests {
	public ExpensesAppServiceTests() {
		expenseRepository = new ExpenseRepository();
	}
	
	ExpenseRepository expenseRepository;
	@InjectMocks
	ExpenseApplicationService expenseApplicationService;

	@Test
	void should_create_expense_when_input_valid() throws UnsupportedOperationException {
		NewExpenseCommand command = new NewExpenseCommand(new BigDecimal(10), "Candy", Category.Wants);
		TestObserver<UUID> test = this.expenseApplicationService.newExpense(command).test();

		UUID newExpenseId = test.values().get(0);

		Expense newExpense = this.expenseRepository.expenseOfId(newExpenseId);

		verify(this.expenseRepository, times(1)).save(Mockito.any(Expense.class));
		assertNotNull(newExpense);
		assertEquals("Candy", newExpense.description());
		assertEquals(new BigDecimal(10), newExpense.value());
		assertEquals(Category.Wants, newExpense.category());
		test.assertNoErrors();
	}
}
