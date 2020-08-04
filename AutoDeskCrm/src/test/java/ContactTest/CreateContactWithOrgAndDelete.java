package ContactTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import genericLib.FileLib;
import genericLib.WebDriverUtlis;

public class CreateContactWithOrgAndDelete {
	@Test
	public void createContactWithOrgAndDelete() throws IOException, InterruptedException
	{
		FileLib f = new FileLib();
		WebDriverUtlis wlib = new WebDriverUtlis();
		
		/* Read data from Properties*/
		String url = f.getDataFromPropery("url");
		String username = f.getDataFromPropery("username");
		String password = f.getDataFromPropery("password");
		String browser = f.getDataFromPropery("browser");
		/*read data from excel*/
		String OrgName = f.getDataFromExcel("Contact",1 , 4)+wlib.getRandomValue();
		String SearchIn = f.getDataFromExcel("Contact", 1, 6);
		String lastname = f.getDataFromExcel("Contact", 1, 3);
		String expMsg= f.getDataFromExcel("Contact", 1, 7);
		
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
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		
		 /*navigate to Contact page*/
		 
		WebElement contact1 = driver.findElement(By.linkText("Contacts"));
		wlib.waitAndClickElement(driver, contact1);
		
		/*navigate to create new Contact page*/
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		wlib.switchToWindow(driver, "specific_contact_account_address");
		driver.findElement(By.id("search_txt")).sendKeys(OrgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.linkText(OrgName)).click();
		 driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		 /*go to conatct and delete*/
			WebElement contact = driver.findElement(By.linkText("Contacts"));
		 wlib.waitAndClickElement(driver, contact);
		 driver.findElement(By.name("search_text")).sendKeys(lastname);
		 WebElement searchIn = driver.findElement(By.id("bas_searchfield"));
		 wlib.selectItemFromList(searchIn, "Last Name");
		 driver.findElement(By.xpath("//input[@value=' Search Now ']")).click();
		 driver.findElement(By.xpath("//a[text()='"+lastname+"']/../../td/input")).click();
	     driver.findElement(By.xpath("//input[@value='Delete']")).click();
	     wlib.acceptAlert(driver);
	     
	     /*go to org and search the org and delete*/
			driver.findElement(By.linkText("Organizations")).click();
			 driver.findElement(By.className("hdrLink")).click();
		     driver.findElement(By.name("search_text")).sendKeys(OrgName);
		     WebElement searchInOrg = driver.findElement(By.xpath("//div[@id='basicsearchcolumns_real']/select"));
		     wlib.selectItemFromList(searchInOrg, OrgName);
		     driver.findElement(By.xpath("//input[@value=' Search Now ']")).click(); 
		     /*Select the organization and delete*/
		     driver.findElement(By.xpath("//a[text()='"+OrgName+"']/../../td/input")).click();
		     driver.findElement(By.xpath("//input[@value='Delete']")).click();
		     wlib.acceptAlert(driver);
		     
		     /*validate the deletion*/
		     String ActMsg = driver.findElement(By.xpath("//span[contains(text(),'No Organization')]")).getText();
			
			 Assert.assertTrue(ActMsg.contains(expMsg));



	}
	
	
}
