package com.dgsystems.myexpenses.application.expense;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExpenseApplicationService {
	private ExpenseRepository expenseRepository;

	public ExpenseApplicationService(ExpenseRepository expenseRepository) {
		this.expenseRepository = expenseRepository;
	}

	public Single<UUID> newExpense(NewExpenseCommand command) throws UnsupportedOperationException {
		return Single.create(source -> {
			UUID expenseId = UUID.randomUUID();

			Expense expense = 
					new Expense(
							expenseId, 
							command.getValue(), 
							command.getDescription(),
							command.getCategory());

			expenseRepository.save(expense);
			source.onSuccess(expenseId);
		});
	}

	public Single<List<ExpenseDto>> getExpenses() {
		return Single.create((source -> {
			List<ExpenseDto> expenseDtos = expenseRepository.getExpenses()
					.stream()
					.map(e -> new ExpenseDto(e.category().toString(), e.description(), e.id(), e.value()))
					.collect(Collectors.toList());

			source.onSuccess(expenseDtos);
		}));
	}
}
