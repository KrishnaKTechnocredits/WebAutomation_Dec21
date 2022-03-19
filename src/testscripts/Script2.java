package testscripts;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Script2 {

	@Test
	public void testScript2() {
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
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='account-job']/descendant::i")))
				.click();
		System.out.println("STEP : User click on arrow");

		List<WebElement> menuList = driver.findElements(By.xpath("//ul[@id='user_menu']/descendant::a"));

		Assert.assertEquals(menuList.size(), 3);
		System.out.println("STEP : 3 options get display");

		driver.findElement(By.xpath("//ul[@id='user_menu']/descendant::a[text()='About']")).click();
		System.out.println("STEP : Click on About element");
		
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//node()[@id='frmSelectEmployees']")));
		WebElement company = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Company')]")));
		Assert.assertTrue(company.isDisplayed(), "Company field is not displayed on popup");
		System.out.println("STEP : Verified company name display");
		
		WebElement version = driver.findElement(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Version')]"));
		Assert.assertTrue(version.isDisplayed());
		System.out.println("STEP : Verified version display");

		WebElement employeeElement = driver
				.findElement(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Employees')]"));
		Assert.assertTrue(employeeElement.isDisplayed());
		System.out.println("STEP : Verified employee count display");

		String employeeCount = employeeElement.getText();
		employeeCount = employeeCount.trim().split(" ")[1];
		int empCount = Integer.parseInt(employeeCount);

		Assert.assertTrue(empCount > 0);
		System.out.println("STEP : Verified Employee count are non-zero");
		
		driver.quit();
		System.out.println("STEP : Browser closed");

	}

}
