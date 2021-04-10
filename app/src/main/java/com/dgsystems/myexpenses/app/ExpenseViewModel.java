package com.dgsystems.myexpenses.app;

import androidx.lifecycle.ViewModel;

public class ExpenseViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public ExpenseViewModel() {
        message = "Hello from view model.";
    }

    private String message;
    public String getMessage() {
        return message;
    }
}