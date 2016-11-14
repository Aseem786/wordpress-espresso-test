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
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
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
 ||  Class AddBlogPostTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement for adding blog post scenario.
 ||
 ||         Scenario Steps:
 ||         1) Click on the 'Blog Posts'
 ||         2) Click on 'Fab' button to add the new blog post
 ||         3) Fill the 'Post Title' field
 ||         4) Fill the 'Share your story here' field
 ||         5) Click on 'Publish' button
 ||         6) Verify the added blog post.
 ||
 ||         Class Methods:  addBlogPost()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class AddBlogPostTest {
    private static String blogTitle = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogTitle") + Generators.generateRandomString();
    private static String blogContent = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogContent") + Generators.generateRandomString();
    private static long shortWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("shortWaitTime"));
    private static long longWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("longWaitTime"));
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- addBlogPost
      Functionality:- To add the blog post
      Parameter1:- NA
      Return Value:- NA
      Date created:-22-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    @Test
    public void addBlogPost() {
        // Click on the 'Blog Posts'
        onView(withId(R.id.row_blog_posts)).perform(scrollTo(), click());

        // Wait for fab button
        onView(isRoot()).perform(waitView(withId(R.id.fab_button), shortWaitTime));

        // Click on 'Fab' button to add the new blog post
        onView(allOf(withId(R.id.fab_button), withContentDescription("New post"), withParent(allOf(withId(R.id.coordinator), withParent(withId(R.id.postList)))), isDisplayed())).perform(click());

        //wait for loading the web view
        onView(isRoot()).perform(waitView(withId(R.id.webview), shortWaitTime));

        // Click on 'Html' button
        onView(allOf(withId(R.id.format_bar_button_html), withContentDescription("HTML mode"), withParent(allOf(withId(R.id.format_bar_buttons), withParent(withId(R.id.format_bar)))), isDisplayed())).perform(click());

        // Fill the 'Post Title' field
        onView(allOf(withId(R.id.sourceview_title), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(typeText(blogTitle), closeSoftKeyboard());

        // Fill the 'Share your story here' field
        onView(allOf(withId(R.id.sourceview_content), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(typeText(blogContent), closeSoftKeyboard());

        // Click on 'Publish' button
        onView(allOf(withId(R.id.menu_save_post), withText("Publish"), withContentDescription("Publish"), isDisplayed())).perform(click());

        // Wait for blog title
        onView(isRoot()).perform(waitView(allOf(withId(R.id.text_title), withText(blogTitle)), longWaitTime));

        // Assert the post title present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(blogTitle))).check(matches(withText(blogTitle)));

        // Assert the share your story here(blog content) present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(blogContent))).check(matches(withText(blogContent)));
    }
}