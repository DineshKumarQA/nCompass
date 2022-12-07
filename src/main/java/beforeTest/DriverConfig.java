package beforeTest;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverConfig {

	// Declarations Of Log4j For This Class
	public static Logger log = Logger.getLogger(DriverConfig.class.getName());

	public static WebDriver driver;
	public static WebDriverWait wait_E; // WAIT -> Ends With '_E' Mean 'Explicit Wait'.
	public static Wait<WebDriver> wait_F; // WAIT -> Ends With '_F' Mean 'Fluent Wait'.

	static String Browser;

	public static WebDriver driverSet() {

		log.info("*************** <Driver Configuration Initiated> ***************");

		/** Browser Value From PageObjectModel **/
		Browser = frameWorks.dataDrivenModel.Test_Info.TestBrowser_I;
		log.info("The Browser Mentioned in Excel is '" + Browser + "'");

		if (Browser.isEmpty()) {
			log.error("The Browser Value is Empty. Please Seek Technical Assistance!!");
			log.error("Test Will Not Be Continued, Due to Empty 'Browser' Data!");
		}

		// Check For Various Browsers
		else if (Browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (Browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (Browser.equalsIgnoreCase("Internet Explorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			log.fatal("The Browser Value Not Properly Loaded. Please Seek Technical Assistance!!");
		}

		log.info("The Configured Browser is '" + Browser + "'");
		log.info("--------------- <Driver Configuration Completed> ---------------");
		return driver;

	}

	@SuppressWarnings("deprecation")
	public static WebDriverWait driverWait() {
		log.info("*************** <'Explicit' Driver Wait Configuration Initiated> ***************");
		
		/** Initialize 'Explicit' wait **/
		wait_E = new WebDriverWait(driver, 20);

		log.info("--------------- <'Explicit' Driver Wait Configuration Completed> ---------------");
		return wait_E;

	}

	public static Wait<WebDriver> driverWait_F() {
		log.info("*************** <'Fluent' Driver Wait Configuration Initiated> ***************");
		
		/** Initialize 'Fluent' wait **/
		Wait<WebDriver> wait_F = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(5))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		log.info("--------------- <'Fluent' Driver Wait Configuration Completed> ---------------");
		return wait_F;

	}

}
