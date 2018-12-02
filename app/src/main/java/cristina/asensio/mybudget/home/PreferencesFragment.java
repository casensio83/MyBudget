package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
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
import cristina.asensio.mybudget.preferences.PreferencesViewModel;

public class PreferencesFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.preferences_max_amount)
    EditText preferencesMaxAmountEditText;

    @BindView(R.id.save_preferences)
    Button savePreferencesButton;

    private Unbinder unbinder;
    PreferencesViewModel preferencesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.preferences, container, false);
        unbinder = ButterKnife.bind(this, view);
        savePreferencesButton.setOnClickListener(this);
        preferencesViewModel = ViewModelProviders.of(getActivity()).get(PreferencesViewModel.class);
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
        preferencesViewModel.setMaxAmount(preferencesMaxAmountEditText.getText().toString());
        goBackToExpensesList();
    }

    private void goBackToExpensesList() {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.screen_container, new ExpensesListFragment())
                .addToBackStack(null)
                .commit();
    }
}
