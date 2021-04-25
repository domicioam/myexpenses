package com.dgsystems.myexpenses.app;

import android.content.Intent;
import android.os.Bundle;

import com.dgsystems.myexpenses.R;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationCommandService;
import com.dgsystems.myexpenses.expense.application.ExpenseApplicationService;
import com.dgsystems.myexpenses.expense.presentation.ExpenseViewModel;
import com.dgsystems.myexpenses.expense.presentation.ExpenseViewModelFactory;
import com.dgsystems.myexpenses.expense.presentation.NewExpenseActivity;
import com.dgsystems.myexpenses.expense.presentation.NewExpenseFragment;
import com.dgsystems.myexpenses.expense.repository.ExpenseRepositoryImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    public static final int ADD_EXPENSE_REQUEST = 1;
    public static final int EDIT_EXPENSE_REQUEST = 2;

    private ExpenseViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ExpenseApplicationCommandService service = new ExpenseApplicationService(new ExpenseRepositoryImpl());

        viewModel = new ViewModelProvider(this, new ExpenseViewModelFactory(service)).get(ExpenseViewModel.class);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewExpenseActivity.class);
                startActivityForResult(intent, ADD_EXPENSE_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_EXPENSE_REQUEST && resultCode == RESULT_OK) {
            String description = data.getStringExtra(NewExpenseFragment.EXTRA_DESCRIPTION);
            String date = data.getStringExtra(NewExpenseFragment.EXTRA_DATE);
            String category = data.getStringExtra(NewExpenseFragment.EXTRA_CATEGORY);
            Double value = data.getDoubleExtra(NewExpenseFragment.EXTRA_VALUE, 0);

            viewModel.newExpense(description, date, category, value);

        }
    }
}