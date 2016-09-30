package Utils;

import android.util.Log;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

public class ReadExcelUtils {
    private static String sourceFile;
    private static Workbook workBook;

    /*--------------------------------------------------------------------------------------------------------------
    Function Name:- readDataFromExcel
    Functionality:- To read the data from excel file and store into the hash map
    Parameter1:- sourceFile is the name of the file
    Parameter2:- sheetName  is the name of the sheet
    Return Value:- map
    Date created:-26-Sep-2016
    Scripted By:-Aseem Tiwari
    Script Reviewed by:-
    -----------------------------------------------------------------------------------------------------------------
    Date Modified   :
    Modified By    :
    Comments       :
    -----------------------------------------------------------------------------------------------------------------
    */
    public static Map<String, String> readDataFromExcel(String sourceFile, String sheetName) {
        Map<String, String> map = null;
        try {
            InputStream myInput = getInstrumentation().getContext().getResources().getAssets().open(sourceFile);
            // Create a POIFSFileSystem object
            POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);

            // Create a workbook using the File System
            HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);

            // Get the sheet from workbook by passing the sheet name
            HSSFSheet mySheet = myWorkBook.getSheet(sheetName);

            // To iterate the cells
            Iterator<Row> rowIter = mySheet.rowIterator();

            //To store the excel values
            map = new HashMap<>();

            while (rowIter.hasNext()) {
                HSSFRow myRow = (HSSFRow) rowIter.next();
                Iterator<Cell> cellIter = myRow.cellIterator();
                while (cellIter.hasNext()) {
                    HSSFCell myCell = (HSSFCell) cellIter.next();
                    Log.e("FileUtils", "Cell Value: " + myCell.toString() + " Column :" + myCell.getColumnIndex());
                    map.put("Row" + myCell.getRowIndex() + "Column" + myCell.getColumnIndex(), myCell.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}