package Utils;

import android.app.Activity;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import java.util.Collection;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.runner.lifecycle.Stage.RESUMED;
import static org.junit.Assert.assertTrue;

/*+----------------------------------------------------------------------
 ||
 ||  Class Assertions
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: In this class, we have written a function
 ||                  for verifying current activity
 ||
 ||         Class Methods:  assertCurrentActivity(Class)
 ||                         getInstanceOfActivity()
 ++-----------------------------------------------------------------------*/
public class Assertions {
    private static Activity currentActivity = null;

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- assertCurrentActivity
    Functionality:- To assert the current activity is instance of particular class
    Parameter1:- activityClass is the class of that activity
    Return Value:- NA
    Date created:-20-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void assertCurrentActivity(Class<? extends Activity> activityClass) {
        Activity currentActivity = getInstanceOfActivity();
        checkNotNull(currentActivity);
        checkNotNull(activityClass);
        assertTrue(currentActivity.getClass().isAssignableFrom(activityClass));
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- getInstanceOfActivity
    Functionality:- To get the instance of the current activity
    Parameter1:- NA
    Return Value:- NA
    Date created:-20-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static Activity getInstanceOfActivity() {
        getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection activitiesResumed = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(RESUMED);
                if (activitiesResumed != null && activitiesResumed.iterator() != null && activitiesResumed.iterator().hasNext()) {
                    currentActivity = (Activity) activitiesResumed.iterator().next();
                }
            }
        });

        return currentActivity;
    }
}
