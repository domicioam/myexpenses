package com.dgsystems.myexpenses.expense.application;

import com.dgsystems.myexpenses.expense.core.Category;

import java.math.BigDecimal;

public class NewExpenseCommand extends Command {
	public NewExpenseCommand(BigDecimal value, String description, Category category, ExpenseApplicationCommandService commandService) {
		super();
		this.value = value;
		this.description = description;
		this.category = category;
		this.commandService = commandService;
	}

	private BigDecimal value;
	private String description;
	private Category category;
	private ExpenseApplicationCommandService commandService;
	
	public BigDecimal getValue() {
		return value;
	}
	public String getDescription() {
		return description;
	}
	public Category getCategory() {
		return category;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NewExpenseCommand other = (NewExpenseCommand) obj;
		if (category != other.category)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public void execute() {
		commandService.newExpense(this);
	}
}
