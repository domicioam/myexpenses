package com.dgsystems.myexpenses.expense.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationQueryService;

public class ExpenseViewModelFactory implements ViewModelProvider.Factory{
    private final ExpenseApplicationCommandService commandService;
    private final ExpenseApplicationQueryService queryService;

    public ExpenseViewModelFactory(ExpenseApplicationCommandService commandService, ExpenseApplicationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ExpenseViewModel(commandService, queryService);
    }
}
