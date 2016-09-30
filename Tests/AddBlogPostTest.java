package Tests;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.CommonFunctions;
import Utils.ReadPropertiesUtils;

import static Utils.ElapsedTimeIdlingResource.startWaiting;
import static Utils.ElapsedTimeIdlingResource.stopWaiting;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class AddBlogPostTest {
    private static String blogTitle = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogTitle") + CommonFunctions.generateRandomString();
    private static String blogContent = ReadPropertiesUtils.readProperties("testData.properties").getProperty("blogContent") + CommonFunctions.generateRandomString();
    private static long shortWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("shortWaitTime"));
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

        // Wait
        IdlingResource idlingResource = startWaiting(shortWaitTime);

        // Click on 'Fab' button to add the new blog post
        onView(allOf(withId(R.id.fab_button), withContentDescription("New post"), withParent(allOf(withId(R.id.coordinator), withParent(withId(R.id.postList)))), isDisplayed())).perform(click());
        stopWaiting(idlingResource);

        // Click on 'Html' button
        onView(allOf(withId(R.id.format_bar_button_html), withContentDescription("HTML mode"), withParent(allOf(withId(R.id.format_bar_buttons), withParent(withId(R.id.format_bar)))), isDisplayed())).perform(click());

        // Fill the 'Post Title' field
        onView(allOf(withId(R.id.sourceview_title), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(typeText(blogTitle), closeSoftKeyboard());

        // Fill the 'Share your story here' field
        onView(allOf(withId(R.id.sourceview_content), withParent(allOf(withId(R.id.post_content_wrapper), withParent(withId(R.id.sourceview)))))).perform(typeText(blogContent), closeSoftKeyboard());

        // Click on 'Publish' button
        onView(allOf(withId(R.id.menu_save_post), withText("Publish"), withContentDescription("Publish"), isDisplayed())).perform(click());

        // Assert the post title present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(blogTitle))).check(matches(isDisplayed()));

        // Assert the share your story here(blog content) present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(blogContent))).check(matches(isDisplayed()));
    }
}