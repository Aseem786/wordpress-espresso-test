package Tests;

import android.support.test.espresso.PerformException;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.DataHolder;

import static Utils.ActionValidator.childAtPosition;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/*+----------------------------------------------------------------------
 ||
 ||  Class LikeBlogPostTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement test for liking the blog post.
 ||
 ||         Scenario Steps:
 ||         1) Navigate to the Readers screen
 ||         2) Filter the Blog post
 ||         3) Verify the filter results.
 ||         4) Click on count likes button in the filter result.
 ||         5) Like the blog post.
 ||         6) Verify the like count.
 ||         7) Also, verify that blog post is present in 'Post I like' filter
 ||
 ||         Class Methods:  likeBlogPost()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class LikeBlogPostTest {
    private static final String updatedBlogTitle = DataHolder.get("updatedBlogTitle");
    private static final String updatedBlogContent = DataHolder.get("updatedBlogContent");
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- likeBlogPost
      Functionality:- To like the blog post
      Parameter1:- NA
      Return Value:- NA
      Date created:-28-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    @Test
    public void likeBlogPost() throws PerformException {
        // Swipe left over the Readers screen
        onView(withId(R.id.viewpager_main)).perform(swipeLeft());

        // Click on Dropdown menu to select the filter
        onView(allOf(withId(R.id.filter_spinner), withParent(allOf(withId(R.id.toolbar_with_spinner), withParent(withId(R.id.app_bar_layout)))), isDisplayed())).perform(click());

        // Select 'Followed Sites' option
        onView(allOf(withId(R.id.text), withText("Followed Sites"), isDisplayed())).perform(click());

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(matches(isDisplayed()));

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(matches(isDisplayed()));

        // Assert the like count is present as '0'
        onView(allOf(withId(R.id.text_count), withText("0"), childAtPosition(childAtPosition(withId(R.id.count_likes), 0), 1), isDisplayed())).check(matches(withText("0")));

        // Click on the 'Count likes' button
        onView(allOf(withId(R.id.count_likes), withParent(allOf(withId(R.id.layout_footer), withParent(withId(R.id.layout_container)))), isDisplayed())).perform(click());

        // Assert the like count is increased to '1'
        onView(allOf(withId(R.id.text_count), withText("1"), childAtPosition(childAtPosition(withId(R.id.count_likes), 0), 1), isDisplayed())).check(matches(withText("1")));

        // Click on Dropdown menu to select the filter
        onView(allOf(withId(R.id.filter_spinner), withParent(allOf(withId(R.id.toolbar_with_spinner), withParent(withId(R.id.app_bar_layout)))), isDisplayed())).perform(click());

        // Select 'Posts I Like' option
        onView(allOf(withId(R.id.text), withText("Posts I Like"), isDisplayed())).perform(click());

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(matches(isDisplayed()));

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(matches(isDisplayed()));
    }
}