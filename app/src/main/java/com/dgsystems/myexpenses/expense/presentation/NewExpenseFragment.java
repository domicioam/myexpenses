package com.dgsystems.myexpenses.expense.presentation;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dgsystems.myexpenses.R;

import static android.app.Activity.RESULT_OK;

public class NewExpenseFragment extends Fragment {

    public static final String EXTRA_DESCRIPTION = "com.dgsystems.myexpenses.EXTRA_DESCRIPTION";
    public static final String EXTRA_VALUE = "com.dgsystems.myexpenses.EXTRA_VALUE";
    public static final String EXTRA_CATEGORY = "com.dgsystems.myexpenses.EXTRA_CATEGORY";
    public static final String EXTRA_DATE = "com.dgsystems.myexpenses.EXTRA_DATE";
    public static final String EXTRA_ID = "com.dgsystems.myexpenses.EXTRA_ID";

    private ExpenseViewModel mViewModel;

    EditText editTextDescription;
    EditText editTextValue;
    EditText editTextCategory;
    EditText editTextDate;

    public static NewExpenseFragment newInstance() {
        return new NewExpenseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.new_expense_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editTextDescription = getView().findViewById(R.id.description);
        editTextValue = getView().findViewById(R.id.value);
        editTextCategory = getView().findViewById(R.id.category);
        editTextDate = getView().findViewById(R.id.date);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.add_expense_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_expense:
                saveExpense();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveExpense() {
        String description = editTextDescription.getText().toString();
        Double value = Double.parseDouble(editTextValue.getText().toString());
        String category = editTextCategory.getText().toString();
        String date = editTextDate.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_VALUE, value);
        data.putExtra(EXTRA_CATEGORY, category);
        data.putExtra(EXTRA_DATE, date);

        getActivity().setResult(RESULT_OK, data);
        getActivity().finish();
    }
}