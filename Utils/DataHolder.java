package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/*+----------------------------------------------------------------------
 ||
 ||  Class DataHolder
 ||
 ||         Author:  Aseem Tiwari
 ||
 ||         Purpose: This class is used to hold the data
 ||                  which can be use in different test classes.
 ||
 ||         Class Methods:  getLength()
 ||                         add(String, String)
 ||                         get(String)
 ||                         getAllKeys()
 ||                         valueExists(String)
 ||                         keyExists(String)
 ||                         update(String, String)
 ||                         remove(String)
 ||                         reset()
 ++-----------------------------------------------------------------------*/
public class DataHolder {
    private static Map<String, String> DATA_HOLDER = new HashMap<String, String>();

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- getLength
    Functionality:- To get the size of the map
    Parameter1:- NA
    Return Value:- Integer
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static int getLength() {
        return DATA_HOLDER.size();
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- add
    Functionality:- To add the key and value into the map
    Parameter1:- NA
    Return Value:- NA
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void add(String key, String value) {
        DATA_HOLDER.put(key, value);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- get
    Functionality:- To get the value by passing the key
    Parameter1:- key
    Return Value:- value
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static String get(String key) {
        if (!DataHolder.keyExists(key))
            return null;

        return DATA_HOLDER.get(key);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- getAllKeys
    Functionality:- To get all the keys
    Parameter1:- NA
    Return Value:- Set<String>
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static Set<String> getAllKeys() {
        return DATA_HOLDER.keySet();
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- valueExists
    Functionality:- To check whether the value exists
    Parameter1:- value is the value
    Return Value:- boolean
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static boolean valueExists(String value) {
        return DATA_HOLDER.containsValue(value);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- keyExists
    Functionality:- To check whether the key exists
    Parameter1:- key is the key
    Return Value:- boolean
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static boolean keyExists(String key) {
        return DATA_HOLDER.containsKey(key);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- update
    Functionality:- To check whether the key exists and update the key and value
    Parameter1:- key is the key
    Parameter2:- value is the value
    Return Value:- NA
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void update(String key, String value) {
        if (!DataHolder.keyExists(key))
            return;
        DATA_HOLDER.put(key, value);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- remove
    Functionality:- To check whether the key exists and remove the key
    Parameter1:- key is the key
    Return Value:- NA
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void remove(String key) {
        if (!DataHolder.keyExists(key))
            return;
        DATA_HOLDER.remove(key);
    }

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- reset
    Functionality:- To clear the map
    Parameter1:- NA
    Return Value:- NA
    Date created:-24-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static void reset() {
        DATA_HOLDER.clear();
    }
}
