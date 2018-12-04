package cristina.asensio.mybudget.util;

import java.util.List;

import cristina.asensio.mybudget.database.Expense;

public class ExpensesUtil {

    // TODO check why only counts expenses.size - 1
    public static String getMaxAvailable(String maxAmountInPreferences, List<Expense> expenses) {
        double maxAmountPrefs = Double.parseDouble(maxAmountInPreferences);

        if(expenses != null) {
            for(Expense expense : expenses) {
                maxAmountPrefs -= Double.parseDouble(expense.getAmount());
            }
        }

        return String.valueOf(maxAmountPrefs);
    }

}
