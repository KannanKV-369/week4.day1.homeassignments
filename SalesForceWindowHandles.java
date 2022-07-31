package week4.day1.homeassignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceWindowHandles {
	/*
	 Salesforce Customer service:
		1.Launch the browser
		2.Load the url as "https://login.salesforce.com/ "
		3.Enter the username as " ramkumar.ramaiah@testleaf.com "
		4. Enter the password as " Password$123 "
		5.click on the login button
		6.click on the learn more option in the Mobile publisher
		7.Switch to the next window using Windowhandles.
		8.click on the confirm button in the redirecting page
		9.Get the title
		10.Get back to the parent window
		11.close the browser

	 */


	public static void main(String[] args) throws InterruptedException {
		//Call WebDriverManager for the brower driver
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		//Lauch the brower Chrome
		ChromeDriver driver = new ChromeDriver();
		//Load the URL
		driver.get("https://login.salesforce.com/");
		//Maximize the browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Enter the username as " ramkumar.ramaiah@testleaf.com "
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		//Enter the password as " Password$123 "
		driver.findElement(By.id("password")).sendKeys("Password$123");
		//click on the login button
		driver.findElement(By.id("Login")).click();
		//click on the learn more option in the Mobile publisher
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		//Switch to the next window using Windowhandles.
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(windowHandles);
		String secondWindow = lstWindowHandles.get(1);
		driver.switchTo().window(secondWindow);
		//click on the confirm button in the redirecting page
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		//Get the title
		String title = driver.getTitle();
		System.out.println("Second Window Title: "+ title);
		//Get back to the parent window
		driver.switchTo().window(lstWindowHandles.get(0));
		//driver.switchTo().defaultContent();
		String parentWindowTitle = driver.getTitle();
		System.out.println("Parent Window Title: "+ parentWindowTitle);
		//close the browser
		driver.close();


	}

}
