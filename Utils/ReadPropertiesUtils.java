package Utils;

import java.io.InputStream;
import java.util.Properties;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class ReadPropertiesUtils {
    private static Properties properties;

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- readProperties
    Functionality:- To read the properties file
    Parameter1:- sourceFile is the name of the file
    Return Value:- properties
    Date created:-30-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static Properties readProperties(String sourceFile) {
        try {
            properties = new Properties();
            InputStream inputStream = getInstrumentation().getContext().getResources().getAssets().open(sourceFile);
            properties.load(inputStream);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

        return properties;
    }
}
