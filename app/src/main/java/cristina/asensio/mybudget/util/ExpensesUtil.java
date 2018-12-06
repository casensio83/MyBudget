package cristina.asensio.mybudget.util;

import android.arch.lifecycle.LifecycleOwner;
import android.widget.TextView;

import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;

public class ExpensesUtil {

    private static double maxAmount;

    public static void updateTotalAvailable(LifecycleOwner activity, ExpenseViewModel mExpenseViewModel, TextView totalBudgetTextView, double maxAmountAvailable) {
        maxAmount = maxAmountAvailable;
        mExpenseViewModel.getAllExpenses().observe(activity, expenses -> {
            for (Expense expense : expenses) {
                maxAmount -= Double.parseDouble(expense.getAmount());
            }
            if (totalBudgetTextView != null) {
                totalBudgetTextView.setText(String.valueOf(maxAmount));
            }
        });

        totalBudgetTextView.setText(String.valueOf(maxAmount));
    }

}
