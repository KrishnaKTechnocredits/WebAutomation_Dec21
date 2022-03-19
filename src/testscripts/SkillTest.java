package testscripts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.PredefinedActions;
import pages.DashboardPage;
import pages.DashboardPage.MenuOptions;
import pages.LoginPage;
import pages.admin.qualifications.SkillsPage;
import utility.ExcelOperation;

public class SkillTest{
	private SkillsPage skillsPage;
	private List<String> listOfSkills = new ArrayList<String>();
	
	@BeforeClass
	public void setup()
	{
		PredefinedActions.start();
		LoginPage loginPage = new LoginPage();
		loginPage.login("admin", "rqC8@zPF5A");
		
		System.out.println("Step - Navigate to Admin->Qualifications->Skills");
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.goto_menu(MenuOptions.SKILLS);
		
		skillsPage = new SkillsPage();
		System.out.println("Step - Wait untill Table Loaded");
		skillsPage.waitUntilPageLoad();
	}
	
	
	@Test(dataProvider="skillNameDataProvier")
	public void addSkillTest(String skillName, boolean flag) {
		
		boolean isSkillPresentFlag = skillsPage.isSkillPresent(skillName);
		if(flag) {
			if(isSkillPresentFlag) {
				skillsPage.deleteSkill(skillName);
				isSkillPresentFlag = false;
			}
		}
		
		System.out.println("VERIFY - isSkilPresent is as expected");
		Assert.assertNotEquals(flag, isSkillPresentFlag);
		
		System.out.println("Step - click on Add skills (+)");
		skillsPage.clickOnAddSkillBtn();

		System.out.println("Step : Enter skill name ");
		skillsPage.enterSkillName(skillName);
		
		System.out.println("Step : Enter description name ");
		skillsPage.enterSkillDescription(skillName + " skill is must to have");
		
		
		if(isSkillPresentFlag) {
			boolean isErorrMessageDisplayed = skillsPage.isErrorMessageDisplayed();
			System.out.println("VERIFY - Error message is displayed");
			Assert.assertTrue(isErorrMessageDisplayed);
			skillsPage.clickOnCancel();
		}else {
			System.out.println("Step - click on Save button");
			skillsPage.clickOnSave();
			skillsPage.waitForVisibilityOfToastMessage();
			isSkillPresentFlag = skillsPage.isSkillPresent(skillName);
			System.out.println("VERIFY - Skill is added");
			Assert.assertTrue(isSkillPresentFlag);
			listOfSkills.add(skillName);
		}
	}
	
	@AfterClass
	public void deleteSkill() {
		for(String skillName : listOfSkills) {
			skillsPage.deleteSkill(skillName);
		}
	}
	
	@DataProvider(name="skillNameDataProvier")
	public Object[][] getSkillData() throws IOException{
		Object[][] data = ExcelOperation.getAllRows("skills.xlsx", "data");
		return data;
	}
}
