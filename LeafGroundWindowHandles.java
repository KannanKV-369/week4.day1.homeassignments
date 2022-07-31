package week4.day1.homeassignments;

import java.time.Duration;
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

public class LeafGroundWindowHandles {
	/*
	  http://www.leafground.com/pages/Window.html -- WindowHandles Practice
	 */


	public static void main(String[] args) throws InterruptedException {
		//Call WebDriverManager for the brower driver
		WebDriverManager.chromedriver().setup();
		//Lauch the brower Chrome
		ChromeDriver driver = new ChromeDriver();
		//Load the URL
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Click button to open home page in New Window
		driver.findElement(By.xpath("//button[text()='Open Home Page']")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> lstWindowHandles = new ArrayList<String>(windowHandles);
		System.out.println("No. of Windows: "+lstWindowHandles.size());
		driver.switchTo().window(lstWindowHandles.get(1)).close();
		driver.switchTo().window(lstWindowHandles.get(0));
		String titleParentWindow = driver.getTitle();
		System.out.println(titleParentWindow);
		//Find the number of opened windows
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set<String> windowHandles2 = driver.getWindowHandles();
		List<String> lstWindowHandles2 = new ArrayList<String>(windowHandles2);
		System.out.println("Number of Opended Windows: "+lstWindowHandles2.size());
		for (int i = 1; i < lstWindowHandles2.size(); i++) {
			String string = lstWindowHandles2.get(i);
			driver.switchTo().window(string).close();
			
		}
		driver.switchTo().window(lstWindowHandles2.get(0));
		//driver.switchTo().defaultContent();
		String title = driver.getTitle();
		System.out.println(title);
		//Close all except this window
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set<String> windowHandles3 = driver.getWindowHandles();
		List<String> lstWindowHandles3 = new ArrayList<String>(windowHandles3);
		for (int i = 1; i < args.length; i++) {
			String string = lstWindowHandles3.get(i);
			driver.switchTo().window(string).close();
		}
		driver.switchTo().window(lstWindowHandles3.get(0));
		//driver.switchTo().defaultContent();
		String title2 = driver.getTitle();
		System.out.println(title2);
		//Wait for 2 new Windows to open
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Thread.sleep(5000);
		Set<String> windowHandles4 = driver.getWindowHandles();
		List<String> lstWindowHandles4 = new ArrayList<String>(windowHandles4);
		System.out.println("Now Number of windows opened: "+lstWindowHandles4.size());
		driver.quit();		
		
	}

}
