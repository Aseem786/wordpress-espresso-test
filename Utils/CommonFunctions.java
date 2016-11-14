package Utils;

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

/*+----------------------------------------------------------------------
 ||
 ||  Class CommonFunctions
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: In this class, we have written some common functions
 ||                  which can be used across the different test classes.
 ||
 ||         Class Methods:  waitId(int, long)
 ||
 ++-----------------------------------------------------------------------*/
public class CommonFunctions {
    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- waitView
    Functionality:- Perform action of waiting for a specific view.
    Parameter1:- viewProperties is the properties of a view
    Parameter2:- millis is the time to wait for view
    Return Value:- ViewAction
    Date created:-28-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static ViewAction waitView(final Matcher<View> viewProperties,final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                String description = "wait for a specific view with properties <" + viewProperties + "> during " + millis + " millis.";
                return description;
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = viewProperties;

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(Constants.DELAY);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}