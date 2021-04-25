package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModel;

import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationService;
import com.dgsystems.myexpenses.expense.application.NewExpenseCommand;
import com.dgsystems.myexpenses.expense.core.Category;
import com.dgsystems.myexpenses.expense.repository.ExpenseRepositoryImpl;

import java.math.BigDecimal;

public class ExpenseViewModel extends ViewModel {
    public ExpenseViewModel(ExpenseApplicationCommandService commandService) {
        this.commandService = commandService;
    }

    private final ExpenseApplicationCommandService commandService;

    public void newExpense(String description, String date, String category, Double value) {
        Category categoryEnum = Category.valueOf(category);
        NewExpenseCommand command = new NewExpenseCommand(new BigDecimal(value), description, categoryEnum, commandService);
        command.execute();

        if(!command.isExecuted())
            return;
    }
}