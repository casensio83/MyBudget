package cristina.asensio.mybudget.manager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import cristina.asensio.mybudget.database.Expense;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TotalAvailableManagerTest {

    private List<Expense> mExpenses;

    @Before
    public void init() {
        mExpenses = Arrays.asList(
                new Expense("", 100, "", ""),
                new Expense("", 50, "", ""));
    }


    @Test
    public void totalAvailableManager_addsNewExpense_newExpenseAddedToTotalAvailable() {
        String result = TotalAvailableManager.getUpdatedTotalAvailable(mExpenses, 600, 0);
        assertThat(result, is("700.0"));
    }

}