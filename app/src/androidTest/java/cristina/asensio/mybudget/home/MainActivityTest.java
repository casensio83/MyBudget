package cristina.asensio.mybudget.home;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import cristina.asensio.mybudget.R;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

public class MainActivityTest {

    @Rule
    public ActivityTestRule mainActivity = new ActivityTestRule(MainActivity.class);

    @Test
    public void testTotalBudgetTextViewIsDisplayedInMainActivity() {
        onView(ViewMatchers.withId(R.id.tv_total_budget)).check(matches(isDisplayed()));
    }



}