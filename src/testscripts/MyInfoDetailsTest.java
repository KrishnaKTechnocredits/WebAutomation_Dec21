package testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.LoginPage;
import pages.myinfo.MyInfoPage;
import pages.myinfo.SalaryPage;

public class MyInfoDetailsTest extends TestBase{
	
	
	@Test
	public void verifyCtcTest() {
		System.out.println("STEP - Clicked on My Info Tab");
		MyInfoPage myInfoPage = new MyInfoPage();
		myInfoPage.clickOnMyInfo();
		
		/*int ctcAmount = myInfoPage
			.clickOnMyInfo()
			.waitUntilMyInfoPageIsLoaded()
			.goto_SalaryTab()
			.getCostToTheCompany();*/
		
		System.out.println("Wait until MyInfo page loaded");
		myInfoPage.waitUntilMyInfoPageIsLoaded();
		
		/*System.out.println("STEP - navigate to salary tab and get cost to the company");
		int amount = myInfoPage
			.waitUntilMyInfoPageIsLoaded()
			.goto_SalaryTab()
			.getCostToTheCompany();*/
		
		System.out.println("STEP - navigate to salary tab");
		SalaryPage salaryPage = myInfoPage.goto_SalaryTab();
		
		System.out.println("STEP - get cost to the company from salary tab");
		String ctcAmount = salaryPage.getCostToTheCompany();
		int amount = Integer.parseInt(ctcAmount.substring(1, ctcAmount.indexOf(".")).replace(",", ""));

		System.out.println("VERIFY - CTC is non-zero");
		Assert.assertTrue(amount > 0);
	}

}
