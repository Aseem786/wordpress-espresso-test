package Tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.WPLaunchActivity;
import org.wordpress.android.ui.accounts.SignInActivity;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.ReadPropertiesUtils;

import static Utils.Assertions.assertCurrentActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SignInTest {
    private static String email = ReadPropertiesUtils.readProperties("testData.properties").getProperty("email");
    private static String password = ReadPropertiesUtils.readProperties("testData.properties").getProperty("password");

    /**
     * Launches {@link WPLaunchActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPLaunchActivity> mActivityTestRule = new ActivityTestRule<>(WPLaunchActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- signin
      Functionality:- To sign-in into the application
      Parameter1:- NA
      Return Value:- NA
      Date created:-19-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    @Test
    public void signin() {
        // Assert the sign in activity is present
        assertCurrentActivity(SignInActivity.class);

        // Fill the email field
        onView(withId(R.id.nux_username)).perform(typeText(email), closeSoftKeyboard());

        // Fill the password field
        onView(withId(R.id.nux_password)).perform(typeText(password), closeSoftKeyboard());

        // Click on Sign In button
        onView(withId(R.id.nux_sign_in_button)).perform(click());

        // Assert the tab layout is present
        onView(withId(R.id.tab_layout)).check(matches(isDisplayed()));

        // Assert the main activity is present
        assertCurrentActivity(WPMainActivity.class);
    }
}