package com.dgsystems.myexpenses.application.expense;

import java.util.UUID;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExpenseApplicationService {
	private ExpenseRepository expenseRepository;

	public Single<UUID> newExpense(NewExpenseCommand command) throws UnsupportedOperationException {
		return Single.create(source -> {
			UUID expenseId = UUID.randomUUID();

			Expense expense = 
					new Expense(
							expenseId, 
							command.getValue(), 
							command.getDescription(),
							command.getCategory());

			this.expenseRepository.save(expense);
			source.onSuccess(expenseId);
		});
	}
}
