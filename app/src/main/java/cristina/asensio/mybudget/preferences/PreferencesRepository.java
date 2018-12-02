package cristina.asensio.mybudget.preferences;

import android.content.SharedPreferences;

public class PreferencesRepository {

    private static final String PREFERENCES_MAX_AMOUNT = "maxAmount";
    private static final String PREFERENCES_DEFAULT_MAX_AMOUNT = "200";

    private SharedPreferences sharedPreferences;

    public PreferencesRepository(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    String getMaxAmount() {
        return sharedPreferences.getString(PREFERENCES_MAX_AMOUNT, PREFERENCES_DEFAULT_MAX_AMOUNT);
    }

    void setMaxAmount(String maxAmount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PREFERENCES_MAX_AMOUNT, maxAmount);
        editor.apply();
    }
}
