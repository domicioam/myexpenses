package com.dgsystems.myexpenses;

import com.dgsystems.myexpenses.expense.application.RemoveExpenseCommand;
import com.dgsystems.myexpenses.expense.core.Category;
import com.dgsystems.myexpenses.expense.core.Expense;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationService;
import com.dgsystems.myexpenses.expense.application.ExpenseDto;
import com.dgsystems.myexpenses.expense.core.ExpenseRepository;
import com.dgsystems.myexpenses.expense.application.NewExpenseCommand;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import io.reactivex.rxjava3.observers.TestObserver;

@ExtendWith(MockitoExtension.class)
class ExpensesAppServiceTests {
	@Mock
	ExpenseRepository expenseRepository;
	@InjectMocks
	ExpenseApplicationService expenseApplicationService;

	@Test
	void should_create_expense_when_input_valid() throws UnsupportedOperationException {
		NewExpenseCommand command = new NewExpenseCommand(new BigDecimal(10), "Candy", Category.Wants);
		TestObserver<UUID> test = this.expenseApplicationService.newExpense(command).test();

		UUID newExpenseId = test.values().get(0);

		verify(this.expenseRepository, times(1)).save(Mockito.any(Expense.class));
		assertNotNull(newExpenseId);
		test.assertNoErrors();
	}

	@Test
	void should_return_all_expenses() {
		UUID expenseId = UUID.randomUUID();
		BigDecimal value = new BigDecimal(10);
		String description = "Candy";

		List<Expense> expenses = Arrays.asList(new Expense(expenseId, value, description, Category.Wants));
		Mockito.when(expenseRepository.getExpenses()).thenReturn(expenses);

		TestObserver<List<ExpenseDto>> test = expenseApplicationService.getExpenses().test();
		ExpenseDto expenseDto = test.values().get(0).get(0);
		verify(expenseRepository, times(1)).getExpenses();
		assertEquals(new ExpenseDto("Wants", description, expenseId, value), expenseDto);
		test.assertNoErrors();
		test.assertComplete();
	}

	@Test
	void should_remove_expense() {
		UUID expenseId = UUID.randomUUID();
		BigDecimal value = new BigDecimal(10);
		String description = "Candy";
		Category category = Category.Wants;

		when(expenseRepository.expenseOfId(any(UUID.class)))
				.thenReturn(Optional.of(new Expense(expenseId, value, description, category)));

		RemoveExpenseCommand command = new RemoveExpenseCommand(expenseId);

		TestObserver test = expenseApplicationService.removeExpense(command).test();

		test.assertNoErrors();
		test.assertComplete();
		verify(expenseRepository, times(1)).removeExpense(expenseId);
	}
}
