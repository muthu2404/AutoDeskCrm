package genericLib;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import objectvault.LoginPage;
import objectvault.LogoutPage;

public class Baseclass {

	public static WebDriver driver= null;
	public FileLib f = new FileLib();
	public WebDriverUtlis wlib = new WebDriverUtlis();
	
	
	@BeforeSuite
	public void Dbconnect()
	{
		System.out.println("db connected");
	}
	
	
	@org.testng.annotations.BeforeClass
	public void launchBrowser() throws IOException
	{
		System.out.println("Launch browser");
		String browser = f.getDataFromPropery("browser");
		System.out.println(browser);
				if(browser.equals("chrome"))
				{
					driver = new ChromeDriver();
				}
				else if (browser.equals("firefox"))
				{
					driver = new FirefoxDriver();
					
				}
	}
	
	@BeforeMethod
	public void login() throws IOException
	{
		System.out.println("login done");
		wlib.waitForAllElementToLoad(driver);
		driver.manage().window().maximize();
		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		lp.loginToAutodesk();
		
	}
	@AfterMethod
	public void logout()
	{
		LogoutPage lo = PageFactory.initElements(driver, LogoutPage.class);
		lo.signOut();
		
	}
	@AfterClass
	public void closeBrowser()
	{
		driver.quit();
	}
	@AfterSuite
	public void DbDisconnect()
	{
		System.out.println("db disconnect");
	}
	
}
