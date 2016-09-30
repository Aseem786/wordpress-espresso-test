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
import Utils.ReadPropertiesUtils;

import static Utils.CommonFunctions.clickChildViewById;
import static Utils.ElapsedTimeIdlingResource.startWaiting;
import static Utils.ElapsedTimeIdlingResource.stopWaiting;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Long.parseLong;
import static org.hamcrest.Matchers.allOf;

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

        // Wait
        IdlingResource idlingResource = startWaiting(shortWaitTime);

        // Click on the Trash button of the first blog post present on the Posts screen
        onView(allOf(withId(R.id.recycler_view), withParent(allOf(withId(R.id.ptr_layout), withParent(withId(R.id.coordinator)))), isDisplayed())).perform(actionOnItemAtPosition(0, clickChildViewById(R.id.btn_trash)));
        //stopWaiting(idlingResource);

        // Wait
        idlingResource = startWaiting(shortWaitTime);

        // Assert the updated post title is not present on the Post screen
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(doesNotExist());

        // Assert the updated share your story here(blog content) is not present on the Post screen
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(doesNotExist());
        stopWaiting(idlingResource);

        // Navigate to the Main screen
        onView(allOf(withContentDescription("Navigate up"), withParent(withId(R.id.toolbar)), isDisplayed())).perform(click());

        // Swipe left over the Readers screen
        onView(withId(R.id.viewpager_main)).perform(swipeLeft());

        // Click on the 'Search' icon
        onView(allOf(withId(R.id.menu_reader_search), withContentDescription("Search"), isDisplayed())).perform(click());

        // Type the Search String i.e.updated blog title
        onView(allOf(withId(R.id.search_src_text), withParent(allOf(withId(R.id.search_plate), withParent(withId(R.id.search_edit_frame)))), isDisplayed())).perform(typeText(updatedBlogTitle), closeSoftKeyboard());

        // Click on search button on the keyboard
        onView(allOf(withId(R.id.search_src_text), withText(updatedBlogTitle), withParent(allOf(withId(R.id.search_plate), withParent(withId(R.id.search_edit_frame)))), isDisplayed())).perform(pressImeActionButton());

        // Wait
        idlingResource = startWaiting(shortWaitTime);

        // Assert the updated post title is not present in the search results
        onView(allOf(withId(R.id.text_title), withText(updatedBlogTitle))).check(doesNotExist());

        // Assert the updated share your story here(blog content) is not present in the search results
        onView(allOf(withId(R.id.text_excerpt), withText(updatedBlogContent))).check(doesNotExist());

        // Assert the 'No posts found' is present on the screen
        onView(allOf(withId(R.id.title_empty), withText("No posts found"), isDisplayed())).check(matches(withText("No posts found")));
        stopWaiting(idlingResource);
    }
}