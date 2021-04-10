package com.dgsystems.myexpenses.expense.application;

import com.dgsystems.myexpenses.expense.core.Category;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateExpenseCommand {
    private final UUID expenseId;
    private final BigDecimal newValue;
    private final String newDescription;
    private final Category newCategory;

    public UpdateExpenseCommand(UUID expenseId, BigDecimal newValue, String newDescription, Category newCategory) {
        this.expenseId = expenseId;
        this.newValue = newValue;
        this.newDescription = newDescription;
        this.newCategory = newCategory;
    }

    public UUID getExpenseId() {
        return expenseId;
    }

    public BigDecimal getNewValue() {
        return newValue;
    }

    public String getNewDescription() {
        return newDescription;
    }

    public Category getNewCategory() {
        return newCategory;
    }
}
