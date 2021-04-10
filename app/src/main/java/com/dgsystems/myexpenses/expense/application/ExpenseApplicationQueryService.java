package com.dgsystems.myexpenses.expense.application;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ExpenseApplicationQueryService {
    Single<List<ExpenseDto>> getExpenses();
}
