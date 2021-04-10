package com.dgsystems.myexpenses.expense.repository;

import com.dgsystems.myexpenses.expense.core.Expense;
import com.dgsystems.myexpenses.expense.core.ExpenseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ExpenseRepositoryImpl implements ExpenseRepository { // this class should go away once we start using Spring Data
	private List<Expense>  expenses;
	
	public ExpenseRepositoryImpl() {
		expenses = new ArrayList<>();
	}

	public void save(Expense any) { // this method comes with Spring Data
		Optional<Expense> optionalExpense =  expenses.stream()
				.filter(e -> e.getId() == any.getId())
				.findFirst();

		if(optionalExpense.isPresent()) {
			expenses.remove(optionalExpense.get());
			expenses.add(any);
		} else {
			expenses.add(any);
		}
	}

	public Optional<Expense> expenseOfId(UUID newExpenseId) {
		return expenses.stream()
				.filter(e -> e.getId().equals(newExpenseId))
				.findFirst();
	}

	public List<Expense> getExpenses() {
		return expenses;
	}

	public void removeExpense(UUID expenseId) {
		expenses.remove(expenseId);
	}
}
