package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import constant.ConstantPath;
import utility.PropOperation;

public class DashboardPage extends PredefinedActions {
	
	private PropOperation propOperation; 
	
	public DashboardPage() {
		propOperation = new PropOperation(ConstantPath.DASHBOARDPAGELOCATOR);
	}
	
	
	public boolean waitForDashboardPageLoad() {
		boolean titleFlag = waitUntilPageTitleToBe("Dashboard");
		return titleFlag;
	}
	
	public List<WebElement> getWidgets(){
		return waitUntilNumberOfElementsToBeMoreThan(propOperation.getValue("allWidgets"),1);
		/*String allWidgetsLocator = propOperation.getValue("allWidgets");
		List<WebElement> widgetList = waitUntilNumberOfElementsToBeMoreThan(allWidgetsLocator,1);
		return widgetList;*/
	}
	
	public List<String> getWidgetsText(){
		List<WebElement> widgetList = getWidgets();
		List<String> widgetTextList = getAllElementsText(widgetList);
		return widgetTextList;
	}
	
	public String getWidgetText(int index) {
		return getWidgets().get(index).getText();
	}
	
	public boolean isWidgetDisplayed(String widgetHeader) {
		String widgetLocator = propOperation.getValue("widgetByText");
		widgetLocator = String.format(widgetLocator, widgetHeader);
		return isElementDisplayed(widgetLocator, false);
		/*return driver
			   .findElement(By.xpath("//div[@class='widget-header']/span[2][text()='" + widgetHeader + "']"))
			   .isDisplayed();*/
	}
	
	public enum MenuOptions{
		ADMIN("Admin"),
		PIM("PIM"),
		MYINFO("My Info"),
		LEAVE("Leave"),
		CUSTOMEFIELDS("PIM->Configuration->Custom Fields"),
		USERS("Admin->User Management->Users"),
		SKILLS("Admin->Qualifications->Skills");
		
		public final String value;
		
		private MenuOptions(String value) {
			this.value = value;
		}
	}
	
	public void goto_menu(MenuOptions menuOption) {
		String menuPath = menuOption.value.toString();
		String[] arr = menuPath.split("->");
		for(String currentMenu : arr) {
			String menuLocator = propOperation.getValue("MenuByText");
			menuLocator = String.format(menuLocator,currentMenu);
			clickOnElement(menuLocator, true);
		}
	}
	/*public void goto_Menu(MenuOptions menu) {
		String menuLocator = propOperation.getValue("MenuByText");
		menuLocator = String.format(menuLocator, menu.value.toString());
		clickOnElement(menuLocator, false);
		
		switch(menu.value.toString()) {
			
			case "Admin" :
				return new AdminPage();
				
			case "PIM":
				return new PIMPage();
				
			case "Leave" :
				return new LeavePage();
		}
	}
	*/
	/*public AdminPage goto_AdminPage() {
		String menuLocator = propOperation.getValue("MenuByText");
		menuLocator = String.format(menuLocator, "Admin");
		clickOnElement(menuLocator, false);
		return new AdminPage();
	}*/
	
	//"PIM->Configuration->Custom Fields" 
	//"Custom Fields"
	public void goto_menu(String menuPath) {
		String[] arr = menuPath.split("->");
		for(String currentMenu : arr) {
			String menuLocator = propOperation.getValue("MenuByText");
			menuLocator = String.format(menuLocator,currentMenu).trim();
			clickOnElement(menuLocator, true);
		}
	}
}
