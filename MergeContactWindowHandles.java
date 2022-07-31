package week4.day1.homeassignments;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContactWindowHandles {
	/*
	 * //Pseudo Code
	 * 1. Launch URL "http://leaftaps.com/opentaps/control/login"
	 * 2. Enter UserName and Password Using Id Locator
	 * 3. Click on Login Button using Class Locator
	 * 4. Click on CRM/SFA Link
	 * 5. Click on contacts Button
	 * 6. Click on Merge Contacts using Xpath Locator
	 * 7. Click on Widget of From Contact
	 * 8. Click on First Resulting Contact
	 * 9. Click on Widget of To Contact
	 * 10. Click on Second Resulting Contact
	 * 11. Click on Merge button using Xpath Locator
	 * 12. Accept the Alert
	 * 13. Verify the title of the page
	 */


	public static void main(String[] args) throws InterruptedException {
		//Call WebDriverManager for the brower driver
		WebDriverManager.chromedriver().setup();
		//Lauch the brower Chrome
		ChromeDriver driver = new ChromeDriver();
		//Load the URL
		driver.get("http://leaftaps.com/opentaps/control/login");
		//Maximize the browser
		driver.manage().window().maximize();
		//Enter First Name
		driver.findElement(By.id("username")).sendKeys("demosalesmanager");
		//Enter Password
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		//Click on Login Button
		driver.findElement(By.className("decorativeSubmit")).click();
		//Click on CRM/SFA Link
		driver.findElement(By.linkText("CRM/SFA")).click();
		//Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();
		//Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		Thread.sleep(1000);
		//Click on Widget of From Contact
		driver.findElement(By.xpath("(//span[text()='From Contact']/following::input/following::img[(@src=\"/images/fieldlookup.gif\")])[1]")).click();
		//Click on First Resulting Contact
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(windowHandles);
		String secondWindow = lstWindowHandles.get(1);
		System.out.println("Second Window name: "+secondWindow);
		driver.switchTo().window(secondWindow);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='DemoCustomer']")).click();
		//driver.switchTo().defaultContent();
		driver.switchTo().window(lstWindowHandles.get(0));
		//Click on Widget of To Contact
		driver.findElement(By.xpath("//span[text()='To Contact']/following::input[1]/following::img[@src='/images/fieldlookup.gif']")).click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> lstWindowHandles2 = new ArrayList<String>(windowHandles2);
		driver.switchTo().window(lstWindowHandles2.get(1));
		//Click on Second Resulting Contact
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='DemoLBCust']")).click();
		driver.switchTo().window(lstWindowHandles2.get(0));
		//driver.switchTo().defaultContent();
		//Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		//Verify the title of the page
		Thread.sleep(3000);
		String title = driver.getTitle();
		System.out.println("Title of the page: "+ title);		
		driver.quit();

	}

}
