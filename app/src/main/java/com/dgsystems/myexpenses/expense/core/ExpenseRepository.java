package com.dgsystems.myexpenses.expense.core;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {
    void save(Expense any);
    Optional<Expense> expenseOfId(UUID newExpenseId);
    List<Expense> getExpenses();
    void removeExpense(UUID expenseId);
}
