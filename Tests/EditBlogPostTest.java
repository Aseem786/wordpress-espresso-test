package Tests;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.DataHolder;
import Utils.Generators;
import Utils.ReadPropertiesUtils;

import static Utils.ActionValidator.clickChildViewById;
import static Utils.CommonFunctions.waitView;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.allOf;

/*+----------------------------------------------------------------------
 ||
 ||  Class EditBlogPostTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement test for editing blog post scenario.
 ||
 ||         Scenario Steps:
 ||         1) Click on the 'Blog Posts'
 ||         2) Click on the Edit button of the first blog post present on the Posts screen
 ||         3) Update the 'Post Title' field
 ||         4) Update the 'Share your story here' field
 ||         5) Click on 'Update' button
 ||         6) Verify the updated blog post.
 ||
 ||         Class Methods:  editBlogPost()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class EditBlogPostTest {
    private static final String updatedBlogTitle = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogTitle") + Generators.generateRandomString();
    private static final String updatedBlogContent = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogContent") + Generators.generateRandomString();
    private static long shortWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("shortWaitTime"));
    private static long longWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("longWaitTime"));
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- editBlogPost
      Functionality:- To edit the blog post
      Parameter1:- NA
      Return Value:- NA
      Date created:-23-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    @Test
    public void editBlogPost() {
        // To save updated blog title and content
        DataHolder.add("updatedBlogTitle", updatedBlogTitle);
        DataHolder.add("updatedBlogContent", updatedBlogContent);

        // Click on the 'Blog Posts'
        onView(withId(R.id.row_blog_posts)).perform(scrollTo(), click());

        // Click on the Edit button of the first blog post present on the Posts screen
        onView(allOf(withId(R.id.recycler_view), withParent(allOf(withId(R.id.ptr_layout), withParent(withId(R.id.coordinator)))), isDisplayed())).perform(actionOnItemAtPosition(0, clickChildViewById(R.id.btn_edit)));

        //wait for loading the web view
        onView(isRoot()).perform(waitView(withId(R.id.webview), shortWaitTime));

        // Click on the Html button
        onView(allOf(withId(R.id.format_bar_button_html), withContentDescription("HTML mode"), withParent(allOf(withId(R.id.format_bar_buttons), withParent(withId(R.id.format_bar)))), isDisplayed())).perform(click());

        // Update the 'Post Title' field
        onView(allOf(withId(R.id.sourceview_title), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(clearText(), typeText(updatedBlogTitle), closeSoftKeyboard());

        // Update the 'Share your story here' field
        onView(allOf(withId(R.id.sourceview_content), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(clearText(), typeText(updatedBlogContent), closeSoftKeyboard());

        // Click on 'Update' button
        onView(allOf(withId(R.id.menu_save_post), withText("Update"), withContentDescription("Update"), isDisplayed())).perform(click());

        // Wait for blog title
        onView(isRoot()).perform(waitView(allOf(withId(R.id.text_title), withText(updatedBlogTitle)), longWaitTime));

        // Assert the updated post title present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(matches(withText(updatedBlogTitle)));

        // Assert the updated share your story here(blog content) present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(matches(withText(updatedBlogContent)));
    }
}