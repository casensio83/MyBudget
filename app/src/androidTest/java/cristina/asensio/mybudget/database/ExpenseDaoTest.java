package cristina.asensio.mybudget.database;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExpenseDaoTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private ExpenseDao mExpenseDao;
    private ExpenseRoomDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();

        //Using an in-memory ddbb because the info stored here disappears when the process is killed
        mDb = Room.inMemoryDatabaseBuilder(context, ExpenseRoomDatabase.class)
                .allowMainThreadQueries()
                .build();
        mExpenseDao = mDb.expenseDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }

    @Test
    public void insertAndGetExpense() throws Exception {
        Expense expense = new Expense("11/11/2018", 100, "Transport", "");
        mExpenseDao.insert(expense);
        List<Expense> allExpenses = LiveDataTestUtil.getValue(mExpenseDao.getExpenses());
        assertEquals(allExpenses.get(0).getTitle(), expense.getTitle());
    }

    @Test
    public void getAllExpenses() throws Exception {
        Expense expense = new Expense("11/11/2018", 100, "Transport", "");
        mExpenseDao.insert(expense);
        Expense expense2 = new Expense("12/11/2018", 200, "House", "");
        mExpenseDao.insert(expense2);
        List<Expense> allExpenses = LiveDataTestUtil.getValue(mExpenseDao.getExpenses());
        assertEquals(allExpenses.get(0).getTitle(), expense.getTitle());
        assertEquals(allExpenses.get(1).getTitle(), expense2.getTitle());
    }

    @Test
    public void deleteAll() throws Exception {
        Expense expense = new Expense("11/11/2018", 100, "Transport", "");
        mExpenseDao.insert(expense);
        Expense expense2 = new Expense("12/11/2018", 200, "House", "");
        mExpenseDao.insert(expense2);
        mExpenseDao.deleteAll();
        List<Expense> allExpenses = LiveDataTestUtil.getValue(mExpenseDao.getExpenses());
        assertTrue(allExpenses.isEmpty());
    }

    @Test
    public void deleteOneExpense() throws Exception {
        Expense expense = new Expense("11/11/2018", 100, "Transport", "");
        mExpenseDao.insert(expense);
        mExpenseDao.delete(expense);
        List<Expense> allExpenses = LiveDataTestUtil.getValue(mExpenseDao.getExpenses());
        assertTrue(allExpenses.isEmpty());
    }

}