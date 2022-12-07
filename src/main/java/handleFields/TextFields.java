package handleFields;

import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TextFields {
	
	// Declarations Of Log4j For This Class
	public static Logger log = Logger.getLogger(TextFields.class.getName());		
	
	static Actions action;
	
	
	public static void textFieldsInputsHandle(WebDriver driver,WebElement Element,String Input){
					
		action = new Actions(driver);	
		
		log.info("Excel Read Input is : "+Input);
		String FormattedInput = Input.replaceAll(",|\\%", "");  //To Remove 'Comma' And 'Percentage' From Input.
		int given_Input_Length = FormattedInput.length();
		
		Boolean Flag = true;
		String default_Values = null;			
		
		int get_Input_Length = 0;			
		int loop_Count = 0;
		
		
		while(Flag){
			
			default_Values = Element.getAttribute("value").replaceAll(",|\\%", "").trim();
			log.info("Defaulted Value is : '"+default_Values+"'");
			
			get_Input_Length = default_Values.length();			
			//log.info("Excel_Input Length : "+given_Input_Length+" Vs UI-Field Value Length : " +get_Input_Length);
			
			if(default_Values.equals(FormattedInput)){
				log.info("'Excel_Input Value' : "+FormattedInput+" Vs 'UI-Field Value' : " +default_Values);
				log.info("Since, Both 'Excel_Input Value' AND 'UI-Field Value' is Same. So, Proceed to Next Field");
				Flag=false;
			}
			
			else{
				
				if(handleFields.PageLoad_CircleBar_Handle.is_PageLoaded_Check(driver)){
				
					action.moveToElement(Element)
					.click(Element)
					.keyDown(Keys.CONTROL)
					.sendKeys("a")
					.keyUp(Keys.CONTROL)
					.sendKeys(Keys.BACK_SPACE)
					.sendKeys(Element, FormattedInput)
					.build()
					.perform();
				}
			}
			
			
			loop_Count++;
			
			if(loop_Count==10){
				log.info("Tried 10 Times To Give the Input. But That Didn't Worked!");
				log.info("Please Seek Technical Assistance!!");
				log.info("Browser Will Be Closed!!");
				driver.quit();
				break;				
			}
		}
	}

}
