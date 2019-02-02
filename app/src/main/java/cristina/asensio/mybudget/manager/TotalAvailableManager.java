package cristina.asensio.mybudget.manager;

import java.util.List;

import cristina.asensio.mybudget.database.Expense;

public class TotalAvailableManager {

    public static String getUpdatedTotalAvailable(List<Expense> expenses, double currentTotalAvailable, int position) {
        final float amountToAddInTotalAvailable = expenses.get(position).getAmount();
        return String.valueOf(currentTotalAvailable + amountToAddInTotalAvailable);
    }
}
