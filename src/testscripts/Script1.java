package testscripts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Script1 {

	@Test
	public void script1() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("STEP : Chrome Browser Launch");

		driver.manage().window().maximize();
		System.out.println("STEP : Browser Maximized");
		driver.get("http://automationdec21-trials7401.orangehrmlive.com/");
		System.out.println("STEP : URL loaded in browser");

		WebElement logo = driver.findElement(By.xpath("//div[contains(@class,'organization-logo')]/child::img"));

//		Assert.assertEquals(logo.isDisplayed(), true);
		Assert.assertTrue(logo.isDisplayed(), "Logo is not displayed");

		WebElement loginPanel = driver.findElement(By.xpath("//div[@id='divLogin']/div[position()=2]/child::div"));
		Assert.assertTrue(loginPanel.isDisplayed(), "Login Panel is not displayed");

		WebElement userName = driver.findElement(By.xpath("//input[@id='txtUsername' and @name = 'txtUsername']"));
		if (userName.isEnabled()) {
			userName.sendKeys("admin");
			System.out.println("STEP : User Enters the UserName as Admin");
		}

		WebElement password = driver.findElement(By.xpath("//input[starts-with(@id, 'txtPassword')]"));
		if (password.isEnabled()) {
			password.sendKeys("rqC8@zPF5A");
			System.out.println("STEP : User Enters the Password");
		}

		driver.findElement(By.xpath("//button[@type='submit']")).click();
		System.out.println("STEP : User click on Login button");

		WebDriverWait wait = new WebDriverWait(driver, 10);

		boolean titleFlag = wait.until(ExpectedConditions.titleIs("Dashboard"));
//		Assert.assertEquals(driver.getTitle(), "Dashboard");
		Assert.assertTrue(titleFlag, "Title Mis-match");

		System.out.println("STEP : Verified Title");

		List<WebElement> widgetList = wait.until(
				ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath("//div[@class='widget-header']/span[2]"), 1));
		
		//		List<WebElement> widgetList = driver.findElements(By.xpath("//div[@class='widget-header']/span[2]"));
		Assert.assertEquals(widgetList.size() - 1, 11);
		System.out.println("STEP : 11 widget on dashboard");

		for (int i = 0; i < widgetList.size() - 1; i++) {
			String widgetHeader = widgetList.get(i).getText();
			boolean isWidgetDisplay = driver
					.findElement(By.xpath("//div[@class='widget-header']/span[2][text()='" + widgetHeader + "']"))
					.isDisplayed();
			Assert.assertTrue(isWidgetDisplay, "Widget is not displayed...." + widgetHeader);
		}

		driver.quit();
		System.out.println("STEP : Browser closed");
	}

}

