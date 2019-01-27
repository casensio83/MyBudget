package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;

public class ExpensesListFragment extends Fragment {

    private final String TOTAL_AVAILABLE_KEY = "total_available";
    private final String PREFERENCES_KEY = "my_budget_preferences";
    private final int PRIVATE_MODE = 0;

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
        float maxAmountToSpend = mSharedPreferences.getFloat(TOTAL_AVAILABLE_KEY, 0);

        if(maxAmountToSpend == 0) {
            Editor editor = mSharedPreferences.edit();
            editor.putFloat(TOTAL_AVAILABLE_KEY, 600);
            editor.commit();
            total = 600;
        } else{
            total = maxAmountToSpend;
        }

        showFloatingActionButton();

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
        totalBudgetTextView.setText(String.valueOf(total));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (totalBudgetTextView != null) {
            total = mSharedPreferences.getFloat(TOTAL_AVAILABLE_KEY, 0);
            totalBudgetTextView.setText(String.valueOf(total));
        }
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
