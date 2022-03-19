package pages;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import utility.PropOperation;

public class LoginPage extends PredefinedActions{
	private PropOperation propOperation;
	
	public LoginPage(){
		propOperation = new PropOperation("./src/config/LoginPageLocators.properties");
	}
	
	public DashboardPage login(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickOnLoginButton();
		return new DashboardPage();
	}
	
	public boolean isLogoDisplayed() {
		String logoLocator = propOperation.getValue("logo");
		WebElement logo = getElement(logoLocator, false);
		//WebElement logo = driver.findElement(By.xpath("//div[contains(@class,'organization-logo')]/child::img"));
		return logo.isDisplayed();
	}
	
	public boolean isLoginPanelDisplayed() {
		String loginPanelLocator = propOperation.getValue("loginPanel");
		
		WebElement loginPanel = getElement(loginPanelLocator, false);
		//WebElement loginPanel = driver.findElement(By.xpath("//div[@id='divLogin']/div[position()=2]/child::div"));
		return loginPanel.isDisplayed();
	}
	
	public boolean isUsernameEnabled() {
		String usernameLocator = propOperation.getValue("username");
		WebElement username = getElement(usernameLocator, false);
		//WebElement username = driver.findElement(By.xpath("//input[@id='txtUsername' and @name = 'txtUsername']"));
		return username.isEnabled();
	}

	public void enterUsername(String username) {
		String usernameLocator = propOperation.getValue("username");
		WebElement element = getElement(usernameLocator, false);
		//WebElement element = driver.findElement(By.xpath("//input[@id='txtUsername' and @name = 'txtUsername']"));
		element.sendKeys(username);
	}
	
	/*private WebElement getPasswordElement() {
		return getElement("[XPATH]:-//input[starts-with(@id, 'txtPassword')]", false);
	}*/
	
	public boolean isPasswordEnabled() {
		String passwordLocator = propOperation.getValue("password");
		WebElement element = getElement(passwordLocator, false);
		return element.isEnabled();
	}
	
	public void enterPassword(String password) {
		String passwordLocator = propOperation.getValue("password");
		WebElement element = getElement(passwordLocator, false);
		element.sendKeys(password);
	}
	
	public void clickOnLoginButton() {
		String loginbtnLocator = propOperation.getValue("loginbtn");
		WebElement element = getElement(loginbtnLocator, false);
		element.click();
	}
}
