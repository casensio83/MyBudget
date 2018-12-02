package cristina.asensio.mybudget.preferences;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class PreferencesViewModel extends AndroidViewModel {

    private static final String PREFERENCES_NAME = "sharedPreferencesMyBudget";
    private final SharedPreferences sharedPreferences;
    private PreferencesRepository mPreferencesRepository;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);
        sharedPreferences = application.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        mPreferencesRepository = new PreferencesRepository(sharedPreferences);
    }

    public String getMaxAmount() {
        return mPreferencesRepository.getMaxAmount();
    }

    public void setMaxAmount(String maxAmount) {
        mPreferencesRepository.setMaxAmount(maxAmount);
    }

}
