package testscripts;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Script3 {

	@Test
	public void script3() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
//			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("STEP : Chrome Browser Launch");

		driver.manage().window().maximize();
		System.out.println("STEP : Browser Maximized");
		driver.get("http://automationdec21-trials7401.orangehrmlive.com/");
		System.out.println("STEP : URL loaded in browser");

		WebElement logo = driver.findElement(By.xpath("//div[contains(@class,'organization-logo')]/child::img"));

//			Assert.assertEquals(logo.isDisplayed(), true);
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

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//node()[starts-with(text(),'My Inf')]")))
				.click();
		System.out.println("STEP : Clicked on My Info Tab");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='firstName']")));
		System.out.println("STEP : Verify Personal details page loaded");

		driver.findElement(By.partialLinkText("Salary")).click();

		WebElement ctc = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'summary-cards-container')]/div[1]/div[2][contains(text(),'$')]")));
		String ctcAmount = ctc.getText();
		int amount = Integer.parseInt(ctcAmount.substring(1, ctcAmount.indexOf(".")).replace(",", ""));

		Assert.assertTrue(amount > 0);
		System.out.println("STEP : CTC is non-zero");

		driver.quit();
		System.out.println("STEP : Browser closed");
	}

}
