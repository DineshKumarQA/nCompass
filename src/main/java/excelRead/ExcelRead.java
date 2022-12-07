package excelRead;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelRead {

	// Declarations Of Log4j For This Class.
	public static Logger log = Logger.getLogger(ExcelRead.class.getName());

	// Declarations Of Excel Variables.
	public static FileInputStream ExcelIO;
	public static XSSFWorkbook Workbook;
	public static XSSFSheet ActualSheet;
	public static int lastRowNum_Of_GivenSheet;

	// Declaration of "HashMap", To Store All Row Values From Given Sheet.
	public static HashMap<String, String> Temp_hashMapDatas_FromSheetRead = new HashMap<String, String>();

	// Method - For Reading the Given Sheet From Excel.
	public static HashMap<String, String> excelSheetRead(String ExcelPath, String SheetName) {		
		
		log.info("*************** <Excel_Sheet_Read Initiated> ***************");	
		
		
		log.info("The Given Excel Path is : '"+ExcelPath+"'");
		log.info("The Given Sheet Name is : '"+SheetName+"'");
		
		//To Clear Temporary Sheet Data. As the Data will be Moved to Separate HashMaps Defined for Sheets in Class = 'Test_Excel_Details'
		Temp_hashMapDatas_FromSheetRead.clear();

		try {
			ExcelIO = new FileInputStream(ExcelPath);
			log.info("'ExcelFilePath' is Given To'FileInputStream'");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("Excel_File Not Found in Given Path : " + ExcelPath);
		}

		try {
			Workbook = new XSSFWorkbook(ExcelIO);
			log.info("WorKBook is Configured For Given Excel");
		} catch (IOException e) {
			e.printStackTrace();
			log.error("There is No Such Excel in Given Path!. Kindly Ensure the Given Excel_Path is Valid/Not!!");
		}

		try {
			ActualSheet = Workbook.getSheet(SheetName);
			log.info("The Sheet Name is '" + ActualSheet.getSheetName() + "'");
		} catch (Exception e) {
			log.error("There is No Such Sheet in Given Excel!. Kindly Ensure the Given Sheet_Name is Valid/Not!!");
		}

		// Get Last Row Number Of Sheet To Iterate All Row Values To HashMap.
		lastRowNum_Of_GivenSheet = ActualSheet.getLastRowNum();
		log.info("'" + ActualSheet.getSheetName() + "' Sheet's Last Row Number is = " + lastRowNum_Of_GivenSheet);

		String Map_KeyData;
		String Map_ValueData;

		DataFormatter dft = new DataFormatter();

		Cell CellData_Key;
		Cell CellData_Value;

		// Getting the Datas From Excel.
		for (int initialRow = 1; initialRow <= lastRowNum_Of_GivenSheet; initialRow++) {

			// Try Getting 'Key_Data'
			try {
				CellData_Key = ActualSheet.getRow(initialRow).getCell(0);
				Map_KeyData = dft.formatCellValue(CellData_Key);
			} catch (Exception e) {
				Map_KeyData = ""; // For "Empty Cell" Storing Purpose
			}

			// Try Getting 'Value_Data'
			try {
				CellData_Value = ActualSheet.getRow(initialRow).getCell(1);
				Map_ValueData = dft.formatCellValue(CellData_Value);

			} catch (Exception e) {
				Map_ValueData = ""; // For "Empty Cell" Storing Purpose
			}

			// Try Storing the Read Values From Excel Into HashMap.
			try {
				Temp_hashMapDatas_FromSheetRead.put(Map_KeyData, Map_ValueData);
				// log.info("Key : "+LoginPageElements_KeyData+" | Value : "+ LoginPageElements_ValueData);
			} catch (Exception e) {
				log.info("Data Store From Excel into 'HashMap' is Failed!. Need Technical Assistance!!");
			}

		}
		log.info("--------------- <Excel_Sheet_Read Completed> ---------------");

		return Temp_hashMapDatas_FromSheetRead;
	}

}
