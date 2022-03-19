package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;
import pages.LoginPage;

public class LoginTest {

	@Test
	public void loginTest() {
		PredefinedActions.start();

		LoginPage loginPage = new LoginPage();

		System.out.println("VERIFY - logo is displayed on login page");
		Assert.assertTrue(loginPage.isLogoDisplayed(), "Logo is not displayed");
		System.out.println("VERIFY - login panel is displayed on login page");
		Assert.assertTrue(loginPage.isLoginPanelDisplayed(), "Login Panel is not displayed");
		System.out.println("VERIFY - username is displayed on login page");
		Assert.assertTrue(loginPage.isUsernameEnabled(), "Username is not enabled");

		System.out.println("STEP - User Enters the UserName as Admin");
		loginPage.enterUsername("admin");

		System.out.println("VERFIY -password is enabled");
		Assert.assertTrue(loginPage.isPasswordEnabled());

		System.out.println("STEP - User Enters the Password");
		loginPage.enterPassword("rqC8@zPF5A");

		System.out.println("STEP - User click on Login button");
		loginPage.clickOnLoginButton();

		System.out.println("VERFIY - Verified Title");
		DashboardPage dashboardPage = new DashboardPage();
		Assert.assertTrue(dashboardPage.waitForDashboardPageLoad(), "Title Mis-match");
	}
}
