package Utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import java.util.Random;

public class CommonFunctions {
    private static final int STRING_LENGTH = 10;
    private static final String CHARACTERS_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

      /*--------------------------------------------------------------------------------------------------------------
      Function Name:- generateRandomString
      Functionality:- This method generates random string
      Parameter1:- NA
      Return Value:- Random String
      Date created:-21-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    public static String generateRandomString(){
        StringBuffer randomString = new StringBuffer();
        for(int i=0; i<STRING_LENGTH; i++){
            int number = getRandomNumber();
            char character = CHARACTERS_LIST.charAt(number);
            randomString.append(character);
        }
        return randomString.toString();
    }

      /*--------------------------------------------------------------------------------------------------------------
      Function Name:- getRandomNumber
      Functionality:- This method generates random numbers
      Parameter1:- NA
      Return Value:- Random Integer
      Date created:-21-Sep-2016
      Scripted By:-Aseem Tiwari
      Script Reviewed by:-
      -----------------------------------------------------------------------------------------------------------------
      Date Modified   :
      Modified By    :
      Comments       :
      -----------------------------------------------------------------------------------------------------------------
      */
    public static int getRandomNumber() {
        int randomInteger = 0;
        Random random = new Random();
        randomInteger = random.nextInt(CHARACTERS_LIST.length());
        if (randomInteger - 1 == -1) {
            return randomInteger;
        } else {
            return randomInteger - 1;
        }
    }

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
                return "Click on child view by specified id";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
            }
        };
    }
}