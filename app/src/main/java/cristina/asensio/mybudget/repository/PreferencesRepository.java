package cristina.asensio.mybudget.repository;

import android.content.SharedPreferences;

public class PreferencesRepository {

    private static final String PREFERENCES_MAX_AMOUNT = "maxAmount";
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
}
