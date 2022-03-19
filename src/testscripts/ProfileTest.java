package testscripts;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;
import pages.ProfilePage;

public class ProfileTest {

	@BeforeMethod
	public void setUp() {
		PredefinedActions.start();
		LoginPage loginPage = new LoginPage();
		loginPage.login("admin", "rqC8@zPF5A");
	}
	
	@Test
	public void companyInfoTest() {
		System.out.println("STEP : User click on arrow");
		ProfilePage profilePage = new ProfilePage();
		profilePage.clickOnUserDropdown();
		
		System.out.println("STEP - get menu list");
		List<WebElement> menuList = profilePage.getMenuList();

		System.out.println("VERIFY - 3 options get displayed");
		Assert.assertEquals(menuList.size(), 3);

		System.out.println("STEP - Click on About element");
		profilePage.clickOnAbout();
		
		System.out.println("Verify - Verified company name display");
		Assert.assertTrue(profilePage.isCompanyNameDisplayed(), "Company field is not displayed on popup");
		
		System.out.println("VERIFY - version is displayed");
		Assert.assertTrue(profilePage.isVersionDisplayed(), "Version label was not displayed");

		System.out.println("VERIFY - Verified employee count label is displayed");
		Assert.assertTrue(profilePage.isEmployeeLabelDisplayed());

		System.out.println("STEP - get employee count from employee label");
		int empCount = profilePage.getEmployeeCountFromEmplyeeLabel();

		System.out.println("VERIFY - Employee count is non-zero");
		Assert.assertTrue(empCount > 0, "Employee count is zero");
	}

	@AfterMethod
	public void tearDown() {
		System.out.println("STEP - Cleanup");
		PredefinedActions.closeBrower();
	}
}
