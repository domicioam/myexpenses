package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dgsystems.myexpenses.R;

public class NewExpenseFragment extends Fragment {

    private NewExpenseViewModel mViewModel;

    public static NewExpenseFragment newInstance() {
        return new NewExpenseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.new_expense_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NewExpenseViewModel.class);
        // TODO: Use the ViewModel

        TextView messageTV = getView().findViewById(R.id.message);
        messageTV.setText(mViewModel.getMessage());
    }
}