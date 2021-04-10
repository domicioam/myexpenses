package com.dgsystems.myexpenses.application.expense;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpenseRepository { // this class should go away once we start using Spring Data
	private List<Expense>  expenses;
	
	public ExpenseRepository() {
		expenses = new ArrayList<>();
	}

	public void save(Expense any) { // this method comes with Spring Data
		expenses.add(any);
	}

	public Expense expenseOfId(UUID newExpenseId) {
		return expenses.stream()
				.filter(e -> e.id().equals(newExpenseId))
				.findAny()
				.get();
	}

}
