package handleFields;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FindDeal_Edit_Delete {

	// Declarations Of Log4j For This Class
	public static Logger log = Logger.getLogger(FindDeal_Edit_Delete.class.getName());

	static WebDriverWait wait_E;

	static WebElement Next_Page_Btn_EL;
	static WebElement Deal_Approval_Menu_EL;

	static WebElement DealID_Header_EL;
	static WebElement DealID_Row_EL;

	public static void findDeal(WebDriver driver, String EditMode_DealID_E) {

		log.info("*************** <findDeal Method Started> ***************");

		wait_E = beforeTest.DriverConfig.wait_E;

		/** ----------------------------------------------------------------------------------------------------------- **/
		
		if (handleFields.PageLoad_CircleBar_Handle.is_PageLoaded_Check(driver)) {
			// [DK - Click on "Pending Deal_Icon" From the DashBoard]
			wait_E.until(ExpectedConditions
					.elementToBeClickable(frameWorks.pageObjectModel.Dashboard_Elements.Pending_Deal_Icon_EL(driver))).click();
		}
		
		// [DK - To Handle Prompted Alert Message, As It Intercept the Click Action]
		handleFields.AlertMsg.alert_MsgHandle(driver);

		/** ----------------------------------------------------------------------------------------------------------- **/

		// [DK - Find And Click On Top-Left Corner-DropDown, Which Having 'Deal Approval',' Pending Settlement',Etc - DashBoards]
		wait_E.until(
				ExpectedConditions.elementToBeClickable(frameWorks.pageObjectModel.Dashboard_Elements.ApprovalDropDown_EL(driver)))
				.click();

		/** 
		 * WHAT - To Find the 'Deal Approval', 'Pending Settlement',Etc - Which Mentioned in Excel.
		 * WHY - To Get the DashBoard of 'Approval', 'Settlement'.
		 * FOR - To Perform Further Actions on Deals.
		 **/
		int dealApprv_menuFind = 1;
		boolean dealApprv_menuFind_Flag = true;
		String tempDealApprv_menuFind = null;

		while (dealApprv_menuFind_Flag) {

			Deal_Approval_Menu_EL = wait_E.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//li[" + dealApprv_menuFind + "]"))));
			tempDealApprv_menuFind = Deal_Approval_Menu_EL.getText();

			log.info("Approval Dropdown's -> List Name : " + tempDealApprv_menuFind);

			// [DK - Comparing Exact DropDown Value(i.e, 'Deal Approval') From Excel To UI DropDown Value]
			if (tempDealApprv_menuFind.equalsIgnoreCase(frameWorks.pageObjectModel.Dashboard_Elements.Deal_Approval_Menu_X)) {
				Deal_Approval_Menu_EL.click();
				dealApprv_menuFind_Flag = false;
			} else {
				dealApprv_menuFind++;
			}
		}

		/** ----------------------------------------------------------------------------------------------------------- **/
		
		// [DK - To Find the 'Deal ID' Header Position, And Then Find the Actual 'Deal ID' Row]
		 
		int dealID_Column_HeaderPosition;
		int dealID_Row_Position = 1;
		boolean dealIDFlag = true;
		String tempDealID = null;

		for (dealID_Column_HeaderPosition = 1; dealID_Column_HeaderPosition <= 15; dealID_Column_HeaderPosition++) {
			/**
			 * [DK - 
			 * WHAT - To Find the "DealID" Table Header Column Position.
			 * WHY - To Get All DealIDs from Rows & Find Identify the Exact 'DealID'.
			 * FOR - To Perform Click on Deal for 'Edit' / 'Delete']	 
			 **/
			DealID_Header_EL = wait_E.until(ExpectedConditions.visibilityOf(
					driver.findElement(By.xpath("//th[" + dealID_Column_HeaderPosition + "]")))); /** //th[9] **/

			if (DealID_Header_EL.getText().equalsIgnoreCase("Deal ID")) {
				
				while (dealIDFlag) {
					
					DealID_Row_EL = wait_E.until(ExpectedConditions
							.visibilityOf(driver.findElement(By.xpath("//tr[" + dealID_Row_Position + "]//td["
									+ dealID_Column_HeaderPosition + "]")))); /** "//tr["+1+"]//td["+9+"]" **/
					tempDealID = DealID_Row_EL.getText().trim();

					log.info("Temp DealIDs : " + tempDealID);
					log.info("Target DealID : " + EditMode_DealID_E);
					log.info("Row Count : " + dealID_Row_Position);

					if (!EditMode_DealID_E.isEmpty()) {
						if (tempDealID.equals(EditMode_DealID_E)) {
							DealID_Row_EL.click();

							dealIDFlag = false;
						} else {
							log.info("The Deal ID is Empty!. Test Will Not Continue!!");
							driver.quit();
						}
					} else {
						if ((dealID_Row_Position %= 5) == 0) {
							wait_E.until(ExpectedConditions
									.elementToBeClickable(frameWorks.pageObjectModel.Dashboard_Elements.Next_Page_Btn_EL(driver)))
									.click();
							log.info("Next_Page Button is Clicked");
						}

						dealID_Row_Position++;
					}
				}
			}

			if (dealIDFlag == false) {
				break;
			}

		}

		/** ----------------------------------------------------------------------------------------------------------- **/

		// In Deal Information Card > Top Right Corner > click on Three Dot
		// Button
		wait_E.until(ExpectedConditions.elementToBeClickable(frameWorks.pageObjectModel.Dashboard_Elements.Three_Dots_Btn_EL(driver)))
				.click();

		// In Three Dot Button > Click On 'Edit' Button.
		wait_E.until(ExpectedConditions.elementToBeClickable(frameWorks.pageObjectModel.Dashboard_Elements.Deal_Edit_EL(driver)))
				.click();

		// wait_E.until(ExpectedConditions.elementToBeClickable(pageObjectModel.Menu.Deal_Delete_EL(driver))).click();

		log.info("--------------- <findDeal Method Completed> ---------------");
	}

}
