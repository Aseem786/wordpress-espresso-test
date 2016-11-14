package Tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.Generators;
import Utils.ReadPropertiesUtils;

import static Utils.CommonFunctions.waitView;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

/*+----------------------------------------------------------------------
 ||
 ||  Class EditSiteTitleTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement test for editing site title scenario.
 ||
 ||         Scenario Steps:
 ||         1) Click on the 'Settings'
 ||         2) Click on the 'Site Title'
 ||         3) Enter the Site title
 ||         4) Click on 'OK' button
 ||         5) Verify the updated Site title
 ||
 ||         Class Methods:  editSiteTitle()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class EditSiteTitleTest {
    private static final String siteTitle = Generators.generateRandomString();
    private static long shortWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("shortWaitTime"));
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- editSiteTitle
      Functionality:- To edit the site title
      Parameter1:- NA
      Return Value:- NA
      Date created:-27-Oct-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    @Test
    public void editSiteTitle() {

        // Click on the 'Settings'
        onView(withId(R.id.row_settings)).perform(scrollTo(), click());

        // Click on the 'Site Title'
        onView(allOf(withId(android.R.id.title), withText("Site Title"), isDisplayed())).perform(click());

        // Enter the Site title
        onView(allOf(withId(android.R.id.edit), withParent(withClassName(is("android.widget.LinearLayout"))))).perform(scrollTo(), clearText(), typeText(siteTitle), closeSoftKeyboard());

        // Click on 'OK' button
        onView(allOf(withId(android.R.id.button1), withText("OK"), withParent(allOf(withId(R.id.buttonPanel), withParent(withId(R.id.parentPanel)))), isDisplayed())).perform(click());

        // Wait for site title
        onView(isRoot()).perform(waitView(withId(android.R.id.summary), shortWaitTime));

        // Assert the Site title is updated
        onView(allOf(withId(android.R.id.summary), withText(siteTitle), isDisplayed())).check(matches(withText(siteTitle)));
    }
}