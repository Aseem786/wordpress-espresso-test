package Utils;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;

public class ElapsedTimeIdlingResource implements IdlingResource {
    private long startTime;
    private final long waitTime;
    private ResourceCallback resourceCallback;

    public ElapsedTimeIdlingResource(long waitTime) {
        this.startTime = System.currentTimeMillis();
        this.waitTime = waitTime;
    }

    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName();
    }

    @Override
    public boolean isIdleNow() {
        long elapsedTime = System.currentTimeMillis() - startTime;
        boolean idle = (elapsedTime >= waitTime);
        if (idle && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- startWaiting
    Functionality:- To  register the resource before Espresso operation
    Parameter1:- time is the waiting time
    Return Value:- IdlingResource
    Date created:-25-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static IdlingResource startWaiting(long time) {
        IdlingResource idlingResource = new ElapsedTimeIdlingResource(time);
        Espresso.registerIdlingResources(idlingResource);
        return idlingResource;
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- stopWaiting
    Functionality:- To  unregister the resource after Espresso operation
    Parameter1:- idlingResource is the idling resource
    Return Value:- NA
    Date created:-25-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void stopWaiting(IdlingResource idlingResource) {
        Espresso.unregisterIdlingResources(idlingResource);
    }
}
