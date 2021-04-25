package com.dgsystems.myexpenses.expense.application;

public abstract class Command {
    private boolean is_executed;

    public Command() {
        is_executed = false;
    }

    public boolean isExecuted() {
        return is_executed;
    }

    public void setIsExecuted(boolean is_executed) {
        this.is_executed = is_executed;
    }

    public abstract void execute();
}
