package frameWorks.dataDrivenModel;

import org.apache.log4j.Logger;

public class Test_Info {
	
	// Declarations Of Log4j For This Class
	public static Logger log = Logger.getLogger(Test_Info.class.getName());	
			
		public static String TestBrowser_I;
		public static String nC_URL_I;
		public static String UserName_I;
		public static String PassWord_I;
		
		public static String CW_Elements_I;
		public static String LMM_TestInputs_I;
		
		
		public static void configureValues(){
			
			log.info("*************** <Test_Info > configureValues() Initiate Started> ***************");
			
			TestBrowser_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("Test_Browser");
			nC_URL_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("nC_URL");
			UserName_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("Username");
			PassWord_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("Password");
			
			CW_Elements_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("CW_Elements");
			LMM_TestInputs_I = excelRead.Test_Excel_Details.Test_Info_Sheet_MapDatas.get("LMM_TestInputs");
			
			log.info("--------------- <Test_Info > configureValues() Initiate Completed> ---------------");
		}

}