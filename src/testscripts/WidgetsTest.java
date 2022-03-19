package testscripts;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;
import pages.LoginPage;

public class WidgetsTest {

	private List<String> getExpectedWidgetList(){
		List<String> expectedWidgetTextList = new ArrayList<String>();
		expectedWidgetTextList.add("Quick Access");
		expectedWidgetTextList.add("Employee Job Details");
		expectedWidgetTextList.add("Buzz Latest Posts");
		expectedWidgetTextList.add("My Actions");
		expectedWidgetTextList.add("Headcount by Location");
		expectedWidgetTextList.add("Employees on Leave Today");
		expectedWidgetTextList.add("Time At Work");
		expectedWidgetTextList.add("Performance Quick Feedback");
		expectedWidgetTextList.add("Latest Documents");
		expectedWidgetTextList.add("Latest News");
		expectedWidgetTextList.add("Covid - 19 Vaccination");
		return expectedWidgetTextList;
	}
	
	@Test
	public void widgetsTest() {
		
		PredefinedActions.start();
		LoginPage loginPage = new LoginPage();
		DashboardPage dashboardPage = loginPage.login("admin", "rqC8@zPF5A");
		
		System.out.println("VERFIY - Verified Title");
		Assert.assertTrue(dashboardPage.waitForDashboardPageLoad(), "Title Mis-match");

		List<String> expectedWidgetTextList = getExpectedWidgetList();
		
		System.out.println("STEP - getList of all widgets");
		List<WebElement> actualwidgetList = dashboardPage.getWidgets();
		
		System.out.println("VERIFY - " + expectedWidgetTextList.size() + " widget on dashboard is loaded");
		Assert.assertEquals(actualwidgetList.size() - 1, expectedWidgetTextList.size());
		
		System.out.println("STEP - getList of text of all widgets");
		List<String> actualWidgetTextList = dashboardPage.getWidgetsText();
		actualWidgetTextList.remove(actualwidgetList.size()-1);
		
		System.out.println("VERIFY - all widget text is as expected");
		Assert.assertEquals(actualWidgetTextList, expectedWidgetTextList);
		
		System.out.println("STEP - Browser closed");
		PredefinedActions.quitBrowser();
	}

}

