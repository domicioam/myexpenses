package com.dgsystems.myexpenses.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.dgsystems.myexpenses.R;

public class NewExpenseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_expense_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, NewExpenseFragment.newInstance())
                    .commitNow();
        }
    }
}