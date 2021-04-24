package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModel;

import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationService;
import com.dgsystems.myexpenses.expense.application.NewExpenseCommand;
import com.dgsystems.myexpenses.expense.core.Category;
import com.dgsystems.myexpenses.expense.repository.ExpenseRepositoryImpl;

import java.math.BigDecimal;

public class ExpenseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public ExpenseViewModel() {
        message = "Hello from view model.";
    }

    private String message;
    public String getMessage() {
        return message;
    }

    public void newExpense(String description, String date, String category, Double value) {
        ExpenseApplicationCommandService service = new ExpenseApplicationService(new ExpenseRepositoryImpl());

        Category categoryEnum = Category.valueOf(category);

        NewExpenseCommand command = new NewExpenseCommand(new BigDecimal(value), description, categoryEnum);
        service.newExpense(command); // use Command Pattern and move the app service and this call to the command
    }
}