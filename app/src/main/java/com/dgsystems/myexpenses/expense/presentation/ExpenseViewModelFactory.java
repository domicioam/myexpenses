package com.dgsystems.myexpenses.expense.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;

public class ExpenseViewModelFactory implements ViewModelProvider.Factory{
    private final ExpenseApplicationCommandService commandService;

    public ExpenseViewModelFactory(ExpenseApplicationCommandService commandService) {
        this.commandService = commandService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExpenseViewModel(commandService);
    }
}
