package com.dgsystems.myexpenses.expense.application;

import java.util.UUID;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface ExpenseApplicationCommandService {
    Single<UUID> newExpense(NewExpenseCommand command);
    Completable removeExpense(RemoveExpenseCommand command);
    Completable updateExpense(UpdateExpenseCommand command);
}
