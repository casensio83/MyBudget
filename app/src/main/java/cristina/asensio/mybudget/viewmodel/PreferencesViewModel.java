package cristina.asensio.mybudget.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import cristina.asensio.mybudget.repository.PreferencesRepository;

public class PreferencesViewModel extends AndroidViewModel {

    private static final String PREFERENCES_NAME = "sharedPreferencesMyBudget";

    private final SharedPreferences mSharedPreferences;
    private PreferencesRepository mPreferencesRepository;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);
        mSharedPreferences = application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        mPreferencesRepository = new PreferencesRepository(mSharedPreferences);
    }

    public String getMaxAmount() {
        return mPreferencesRepository.getMaxAmount();
    }

    public void setMaxAmount(String maxAmount) {
        mPreferencesRepository.setMaxAmount(maxAmount);
    }

    public void setTotalAvailableDisplayed(String totalAvailableDisplayed) {
        mPreferencesRepository.setTotalAvailableDisplayed(totalAvailableDisplayed);
    }

    public String getTotalAvailableDisplayed() {
        return mPreferencesRepository.getTotalAvailableDisplayed();
    }

}
