package com.dgsystems.myexpenses.expense.core;

import java.util.List;
import java.util.UUID;

public interface ExpenseRepository {
    void save(Expense any);
    Expense expenseOfId(UUID newExpenseId);
    List<Expense> getExpenses();
}
