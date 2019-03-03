package cristina.asensio.mybudget.home;

import androidx.test.espresso.action.ViewActions;
import androidx.test.rule.ActivityTestRule;
import cristina.asensio.mybudget.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainActivityTest {

    @Rule
    public ActivityTestRule mainActivity = new ActivityTestRule(MainActivity.class);

    @Test
    public void givenApplicationIsLaunched_whenMainActivityStarts_thenTotalBudgetTextViewIsDisplayed() {
        onView(withId(R.id.tv_total_budget)).check(matches(isDisplayed()));
    }

    @Test
    public void givenWeAreInMainScreen_whenClickButtonToAddExpense_thenFragmentToAddExpenseIsDisplayed() {
        onView(withId(R.id.fab)).perform(ViewActions.click());
        onView(withId(R.id.new_expense_title_edittext)).check(matches(isDisplayed()));
    }

}