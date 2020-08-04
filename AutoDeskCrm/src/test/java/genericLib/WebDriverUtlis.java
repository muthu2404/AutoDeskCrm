package genericLib;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * 
 * @author Muthu Manick
 *
 */
public class WebDriverUtlis {
	
	/**
	 *  
	 * @param element
	 * @param itemname
	 * This method is used to select the item from listbox
	 */
	public void selectItemFromList(WebElement element,String itemname)
	{
		Select s = new Select(element);
		s.selectByVisibleText(itemname);
	}
	/**
	 * 
	 * @param element
	 * This method is used to move mouse pointer to specific webelement
	 */
	public void moveMousePointer(WebDriver driver,WebElement element)
	{
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();
	}
	
	/**
	 * 
	 * @return random number
	 * This method is used to generate random number
	 */
	public  int getRandomValue()
	{
		Random r = new Random();
		int sno = r.nextInt(100);
		return sno;
		
	}
	
	/**
	 * 
	 * @param EnterWindowNumToSwitch
	 * This method is used to switch to specific window 
	 */
	public boolean switchToWindow(WebDriver driver,String exptitle)
	
	{
		boolean flag= false;
			Set<String> allids = driver.getWindowHandles();
			Iterator<String> it = allids.iterator();
			while(it.hasNext())
			{
				String currentWindowId = it.next();
				driver.switchTo().window(currentWindowId);
				String actTitle = driver.getTitle();
				if(actTitle.contains(exptitle))
				{
					flag=true;
					break;
				}
				
			}
			return flag;
			
	}
	/**
	 * 
	 * @param element
	 * this method is used to wait for the particular webelement
	 */
	public void waitForAnyElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForAllElementToLoad(WebDriver driver)
	
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/**
	 * this method is used to accept the alert message
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	/**
	 * This method is used to get the alert message
	 */
	public void getAlertMsg(WebDriver driver)
	{
		driver.switchTo().alert().getText();
	}
	
	public void waitAndClickElement(WebDriver driver, WebElement element) throws InterruptedException
	{
		int count=0;
		while(count<10)
		{
			try {
				element.click();
				break;
			}
			catch(Throwable t){
				Thread.sleep(1000);
				count++;
			}
		}
	}
	
	
}
