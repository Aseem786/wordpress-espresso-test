package Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        return DataHolder.DATA_HOLDER.size();
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
        DataHolder.DATA_HOLDER.put(key, value);
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

        return DataHolder.DATA_HOLDER.get(key);
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
        return DataHolder.DATA_HOLDER.keySet();
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
        return DataHolder.DATA_HOLDER.containsValue(value);
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
        return DataHolder.DATA_HOLDER.containsKey(key);
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
        DataHolder.DATA_HOLDER.put(key, value);
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
        DataHolder.DATA_HOLDER.remove(key);
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
        DataHolder.DATA_HOLDER.clear();
    }
}
