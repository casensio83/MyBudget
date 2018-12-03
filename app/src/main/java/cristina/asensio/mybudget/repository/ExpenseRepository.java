package cristina.asensio.mybudget.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import cristina.asensio.mybudget.database.Expense;
import cristina.asensio.mybudget.database.ExpenseDao;
import cristina.asensio.mybudget.database.ExpenseRoomDatabase;

public class ExpenseRepository {

    private ExpenseDao mExpenseDao;
    private LiveData<List<Expense>> mAllExpenses;

    public ExpenseRepository(Application application) {
        ExpenseRoomDatabase db = ExpenseRoomDatabase.getDatabase(application);
        this.mExpenseDao = db.expenseDao();
        mAllExpenses = mExpenseDao.getAlphabetizedExpenses();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return mAllExpenses;
    }

    public void insert(Expense expense) {
        new insertAsyncTask(mExpenseDao).execute(expense);
    }

    public void delete(Expense expense) {
        new deleteAsyncTask(mExpenseDao).execute(expense);
    }

    private static class insertAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao mAsyncTaskDao;

        insertAsyncTask(ExpenseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Expense... expenses) {
            mAsyncTaskDao.insert(expenses[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Expense, Void, Void> {

        private ExpenseDao mAsyncTaskDao;

        deleteAsyncTask(ExpenseDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Expense... expenses) {
            mAsyncTaskDao.delete(expenses[0]);
            return null;
        }
    }
}
