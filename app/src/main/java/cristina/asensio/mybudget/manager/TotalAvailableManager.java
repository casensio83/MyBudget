package cristina.asensio.mybudget.manager;

import android.content.SharedPreferences;

import java.util.Calendar;
import java.util.List;

import cristina.asensio.mybudget.database.Expense;

public class TotalAvailableManager {

    private static final String MAX_AMOUNT_TO_SPEND_A_MONTH_KEY = "max_amount_to_spend";
    private static final String TOTAL_AVAILABLE_KEY = "total_available";
    private static final String TWO_DECIMALS_FORMAT = "%.2f";
    private static final int MAX_AMOUNT_TO_SPEND_DEFAULT_VALUE = 600;
    private static final int DEFAULT_VALUE = 0;
    private static final int FIRST_DAY_OF_MONTH = 1;

    private TotalAvailableManager() {
        
    }

    public static String getUpdatedTotalAvailable(List<Expense> expenses, double currentTotalAvailable, int position) {
        final float amountToAddInTotalAvailable = expenses.get(position).getAmount();
        return String.format(TWO_DECIMALS_FORMAT, currentTotalAvailable + amountToAddInTotalAvailable);
    }

    public static float calculateTotalAvailable(SharedPreferences mSharedPreferences) {
        float total;
        float maxAmountToSpend = mSharedPreferences.getFloat(MAX_AMOUNT_TO_SPEND_A_MONTH_KEY, MAX_AMOUNT_TO_SPEND_DEFAULT_VALUE);
        float totalAvailable = mSharedPreferences.getFloat(TOTAL_AVAILABLE_KEY, DEFAULT_VALUE);
        final Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        if (day == FIRST_DAY_OF_MONTH) {
            total = maxAmountToSpend;
            editor.putFloat(TOTAL_AVAILABLE_KEY, maxAmountToSpend);
            editor.commit();
        } else if (totalAvailable == DEFAULT_VALUE) {
            editor.putFloat(TOTAL_AVAILABLE_KEY, MAX_AMOUNT_TO_SPEND_DEFAULT_VALUE);
            editor.commit();
            total = MAX_AMOUNT_TO_SPEND_DEFAULT_VALUE;
        } else {
            total = totalAvailable;
        }

        return total;
    }
}
