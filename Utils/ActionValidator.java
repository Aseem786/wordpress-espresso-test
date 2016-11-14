package Utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/*+----------------------------------------------------------------------
 ||
 ||  Class ActionValidator
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: In this class, we have written a function
 ||                  for performing actions on child view.
 ||
 ||         Class Methods:  clickChildViewById(int)
 ||
 ++-----------------------------------------------------------------------*/
public class ActionValidator {
    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- clickChildViewById
    Functionality:- To click the child view by passing the Id
    Parameter1:- id is the id of the child view
    Return Value:- ViewAction
    Date created:-26-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static ViewAction clickChildViewById(final int id) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                String description = "Click on child view by specified id";
                return description;
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- childAtPosition
    Functionality:- To match the child view at a particular position in parent view
    Parameter1:- parentMatcher is the parent view which is matched
    Parameter2:- position is the child position
    Return Value:- ViewAction
    Date created:-26-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}