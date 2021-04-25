package com.dgsystems.myexpenses.expense.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Expense {
	private final UUID id;
	private String description;
	private BigDecimal value;
	private Category category;
	private final Date date;

	public Expense(UUID expenseId, BigDecimal value, String description, Category category, Date date) {
		id = expenseId;
		this.value = value;
		this.description = description;
		this.category = category;
		this.date = date;
	}

	public UUID getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Expense expense = (Expense) o;
		return getId().equals(expense.getId()) &&
				getDescription().equals(expense.getDescription()) &&
				getValue().equals(expense.getValue()) &&
				getCategory() == expense.getCategory() &&
				getDate().equals(expense.getDate());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getDescription(), getValue(), getCategory(), getDate());
	}
}
