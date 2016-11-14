package Utils;

import java.util.Random;
/*+----------------------------------------------------------------------
 ||
 ||  Class Generators
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used for generating test data such as random string and numbers.
 ||
 ||         Class Methods:  generateRandomString()
 ||                         isIdleNow()
 ||                         registerIdleTransitionCallback(ResourceCallback)
 ||                         startWaiting(long)
 ||                         stopWaiting(IdlingResource)
 ++-----------------------------------------------------------------------*/
public class Generators {
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
    public static String generateRandomString() {
        StringBuffer randomString = new StringBuffer();
        for (int i = 0; i < Constants.STRING_LENGTH; i++) {
            int number = getRandomNumber();
            char character = Constants.CHARACTERS_LIST.charAt(number);
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
        randomInteger = random.nextInt(Constants.CHARACTERS_LIST.length());
        if (randomInteger - 1 == -1) {
        } else {
            randomInteger = randomInteger - 1;
        }
        return randomInteger;
    }
}