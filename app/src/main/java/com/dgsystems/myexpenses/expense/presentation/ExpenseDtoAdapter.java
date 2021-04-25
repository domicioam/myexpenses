package com.dgsystems.myexpenses.expense.presentation;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dgsystems.myexpenses.expense.application.ExpenseDto;

public class ExpenseDtoAdapter extends ListAdapter<ExpenseDto, ExpenseDtoAdapter.ExpenseDtoHolder> {

    protected ExpenseDtoAdapter(@NonNull DiffUtil.ItemCallback<ExpenseDto> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ExpenseDtoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseDtoHolder holder, int position) {

    }

    class ExpenseDtoHolder extends RecyclerView.ViewHolder {

        public ExpenseDtoHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
