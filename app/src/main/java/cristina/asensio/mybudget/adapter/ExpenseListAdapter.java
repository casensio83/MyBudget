package cristina.asensio.mybudget.adapter;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cristina.asensio.mybudget.R;
import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.viewmodel.ExpenseViewModel;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseViewHolder> {

    private final LayoutInflater mInflater;
    private List<Expense> mExpenses = Collections.emptyList();
    private Context mContext;

    public ExpenseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ExpenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        holder.bind(mExpenses.get(position));
        holder.removeExpenseButton.setOnClickListener(view -> {
            ExpenseViewModel mExpenseViewModel = ViewModelProviders.of((FragmentActivity) mContext).get(ExpenseViewModel.class);
            mExpenseViewModel.delete(mExpenses.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mExpenses.size();
    }

    public void setExpenses(List<Expense> expenses) {
        mExpenses = expenses;
        notifyDataSetChanged();
    }

    class ExpenseViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title_textView)
        TextView expenseTitleTextView;

        @BindView(R.id.amount_textView)
        TextView expenseAmountTextView;

        @BindView(R.id.remove_expense_button)
        ImageView removeExpenseButton;

        private Expense expense;

        private ExpenseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(Expense expense) {
            this.expense = expense;
            expenseTitleTextView.setText(expense.getTitle());
            expenseAmountTextView.setText(String.format(expense.getAmount() , " €"));
        }
    }
}
