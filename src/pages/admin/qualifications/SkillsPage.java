package pages.admin.qualifications;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import constant.ConstantPath;
import utility.PropOperation;

public class SkillsPage extends PredefinedActions{
	
	private PropOperation propOperation;
	
	public SkillsPage() {
		propOperation = new PropOperation(ConstantPath.SKILLSPAGELOCATOR);
	}
	
	public SkillsPage waitUntilPageLoad() {
		getElement(propOperation.getValue("headerSkill"), true);
		return this;
	}
	
	public SkillsPage clickOnAddSkillBtn() {
		clickOnElement(propOperation.getValue("addSkillbtn"), true);
		return this;
	}
	
	public SkillsPage addSkill(String skillName, String skillDescription) {
		enterSkillName(skillName);
		enterSkillDescription(skillDescription);
		clickOnSave();
		return this;
	}
	
	public SkillsPage enterSkillName(String skillName) {
		setText(propOperation.getValue("addSkillName"), true, skillName);
		return this;
	}
	
	public SkillsPage enterSkillDescription(String skillDescription) {
		setText(propOperation.getValue("skillDescription"), false, skillDescription);
		return this;
	}
	
	public SkillsPage clickOnSave() {
		clickOnElement(propOperation.getValue("savebtn"), false);
		return this;
	}
	
	public SkillsPage clickOnCancel() {
		clickOnElement(propOperation.getValue("cancelbtn"), false);
		return this;
	}
	
	public boolean isErrorMessageDisplayed() {
		try {
			getElement(propOperation.getValue("errorMessage"), true);
			return true;
		}catch(NoSuchElementException ne) {
			return false;
		}
	}
	
	public boolean isSkillPresent(String skillName) {
		List<WebElement> listOfElement = getAllElements(propOperation.getValue("allSkillsName"), false);
		List<String> listOfElementText = getAllElementsText(listOfElement);
		return listOfElementText.contains(skillName);
	}
	
	public void waitForVisibilityOfToastMessage() {
		waitForvisibilityOfElement(propOperation.getValue("successToastMessage"));
	}
	
	public void deleteSkill(String skillName) {
		String skillCheckboxLocator = propOperation.getValue("skillCheckbox");
		skillCheckboxLocator = String.format(skillCheckboxLocator, skillName);
		clickOnElement(skillCheckboxLocator, false);
		clickOnElement(propOperation.getValue("optionsDropdown"), false);
		clickOnElement(propOperation.getValue("deletedSelected"),true);
		clickOnElement(propOperation.getValue("deleteSkillbtn"),true);
		waitForvisibilityOfElement(propOperation.getValue("deleteSkillToastMessage"));
	}
	
	public void deleteSkill(List<String> list) {
		
	}
	
}
