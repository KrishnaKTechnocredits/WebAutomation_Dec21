package pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class ProfilePage extends PredefinedActions{
	
	public void clickOnUserDropdown() {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='account-job']/descendant::i")))
				.click();
	}
	
	public List<WebElement> getMenuList(){
		List<WebElement> menuList = driver.findElements(By.xpath("//ul[@id='user_menu']/descendant::a"));
		return menuList;
	}
	
	public void clickOnAbout() {
		driver.findElement(By.xpath("//ul[@id='user_menu']/descendant::a[text()='About']")).click();
	}
	
	public boolean isCompanyNameDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 10); // 500 ms
		try {
			WebElement company = wait.until(ExpectedConditions
					.visibilityOfElementLocated(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Company')]")));
			return true;
		}catch(TimeoutException | NoSuchElementException e) {
			return false;
		}
	}
	
	public boolean isVersionDisplayed() {
		
		/*WebElement version = driver.findElement(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Version')]"));
		return version.isDisplayed();*/
		
		try {
			WebElement version = driver.findElement(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Version')]"));
			return version.isDisplayed() ? true : false;
		}catch(TimeoutException | NoSuchElementException e) {
			return false;
		}
	}
	
	private WebElement getEmployeeLabelElement() {
		return driver
				.findElement(By.xpath("//div[@id='companyInfo']//p[contains(text(),'Employees')]"));
	}
	
	public boolean isEmployeeLabelDisplayed() {
		WebElement employeeElement = getEmployeeLabelElement();
		return employeeElement.isDisplayed();
	}
	
	public String getEmployeeLabelText() {
		WebElement employeeElement = getEmployeeLabelElement();
		return employeeElement.getText();		
	}
	
	public int getEmployeeCountFromEmplyeeLabel() {
		String empLabeltext = getEmployeeLabelText();
		empLabeltext = empLabeltext.trim().split(" ")[1];
		return Integer.parseInt(empLabeltext);
	}
}
