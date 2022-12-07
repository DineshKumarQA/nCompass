package beforeTest;

public class Bfr_Any_Test {

	
	public static void bfr_Any_Test() {
		
		/** WHAT - Initialize Excel Reads, Before Run Any Test.
		 * 	WHY -  To Store Excel Read Values, Into HashMap.
		 * 	FOR -  We Can Call that Map to Read Values, Instead Run the Method EVERYTIME - to Get Values.
		 * 	To Reduce the Repetitive Excel Reads to Get Every Value From Same Sheet.
		 **/		
		
		excelRead.Test_Excel_Details.test_Info_SheetRead();
		excelRead.Test_Excel_Details.login_Page_SheetRead();
		excelRead.Test_Excel_Details.Dashboard_Elements_SheetRead();
		excelRead.Test_Excel_Details.common_Elements_SheetRead();

		/**  Initialize Driver Configuration For 'Once', to Use It for All Tests  **/
		beforeTest.DriverConfig.driverSet();
		beforeTest.DriverConfig.driverWait();
		beforeTest.DriverConfig.driverWait_F();

		/** Before Run Test, Initialize the "LOGIN" **/
		beforeTest.Login.login();

	}
	
	
	public static void bfr_LMM_Test() {

		// loan_ND_Elements - Sheet Read.
		excelRead.Test_Excel_Details.loan_ND_Elements_SheetRead();

		// loan_ND_New_Inputs - Sheet Read.
		excelRead.Test_Excel_Details.loan_ND_New_Inputs_SheetRead();

		// loan_ND_Edit_Inputs - Sheet Read.
		excelRead.Test_Excel_Details.loan_ND_Edit_Inputs_SheetRead();

	}
	

}
