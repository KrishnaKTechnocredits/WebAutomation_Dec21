package testscripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.DashboardPage.MenuOptions;
import pages.admin.usermanagement.UsersPage;

public class UsersTest extends TestBase{
	
	@Test
	public void verfiyDefaultPagination() {
		System.out.println("STEP - Navigate to Admin -> User Manager -> Users");
		DashboardPage dashboardPage = new DashboardPage();
		dashboardPage.goto_menu(MenuOptions.USERS);
		
		System.out.println("Wait until Users page is loaded completly");
		UsersPage usersPage = new UsersPage();
		usersPage.waitUntilPageLoad();
		
		System.out.println("STEP - Get value of Pagination dropdown");
		int rowsPerPage = usersPage.getRowsPerPageValue();
		System.out.println("VERIFY - Default value of pagination is 50");
		Assert.assertEquals(rowsPerPage, 50);
		
		System.out.println("STEP - Get row count of current page");
		int totalRows = usersPage.getRowsCountInCurrentPage();
		
		System.out.println("VERIFY - Total Rows In Current Page should less or equal than selected dropdown value");
		Assert.assertTrue(totalRows<=rowsPerPage, 
				"total rows is not as expected, totalRows = " + totalRows + " and rows per page " + rowsPerPage);
	}
	
}
