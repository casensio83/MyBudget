package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cristina.asensio.mybudget.R;
import cristina.asensio.mybudget.adapter.ExpenseListAdapter;
import cristina.asensio.mybudget.manager.TotalAvailableManager;
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;

public class ExpensesListFragment extends Fragment {

    private final String TOTAL_AVAILABLE_KEY = "total_available";
    private final String PREFERENCES_KEY = "my_budget_preferences";
    private final String TWO_DECIMALS_FORMAT = "%.2f";
    private final int PRIVATE_MODE = 0;
    private final int DEFAULT_VALUE = 0;


    @BindView(R.id.recycler_view)
    RecyclerView listView;

    @BindView(R.id.tv_total_budget)
    TextView totalBudgetTextView;

    private Unbinder unbinder;
    private ExpenseViewModel mExpenseViewModel;
    private SharedPreferences mSharedPreferences;
    private float total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        mSharedPreferences = getActivity().getSharedPreferences(PREFERENCES_KEY, PRIVATE_MODE);
        showFloatingActionButton();
        total = TotalAvailableManager.calculateTotalAvailable(mSharedPreferences);

        return view;
    }

    private void showFloatingActionButton() {
        final FloatingActionButton fab = ((MainActivity) getActivity()).getFloatingActionButton();
        fab.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final ExpenseListAdapter adapter = new ExpenseListAdapter(getActivity(), totalBudgetTextView);
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExpenseViewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);
        mExpenseViewModel.getAllExpenses().observe(getActivity(), adapter::setExpenses);
        totalBudgetTextView.setText(String.format(TWO_DECIMALS_FORMAT, total));
    }

    @Override
    public void onResume() {
        super.onResume();

        float totalAvailable = mSharedPreferences.getFloat(TOTAL_AVAILABLE_KEY, DEFAULT_VALUE);
        totalBudgetTextView.setText(String.valueOf(totalAvailable));

    }

    @Override
    public void onPause() {
        super.onPause();
        hideFloatingActionButton();
    }

    private void hideFloatingActionButton() {
        final FloatingActionButton fab = ((MainActivity) getActivity()).getFloatingActionButton();
        fab.hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
    }
}
