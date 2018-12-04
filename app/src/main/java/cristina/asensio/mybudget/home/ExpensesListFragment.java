package cristina.asensio.mybudget.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cristina.asensio.mybudget.R;
import cristina.asensio.mybudget.adapter.ExpenseListAdapter;
import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.util.ExpensesUtil;
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;
import cristina.asensio.mybudget.viewmodel.PreferencesViewModel;

public class ExpensesListFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView listView;

    @BindView(R.id.tv_total_budget)
    TextView totalBudgetTextView;

    private Unbinder unbinder;
    private ExpenseViewModel mExpenseViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final ExpenseListAdapter adapter = new ExpenseListAdapter(getActivity());
        listView.setAdapter(adapter);
        listView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mExpenseViewModel = ViewModelProviders.of(getActivity()).get(ExpenseViewModel.class);
        mExpenseViewModel.getAllExpenses().observe(getActivity(), adapter::setExpenses);
    }

    @Override
    public void onResume() {
        super.onResume();
        PreferencesViewModel preferencesViewModel = ViewModelProviders.of(getActivity()).get(PreferencesViewModel.class);
        String maxAmount = preferencesViewModel.getMaxAmount();
        List<Expense> expenses = mExpenseViewModel.getAllExpenses().getValue();
        totalBudgetTextView.setText(ExpensesUtil.getMaxAvailable(maxAmount, expenses));
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
