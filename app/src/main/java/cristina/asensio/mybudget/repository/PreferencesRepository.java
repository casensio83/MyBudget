package cristina.asensio.mybudget.repository;

import android.content.SharedPreferences;

public class PreferencesRepository {

    private static final String PREFERENCES_MAX_AMOUNT = "maxAmount";
    private static final String PREFERENCES_TOTAL_AVAILABLE = "totalAvailable";
    private static final String PREFERENCES_DEFAULT_MAX_AMOUNT = "500";

    private SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    public PreferencesRepository(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
        editor = mSharedPreferences.edit();
    }

    public String getMaxAmount() {
        return mSharedPreferences.getString(PREFERENCES_MAX_AMOUNT, PREFERENCES_DEFAULT_MAX_AMOUNT);
    }

    public void setMaxAmount(String maxAmount) {
        editor.putString(PREFERENCES_MAX_AMOUNT, maxAmount);
        editor.apply();
    }

    public void setTotalAvailableDisplayed(String totalAvailableDisplayed) {
        editor.putString(PREFERENCES_TOTAL_AVAILABLE, totalAvailableDisplayed);
    }

    public String getTotalAvailableDisplayed() {
        return mSharedPreferences.getString(PREFERENCES_TOTAL_AVAILABLE, PREFERENCES_DEFAULT_MAX_AMOUNT);
    }
}
