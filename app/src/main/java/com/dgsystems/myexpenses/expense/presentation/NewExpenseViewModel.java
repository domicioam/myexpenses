package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModel;

public class NewExpenseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public NewExpenseViewModel() {
        message = "Hello from view model.";
    }

    private String message;
    public String getMessage() {
        return message;
    }
}