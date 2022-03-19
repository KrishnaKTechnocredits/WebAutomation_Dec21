package constant;

public class ConstantPath {	
	private static final String BASEDIR = "./src/config";
	public static final String LOGINPAGELOCATOR = BASEDIR + "/LoginPageLocators.properties";
	public static final String DASHBOARDPAGELOCATOR = BASEDIR + "/DashboardPageLocators.properties";  
	public static final String USERSPAGELOCATOR = BASEDIR + "/UsersPageLocators.properties";  
	public static final String SKILLSPAGELOCATOR = BASEDIR + "/SkillsPageLocators.properties";
	
	public static final int WAITTIMEOUT = 30;
	
	public static final String CHROMEDRIVERKEY = "webdriver.chrome.driver";
	public static final String CHROMEDRIVERVALUE = "./drivers/chromedriver.exe";
	public static final String TESTDATA = "./testdata";
}
