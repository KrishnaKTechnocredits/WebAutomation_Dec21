package pages.myinfo;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.PredefinedActions;

public class MyInfoPage extends PredefinedActions {

	public MyInfoPage clickOnMyInfo() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//node()[starts-with(text(),'My Inf')]")))
				.click();
		return this;
	}
	
	public MyInfoPage waitUntilMyInfoPageIsLoaded() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='firstName']")));	
		return this;
	}
	
	public void goto_PersonalDetailsTab() {
		
	}
	
	public JobPage goto_JobTab() {
		driver.findElement(By.xpath("//a[text()='Job']")).click();
		JobPage jobPage = new JobPage();
		return jobPage;
	}
	
	public SalaryPage goto_SalaryTab() {
		driver.findElement(By.partialLinkText("Salary")).click();
		return new SalaryPage();
	}
}
