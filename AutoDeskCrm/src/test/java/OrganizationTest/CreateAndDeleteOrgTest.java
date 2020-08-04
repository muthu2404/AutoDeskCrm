package OrganizationTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import genericLib.FileLib;
import genericLib.WebDriverUtlis;

public class CreateAndDeleteOrgTest {

	@Test
	public void createAndDeleteOrg() throws IOException, InterruptedException
	{
	FileLib f = new FileLib();
	WebDriverUtlis wlib = new WebDriverUtlis();
	
	/* Read data from Properties*/
	String url = f.getDataFromPropery("url");
	String username = f.getDataFromPropery("username");
	String password = f.getDataFromPropery("password");
	String browser = f.getDataFromPropery("browser");
	/*read data from excel*/
	String OrgName = f.getDataFromExcel("Organization",1 , 2)+wlib.getRandomValue();
	String OrgType=f.getDataFromExcel("Organization", 1, 3);
	String OrgIndustry=f.getDataFromExcel("Organization", 1, 4);

	
	/*launch browser*/
	WebDriver driver = null;
	if(browser.equals("chrome"))
	{
		driver=new ChromeDriver();
	}
	else if (browser.equals("firefox")){
		driver= new FirefoxDriver();
	}
	
	wlib.waitForAllElementToLoad(driver);
	driver.get(url);
	
	
	/*login*/
	driver.findElement(By.name("user_name")).sendKeys(username);
	driver.findElement(By.name("user_password")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	/*navigate to Organization page*/
	driver.findElement(By.linkText("Organizations")).click();
	
	/*navigate to create new Organization page*/
	driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
	
	/*create Org*/
	driver.findElement(By.name("accountname")).sendKeys(OrgName);

	
	WebElement  type = driver.findElement(By.name("accounttype"));
    
    wlib.selectItemFromList(type, OrgType);
			
	WebElement  industry = driver.findElement(By.name("industry"));
    wlib.selectItemFromList(industry, OrgIndustry);
		
    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	/*go to Org page Search for Organization*/
    WebElement org = driver.findElement(By.linkText("Organizations"));
    wlib.waitAndClickElement(driver, org);
     driver.findElement(By.className("hdrLink")).click();
     driver.findElement(By.name("search_text")).sendKeys(OrgName);
     WebElement searchIn = driver.findElement(By.xpath("//div[@id='basicsearchcolumns_real']/select"));
     wlib.selectItemFromList(searchIn, f.getDataFromExcel("Organization", 1, 6));
     driver.findElement(By.xpath("//input[@value=' Search Now ']")).click(); 
     /*Select the organization and delete*/
     driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../td/input")).click();
     driver.findElement(By.xpath("//input[@value='Delete']")).click();
     /*Accept the alert*/
     wlib.acceptAlert(driver);
     /*go to organizations and validate */
     driver.findElement(By.xpath("//input[@value=' Search Now ']")).click();
     String ActMsg = driver.findElement(By.xpath("//span[contains(text(),'No Organization')]")).getText();
	 String ExpMsg = f.getDataFromExcel("Organization", 1, 7);
	 Assert.assertTrue(ActMsg.contains(ExpMsg));
	 
	/*logout*/
	 WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
     wlib.moveMousePointer(driver,wb);
		driver.findElement(By.linkText("Sign Out")).click();
	
	}
}
