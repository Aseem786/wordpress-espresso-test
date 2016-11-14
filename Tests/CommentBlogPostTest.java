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
import Utils.ReadPropertiesUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/*+----------------------------------------------------------------------
 ||
 ||  Class CommentBlogPostTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement test for commenting the blog post.
 ||
 ||         Scenario Steps:
 ||         1) Filter the Blog post
 ||         2) Verify the filter results.
 ||         3) Click on comments count button in the filter result.
 ||         4) Comment on the blog post.
 ||         5) Verify the comment on the blog post.
 ||
 ||         Class Methods:  commentBlogPost()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class CommentBlogPostTest {
    private static String username = ReadPropertiesUtils.readProperties("testData.properties").getProperty("username");
    private static String blogComment = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogComment");
    private static final String updatedBlogTitle = DataHolder.get("updatedBlogTitle");
    private static final String updatedBlogContent = DataHolder.get("updatedBlogContent");
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- commentBlogPost
      Functionality:- To comment on the blog post
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
    public void commentBlogPost() throws PerformException {
        // Click on Dropdown menu to select the filter
        onView(allOf(withId(R.id.filter_spinner), withParent(allOf(withId(R.id.toolbar_with_spinner), withParent(withId(R.id.app_bar_layout)))), isDisplayed())).perform(click());

        // Select 'Followed Sites' option
        onView(allOf(withId(R.id.text), withText("Followed Sites"), isDisplayed())).perform(click());

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(matches(isDisplayed()));

        // Assert the updated post title is present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(matches(isDisplayed()));

        // Click on the Comments count icon
        onView(allOf(withId(R.id.count_comments), withParent(allOf(withId(R.id.layout_footer), withParent(withId(R.id.layout_container)))), isDisplayed())).perform(click());

        // Fill the Comment field on the Comments screen
        onView(allOf(withId(R.id.edit_comment), withParent(allOf(withId(R.id.reply_box), withParent(withId(R.id.layout_comment_box)))), isDisplayed())).perform(click(), typeText(blogComment), closeSoftKeyboard());

        // Click on 'SEND' button
        onView(allOf(withId(R.id.btn_submit_reply), withText("SEND"), withParent(allOf(withId(R.id.reply_box), withParent(withId(R.id.layout_comment_box)))), isDisplayed())).perform(click());

        // Assert the posted comment is present on the screen
        onView(allOf(withId(R.id.text_comment_text), withText(blogComment), isDisplayed())).check(matches(withText(blogComment)));

        // Navigate to the Readers screen
        onView(allOf(withContentDescription("Navigate up"), withParent(withId(R.id.toolbar)), isDisplayed())).perform(click());

        // Swipe right over the main screen
        onView(withId(R.id.viewpager_main)).perform(swipeRight());

        // Click on the 'Comments'
        onView(withId(R.id.row_comments)).perform(scrollTo(), click());

        // Assert the blog title is present along with the username on the Comments screen
        onView(allOf(withId(R.id.title), withText(username + " on " + updatedBlogTitle), isDisplayed())).check(matches(withText(username + " on " + updatedBlogTitle)));

        // Assert the posted comment present on the Comments screen
        onView(allOf(withId(R.id.comment), withText(blogComment), isDisplayed())).check(matches(withText(blogComment)));
    }
}