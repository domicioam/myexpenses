package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModel;

import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationQueryService;
import com.dgsystems.myexpenses.expense.application.ExpenseDto;
import com.dgsystems.myexpenses.expense.application.NewExpenseCommand;
import com.dgsystems.myexpenses.expense.core.Category;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class ExpenseViewModel extends ViewModel {

    public ExpenseViewModel(ExpenseApplicationCommandService commandService, ExpenseApplicationQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    private final ExpenseApplicationQueryService queryService;
    private final ExpenseApplicationCommandService commandService;

    public void newExpense(String description, String date, String category, Double value) {
        Category categoryEnum = Category.valueOf(category);
        NewExpenseCommand command = new NewExpenseCommand(new BigDecimal(value), description, categoryEnum, new Date(), commandService);
        command.execute();

        if(!command.isExecuted())
            return;
    }

    public Single<List<ExpenseDto>> getAllExpenses() {
        return queryService.getExpenses();
    }
}