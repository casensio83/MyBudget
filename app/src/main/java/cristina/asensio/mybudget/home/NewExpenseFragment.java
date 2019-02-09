package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cristina.asensio.mybudget.R;
import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;

public class NewExpenseFragment extends Fragment {

    private final String TOTAL_AVAILABLE_KEY = "total_available";
    private final String PREFERENCES_KEY = "my_budget_preferences";
    private final int PRIVATE_MODE = 0;
    private final int DEFAULT_VALUE = 0;

    @BindView(R.id.new_expense_title_edittext)
    EditText newExpenseTitleEdittext;

    @BindView(R.id.new_expense_description_edittext)
    EditText newExpenseDescriptionEdittext;

    @BindView(R.id.new_expense_amount_edittext)
    EditText newExpenseAmountEdittext;

    @BindView(R.id.save_expense)
    Button saveNewExpenseButton;

    private Unbinder unbinder;
    private ExpenseViewModel mExpenseViewModel;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_expense, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        saveNewExpenseButton.setOnClickListener(view1 -> {
            saveNewExpense();
            goBackToExpensesList();
            hideSoftInput();
        });
    }

    private void hideSoftInput() {
        final InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.RESULT_UNCHANGED_SHOWN);
    }

    private void goBackToExpensesList() {
        saveNewMaxAmountAvailableToSpend();

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_container, new ExpensesListFragment())
                .addToBackStack(null)
                .commit();
    }

    private void saveNewMaxAmountAvailableToSpend() {
        mSharedPreferences = getActivity().getSharedPreferences(PREFERENCES_KEY, PRIVATE_MODE);
        float maxAmountToSpend = mSharedPreferences.getFloat(TOTAL_AVAILABLE_KEY, DEFAULT_VALUE);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        float newAmount = maxAmountToSpend - Float.parseFloat(newExpenseAmountEdittext.getText().toString());
        editor.putFloat(TOTAL_AVAILABLE_KEY,  newAmount);
        editor.commit();
    }

    private void saveNewExpense() {
        final Expense expense = new Expense(
                new Date().toString(),
                Float.parseFloat(newExpenseAmountEdittext.getText().toString()),
                newExpenseTitleEdittext.getText().toString(),
                newExpenseDescriptionEdittext.getText().toString());
        mExpenseViewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);
        mExpenseViewModel.insert(expense);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}