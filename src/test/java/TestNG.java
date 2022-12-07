import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG {
	
	@BeforeSuite	
	public void bfrSuite(){
		System.out.println("The is Before Suite");
	}
	
	@BeforeTest
	public void bfrTest(){
		System.out.println("The is Before Test");
	}
	
	@BeforeClass
	public void bfrclass(){
		System.out.println("The is Before Class");
	}
	
	@BeforeMethod
	public void bfrmthd(){
		System.out.println("The is Before Method");
	}

	@Test
	public void test(){
		System.out.println("The is Test");
	}

	@AfterMethod
	public void afrmthd(){
		System.out.println("The is After Method");
	}
	
	@AfterClass
	public void afrcls(){
		System.out.println("The is After Class");
	}
	
	@AfterTest
	public void afrTest(){
		System.out.println("The is After Test");
	}
	
	@AfterSuite
	public void afrSuite(){
		System.out.println("The is After Suite");
	}

}
