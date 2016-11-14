package Tests;

import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wordpress.android.R;
import org.wordpress.android.ui.main.WPMainActivity;

import Utils.DataHolder;
import Utils.ElapsedTimeIdlingResource;
import Utils.ReadPropertiesUtils;

import static Utils.ActionValidator.clickChildViewById;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.allOf;

/*+----------------------------------------------------------------------
 ||
 ||  Class DeleteBlogPostTest
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to implement test for deleting blog post scenario.
 ||
 ||         Scenario Steps:
 ||         1) Click on the 'Blog Posts'
 ||         2) Click on the Trash button of the first blog post present on the Posts screen
 ||         3) Verify the blog post is not present on the post screen
 ||
 ||         Class Methods:  deleteBlogPost()
 ++-----------------------------------------------------------------------*/
@RunWith(AndroidJUnit4.class)
public class DeleteBlogPostTest {
    private static long shortWaitTime = parseLong(ReadPropertiesUtils.readProperties("testData.properties").getProperty("shortWaitTime"));
    private static final String updatedBlogTitle = DataHolder.get("updatedBlogTitle");
    private static final String updatedBlogContent = DataHolder.get("updatedBlogContent");
    /**
     * Launches {@link WPMainActivity} for the test to begin
     */
    @Rule
    public ActivityTestRule<WPMainActivity> mActivityTestRule = new ActivityTestRule<>(WPMainActivity.class);

    /*--------------------------------------------------------------------------------------------------------------
      Function Name:- deleteBlogPost
      Functionality:- To delete the blog post
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
    public void deleteBlogPost() {
        // Click on the 'Blog Posts'
        onView(withId(R.id.row_blog_posts)).perform(scrollTo(), click());

        // Register iding resource to wait for blog title to load
        IdlingResource waitBlogToLoad = ElapsedTimeIdlingResource.startWaiting(shortWaitTime);

        // Click on the Trash button of the first blog post present on the Posts screen
        onView(allOf(withId(R.id.recycler_view), withParent(allOf(withId(R.id.ptr_layout), withParent(withId(R.id.coordinator)))), isDisplayed())).perform(actionOnItemAtPosition(0, clickChildViewById(R.id.btn_trash)));

        // Unregister iding resource to wait for blog title to load
        ElapsedTimeIdlingResource.stopWaiting(waitBlogToLoad);

        // Register iding resource to wait for post to delete
        IdlingResource waitBlogToDelete = ElapsedTimeIdlingResource.startWaiting(shortWaitTime);

        // Assert the updated post title is not present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(doesNotExist());

        // Assert the updated share your story here(blog content) is not present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(doesNotExist());

        // Unregister iding resource to wait for blog title to delete
        ElapsedTimeIdlingResource.stopWaiting(waitBlogToDelete);
    }
}