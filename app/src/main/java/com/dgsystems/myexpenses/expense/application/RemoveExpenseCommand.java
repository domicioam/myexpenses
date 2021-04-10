package com.dgsystems.myexpenses.expense.application;

import java.util.Objects;
import java.util.UUID;

public class RemoveExpenseCommand {
    public UUID getExpenseId() {
        return expenseId;
    }

    private final UUID expenseId;

    public RemoveExpenseCommand(UUID expenseId) {
        this.expenseId = expenseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RemoveExpenseCommand that = (RemoveExpenseCommand) o;
        return expenseId.equals(that.expenseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expenseId);
    }
}
