package com.dgsystems.myexpenses.expense.presentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.dgsystems.myexpenses.R;
import com.dgsystems.myexpenses.expense.application.ExpenseDto;

import org.w3c.dom.Text;

public class ExpenseDtoAdapter extends ListAdapter<ExpenseDto, ExpenseDtoAdapter.ExpenseDtoHolder> {
    private OnItemClickListener listener;

    public ExpenseDtoAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<ExpenseDto> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ExpenseDto>() {

                @Override
                public boolean areItemsTheSame(@NonNull ExpenseDto oldItem, @NonNull ExpenseDto newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull ExpenseDto oldItem, @NonNull ExpenseDto newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ExpenseDtoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item, parent, false);
        return new ExpenseDtoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseDtoHolder holder, int position) {
        ExpenseDto currentExpense = getItem(position);
        holder.tvCategory.setText(currentExpense.getCategory());
        holder.tvValue.setText(currentExpense.getValue().toString());
        holder.tvDescription.setText(currentExpense.getDescription());
        holder.tvDate.setText(currentExpense.getDate().toString());
    }

    class ExpenseDtoHolder extends RecyclerView.ViewHolder {
        private TextView tvDescription;
        private TextView tvCategory;
        private TextView tvValue;
        private TextView tvDate;

        public ExpenseDtoHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvCategory = itemView.findViewById(R.id.tv_category);
            tvValue = itemView.findViewById(R.id.tv_value);
            tvDate = itemView.findViewById(R.id.tv_date);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();

                if(listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(ExpenseDto expenseDto);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { this.listener = listener; }
}
