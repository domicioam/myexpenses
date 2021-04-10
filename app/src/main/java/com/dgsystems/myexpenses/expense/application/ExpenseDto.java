package com.dgsystems.myexpenses.expense.application;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ExpenseDto {
    private final String category;
    private final String description;
    private final UUID id;
    private final BigDecimal value;

    public ExpenseDto(String category, String description, UUID id, BigDecimal value) {
        this.category = category;
        this.description = description;
        this.id = id;
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public UUID getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExpenseDto that = (ExpenseDto) o;
        return category.equals(that.category) &&
                description.equals(that.description) &&
                id.equals(that.id) &&
                value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, description, id, value);
    }
}
