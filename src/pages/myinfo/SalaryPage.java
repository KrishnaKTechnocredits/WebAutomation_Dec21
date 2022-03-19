package pages.myinfo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.PredefinedActions;

public class SalaryPage extends PredefinedActions{

	public String getCostToTheCompany() {
		WebElement ctc = wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[contains(@class,'summary-cards-container')]/div[1]/div[2][contains(text(),'$')]")));
		String ctcAmount = ctc.getText();
		return ctcAmount;
	}
}
