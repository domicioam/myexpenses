package com.dgsystems.myexpenses.expense.application;

import com.dgsystems.myexpenses.expense.core.Expense;
import com.dgsystems.myexpenses.expense.core.ExpenseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import io.reactivex.rxjava3.core.Completable;
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
					.map(e ->
							new ExpenseDto(
									e.getCategory().toString(),
									e.getDescription(),
									e.getId(),
									e.getValue()))
					.collect(Collectors.toList());

			source.onSuccess(expenseDtos);
		}));
	}

	public Completable removeExpense(RemoveExpenseCommand command) {
		return Completable.create(source -> {
			Optional<Expense> optionalExpense = expenseRepository.expenseOfId(command.getExpenseId());

			if(optionalExpense.isPresent())
			{
				expenseRepository.removeExpense(command.getExpenseId());
				source.onComplete();
			}
			else {
				source.onError(new EntityNotFoundException());
			}
		});
	}

	public Completable updateExpense(UpdateExpenseCommand command) {
		return Completable.create(source -> {
			Optional<Expense> optionalExpense = expenseRepository.expenseOfId(command.getExpenseId());

			if(optionalExpense.isPresent()) {
				Expense expense = optionalExpense.get();
				expense.setCategory(command.getNewCategory());
				expense.setDescription(command.getNewDescription());
				expense.setValue(command.getNewValue());

				expenseRepository.save(expense);
				source.onComplete();
			} else {
				source.onError(new EntityNotFoundException());
			}
		});
	}
}
