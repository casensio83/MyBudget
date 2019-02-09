package cristina.asensio.mybudget.manager;

import android.content.Context;
import android.content.SharedPreferences;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import cristina.asensio.mybudget.database.Expense;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TotalAvailableManagerTest {

    private final float DEFAULT_AMOUNT = 600;

    private List<Expense> mExpenses;
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    @Before
    public void init() {
        mExpenses = Arrays.asList(
                new Expense("", 100, "", ""),
                new Expense("", 50, "", ""));
        mSharedPreferences = mock(SharedPreferences.class);
        mContext = mock(Context.class);
    }

    @Test
    public void getUpdatedTotalAvailable_newExpenseAdded_newExpenseAddedToTotalAvailable() {
        String result = TotalAvailableManager.getUpdatedTotalAvailable(mExpenses, DEFAULT_AMOUNT, 0);
        assertThat(result, is("700.00"));
    }

    @Test
    public void calculateTotalAvailable_applicationIsInstalled_maxAmountIsDefault() {
        when(mSharedPreferences.getFloat(anyString(), anyFloat())).thenReturn(DEFAULT_AMOUNT);
        float total = TotalAvailableManager.calculateTotalAvailable(mSharedPreferences);
        assertThat(total, is(DEFAULT_AMOUNT));
    }

}