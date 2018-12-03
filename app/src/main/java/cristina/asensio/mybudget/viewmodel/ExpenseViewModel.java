package cristina.asensio.mybudget.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.repository.ExpenseRepository;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseRepository mRepository;

    private LiveData<List<Expense>> mAllExpenses;


    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ExpenseRepository(application);
        mAllExpenses = mRepository.getAllExpenses();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return mAllExpenses;
    }

    public void insert(Expense expense) {
        mRepository.insert(expense);
    }

    public void delete(Expense expense) {
        mRepository.delete(expense);
    }
}
