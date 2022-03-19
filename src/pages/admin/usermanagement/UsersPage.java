package pages.admin.usermanagement;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import base.PredefinedActions;
import constant.ConstantPath;
import utility.PropOperation;

public class UsersPage extends PredefinedActions {
	PropOperation propOperation;
	
	public UsersPage() {
		propOperation = new PropOperation(ConstantPath.USERSPAGELOCATOR);
	}
	
	public void waitUntilPageLoad() {
		String rowsLocator = propOperation.getValue("allRows");
		waitUntilNumberOfElementsToBeMoreThan(rowsLocator, 0);
	}
	
	public int getRowsPerPageValue() {
		String paginationLocator = propOperation.getValue("pagination");
		String currentPaginationValue = "";
		try {
			currentPaginationValue = getElementText(paginationLocator, false);
			return Integer.parseInt(currentPaginationValue);
		}catch(NoSuchElementException ne) {
			 paginationLocator = propOperation.getValue("defaultPagination");
			 currentPaginationValue = getElementAttribute(paginationLocator, "value", false);
			 return Integer.parseInt(currentPaginationValue);
		}
	}
	
	public int getRowsCountInCurrentPage() {
		String rowsLocator = propOperation.getValue("allRows");
		List<WebElement> allRows = getAllElements(rowsLocator, false);
		return allRows.size();
	}
}
