package base;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import constant.ConstantPath;
import customexception.InvalidLocatorType;

public class PredefinedActions {
	protected static WebDriver driver;
	protected static WebDriverWait wait;

	static public void start() {
		start("http://automationdec21-trials7401.orangehrmlive.com/");
	}
	
	static public void start(String url) {
		System.out.println("STEP - Launch Chrome Browser");
		System.setProperty(ConstantPath.CHROMEDRIVERKEY, ConstantPath.CHROMEDRIVERVALUE);
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, ConstantPath.WAITTIMEOUT);

		System.out.println("STEP - Browser Maximized");
		driver.manage().window().maximize();
		System.out.println("STEP - Load URL in browser");
		driver.get(url);
	}
	
	/*static public void start(String url, String browser) {
		System.out.println("STEP - Launch Chrome Browser");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 30);
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("STEP - Browser Maximized");
		driver.manage().window().maximize();
		System.out.println("STEP - Load URL in browser");
		driver.get("http://automationdec21-trials7401.orangehrmlive.com/");
	}*/

	// xpath:-//div[contains(@class,'organization-logo')]/child::img
	// orglogo=[id]:-organization-logo

	/*protected WebElement getElement(By by, boolean isWaitRequired) {
		WebElement element;
		if (isWaitRequired)
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		else
			element = driver.findElement(by);

		return null;
	}*/

	protected WebElement getElement(String locator, boolean isWaitRequired) {
		WebElement element = null;
		By by = getLocatorBy(locator);
		if (isWaitRequired)
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		else
			element = driver.findElement(by);

		return element;
	}
	
	protected boolean waitForvisibilityOfElement(String locator) {
		By by = getLocatorBy(locator);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		}catch(Exception e) {
			return false;
		}
	}
	
	protected boolean inVisibilityOfElement(String locator) {
		By by = getLocatorBy(locator);
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}
	

	protected Alert waitUntilAlertToBePresent(String locator) {
		By by = getLocatorBy(locator);
		return wait.until(ExpectedConditions.alertIsPresent());
	}
	
	protected boolean waitUntilPageTitleToBe(String title) {
		boolean titleFlag = wait.until(ExpectedConditions.titleIs(title));
		return titleFlag;
	}

	protected boolean isElementDisplayed(String locator, boolean isWaitRequired) {
		try {
			WebElement element = getElement(locator, isWaitRequired);
			if(element.isDisplayed()) {
				return true;
			}else {
				//TODO : call a method ScrollToElement
				return element.isDisplayed();
			}
		}catch(NoSuchElementException ne) {
			return false;
		}
		
	}
	
	protected List<WebElement> waitUntilNumberOfElementsToBeMoreThan(String locator, int count) {
		List<WebElement> widgetList;
		By by = getLocatorBy(locator);
		widgetList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, count));
		return widgetList;
	}
	
	private By getLocatorBy(String locator) {
		String locators[] = locator.split("]:-"); // [id ... organization-logo
		String locatorType = locators[0].replace("[", "").toUpperCase(); // id
		String locatorValue = locators[1];
		
		switch(locatorType) {
			case "XPATH" : 
				return By.xpath(locatorValue);
			
			case "ID" :
				return By.id(locatorValue);
				
			case "NAME" :
				return By.name(locatorValue);
			
			case "CSS":
				return By.cssSelector(locatorValue);
				
			default:
				throw new InvalidLocatorType(
						locatorType + " is not valid, please select locatortype from ID, XPATH, NAME, CSS, CLASSNAME");	
		}
	}
	
	public String getElementText(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		return element.getText();
	}
	
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	public String getElementAttribute(String locator, String attribute, boolean isWaitRequied) {
		WebElement element = getElement(locator, isWaitRequied);
		String attrValue = element.getAttribute(attribute);
		return attrValue;
	}
	
	public String getElementAttribute(WebElement element, String attribute) {
		String attrValue = element.getAttribute(attribute);
		return attrValue;
	}
	
	public void setText(String locator, boolean isWaitRequired, String text) {
		WebElement element = getElement(locator, isWaitRequired);
		if(element.isEnabled()) {
			element.clear();
			element.sendKeys(text);
		}else {
			System.out.println(locator + " element is not enabled");
		}	
	}
	
	public boolean waitUntilElementIsClickable(WebElement element) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		}catch(Exception e) {
			return false;
		}
	}

	public void clickOnElement(String locator, boolean isWaitRequired) {
		WebElement element = getElement(locator, isWaitRequired);
		if(waitUntilElementIsClickable(element)) {
			element.click();
		}else {
			throw new ElementNotInteractableException(locator + " is not clickable");
		}
	}
	
	protected List<WebElement> getAllElements(String locator, boolean isWaitRequired){
		By by = getLocatorBy(locator);
		return driver.findElements(by);
	}
	
	protected List<String> getAllElementsText(List<WebElement> listOfElement){
		List<String> listOfElementText = new ArrayList<String>();
		for(WebElement element : listOfElement) {
			listOfElementText.add(element.getText());
		}
		return listOfElementText;
	}
	
	public static void closeBrower() {
		driver.close();
	}

	public static void quitBrowser() {
		driver.quit();
	}
}
