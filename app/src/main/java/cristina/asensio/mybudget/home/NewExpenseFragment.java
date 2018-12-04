package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @BindView(R.id.new_expense_title_edittext)
    EditText newExpenseTitleEdittext;

    @BindView(R.id.new_expense_description_edittext)
    EditText newExpenseDescriptionEdittext;

    @BindView(R.id.new_expense_amount_edittext)
    EditText newExpenseAmountEdittext;

    @BindView(R.id.save_expense)
    Button saveNewExpenseButton;

    private Unbinder unbinder;

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
        });
    }

    private void goBackToExpensesList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_container, new ExpensesListFragment())
                .addToBackStack(null)
                .commit();
    }

    private void saveNewExpense() {
        Expense expense = new Expense(
                new Date().toString(),
                newExpenseAmountEdittext.getText().toString(),
                newExpenseTitleEdittext.getText().toString(),
                newExpenseDescriptionEdittext.getText().toString());
        ExpenseViewModel mExpenseViewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);
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
