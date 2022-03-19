package testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import base.PredefinedActions;
import pages.LoginPage;

public class TestBase {

	@BeforeMethod
	public void start() {
		PredefinedActions.start();
		LoginPage loginPage = new LoginPage();
		loginPage.login("admin", "rqC8@zPF5A");
		// Wait
	}
	
	
	@AfterMethod
	public void tearDown() {
		System.out.println("Cleanup - Browser close");
		PredefinedActions.closeBrower();
	}
}
