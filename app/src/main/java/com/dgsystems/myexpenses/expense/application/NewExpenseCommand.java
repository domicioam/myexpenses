package com.dgsystems.myexpenses.expense.application;

import com.dgsystems.myexpenses.expense.core.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class NewExpenseCommand extends Command {
	public NewExpenseCommand(BigDecimal value, String description, Category category, Date date, ExpenseApplicationCommandService commandService) {
		super();
		this.value = value;
		this.description = description;
		this.category = category;
		this.date = date;
		this.commandService = commandService;
	}

	private BigDecimal value;
	private String description;
	private Category category;
	private final Date date;
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
	public Date getDate() {return date;}

	@Override
	public void execute() {
		commandService.newExpense(this);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		NewExpenseCommand that = (NewExpenseCommand) o;
		return getValue().equals(that.getValue()) &&
				getDescription().equals(that.getDescription()) &&
				getCategory() == that.getCategory() &&
				date.equals(that.date) &&
				commandService.equals(that.commandService);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getValue(), getDescription(), getCategory(), date, commandService);
	}
}
