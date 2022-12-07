package handleFields;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScrollToElement {
	
	// Declarations Of Log4j For This Class
	public static Logger log = Logger.getLogger(ScrollToElement.class.getName());

	public static void scrolltoGivenElement(WebDriver driver,WebElement Element){
		
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	/*
	 * scrollBy(X,Y). Here, 'X'-Horizontal Scroll, 'Y'-Vertical Scroll.
	 * For Scroll Down Value Would Be '+plus'
	 * For Scroll Up Value Would Be '-Minus'
	 */	
	
	//js.executeScript("window.scrollBy(0,400)");
	
	js.executeScript("arguments[0].scrollIntoView(true);",Element);
	
	log.info("Page Has Scrolled to Given Element!");
	
	}
}
