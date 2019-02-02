package cristina.asensio.mybudget.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cristina.asensio.mybudget.R;

public class PreferencesFragment extends Fragment implements View.OnClickListener {

    private final String PREFERENCES_KEY = "my_budget_preferences";
    private final String MAX_AMOUNT_TO_SPEND_A_MONTH_KEY = "max_amount_to_spend";
    private final float MAX_AMOUNT_TO_SPEND_A_MONTH_DEFAULT = 600;
    private final int PRIVATE_MODE = 0;

    @BindView(R.id.preferences_max_amount)
    EditText preferencesMaxAmountEditText;

    @BindView(R.id.save_preferences)
    Button savePreferencesButton;

    private Unbinder unbinder;
    private SharedPreferences mSharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preferences, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSharedPreferences = getActivity().getSharedPreferences(PREFERENCES_KEY, PRIVATE_MODE);
        savePreferencesButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }

    @Override
    public void onClick(View view) {
        storeMaxAmountToSpendAMonth();
        goBackToExpensesList();
    }

    private void storeMaxAmountToSpendAMonth() {
        final float maxAmountToSpendPerMonth = Float.parseFloat(preferencesMaxAmountEditText.getText().toString());
        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        if (maxAmountToSpendPerMonth != 0) {
            editor.putFloat(MAX_AMOUNT_TO_SPEND_A_MONTH_KEY, maxAmountToSpendPerMonth);

        } else {
            editor.putFloat(MAX_AMOUNT_TO_SPEND_A_MONTH_KEY, MAX_AMOUNT_TO_SPEND_A_MONTH_DEFAULT);
        }

        editor.commit();
    }

    private void goBackToExpensesList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_container, new ExpensesListFragment())
                .addToBackStack(null)
                .commit();
    }
}
