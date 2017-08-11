

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TS_VerifyHomeScreen {
	private static final Logger LOGGER = Logger
			.getLogger(TS_VerifyHomeScreen.class.getName());
	private AppiumDriver<WebElement> driver;
	private AppiumDriverLocalService appiumService;
	private ApplicationLibrary appLib;

	public TS_VerifyHomeScreen() {
		appLib = new ApplicationLibrary(driver);
	}

	@Test
	public void verifyHomeScreen() {

		appLib.delay(20000);
		appLib.waitForElementVisibility(By.id("com.disney.wdpro.dlr:id/pulldown_image"));
		Dimension size = driver.manage().window().getSize();

		int x = (int) (size.width * 0.50);
		int startY = (int) (size.height * 0.05);
		int endY = (int) (size.height * 0.75);
		
		driver.swipe(x, startY, x, endY, 2000);

		appLib.delay(1000);
		
		WebElement avatar = driver.findElement(By.id("com.disney.wdpro.dlr:id/img_avatar"));
		avatar.click();
		
		String homeScreenTitle = driver.findElement(By.id("section_text_title")).getText();

		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);

		List<WebElement> parkInfoSection = driver.findElements(By.id("text_view_cta_button"));
		WebElement todaysShowtimes = parkInfoSection.get(0);
		WebElement parkHours = parkInfoSection.get(1);
		WebElement myTickets = parkInfoSection.get(2);
		WebElement BuyTickets = parkInfoSection.get(3);

		/**
		 * Navigate to "Today's Show times" screen
		 */
		todaysShowtimes.click();

		// Verify screen title
		Assert.assertEquals("Park Hours & Info", driver.findElement(By.id("toolbar_title")).getText());
		
		List<WebElement> tabs= driver.findElements(By.className("android.support.v7.app.ActionBar$Tab"));

		WebElement todayTab = tabs.get(0);

		// Verify 'Today' is selected by default
		Assert.assertTrue(todayTab.isSelected(), "Today's Show times is not displayed");

		// Navigate to back to home screen
		driver.navigate().back();

		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);

		/**
		 * Navigate to "Park Hours" screen
		 */
		parkHours.click();

		// Verify screen title
		Assert.assertEquals("Park Hours & Info", driver.findElement(By.id("toolbar_title")).getText());

		WebElement parkHoursTab = tabs.get(1);

		// Verify 'Park Hours' is selected by default
		Assert.assertTrue(parkHoursTab.isSelected(), "Park Hours is not displayed");

		// Navigate to back to home screen
		driver.navigate().back();

		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);

		/**
		 * Navigate to "My Tickets" screen
		 */
		myTickets.click();

		// Verify screen title
		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Sign In to Your Account']")).isDisplayed(),"'Sign In to Your Account' screen is not displayed");
		
//		// adding Email and Password 
//		WebElement email = driver.findElement(By.id("txt_username"));
//		email.click();
//		email.clear();
//		email.sendKeys("goutham.test@test.com");
//	
//		WebElement password = driver.findElement(By.id("txt_password"));
//		password.click();
//		password.clear();
//		password.sendKeys("test123!");
//		
//		WebElement signin = driver.findElement(By.id("com.disney.wdpro.dlr:id/btn_login"));
//		signin.click();
//		driver.navigate().back();
//		
		
		// Navigate to back to home screen
		driver.navigate().back();

		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);

		/**
		 * Navigate to "Buy Tickets" screen
		 */
		BuyTickets.click();

		// Verify screen title
		Assert.assertTrue(driver.findElement(By.xpath("//android.widget.TextView[@text='Choose Your Tickets']")).isDisplayed(), "'Choose Your Tickets screen' is not displayed");
		
		// Purchasing a ticket 
		appLib.delay(2000);
		appLib.waitForElementVisibility(By.id("number_of_days_text_view"));
		List<WebElement> numberofdays = driver.findElements(By.id("number_of_days_text_view"));
		WebElement two = numberofdays.get(1);
		two.click();
//		WebElement two = driver.findElement(By.id("com.disney.wdpro.dlr:id/number_of_days_text_view"));
//		two.click();
		appLib.delay(1500);
		
		WebElement add = driver.findElement(By.id("com.disney.wdpro.dlr:id/plus_button"));
		add.click();
		appLib.delay(1000);
		
		Dimension size2 = driver.manage().window().getSize();

		int x1 = (int) (size2.width / 2);
		int startY1 = (int) (size2.height * 0.90);
		int endY1 = (int) (size2.height * 0.20);
		driver.swipe(x1, startY1, x1, endY1, 1000);

		WebElement oneparkperday = driver.findElement(By.id("com.disney.wdpro.dlr:id/price_information_section_front"));
		oneparkperday.click();
//		appLib.delay(1000);
		appLib.waitForElementVisibility(By.id("txt_username"));
		WebElement email = driver.findElement(By.id("txt_username"));
		email.click();
		appLib.delay(2000);
		email.sendKeys("goutham.test@test.com");
	
		WebElement password = driver.findElement(By.id("txt_password"));
		password.click();
		password.sendKeys("test123!");
		
		WebElement signin = driver.findElement(By.id("com.disney.wdpro.dlr:id/btn_login"));
		signin.click();
		
		appLib.delay(3000);
//		driver.swipe(x1, startY1, x1, endY, 1000);
		
//		List<WebElement> guestname = driver.findElements(By.xpath("//android.widget.EditText")).get(5);
//		WebElement assignname = guestname.get(4);
//		assignname.click();
		appLib.waitForElementVisibility(By.name("Name"));
		WebElement assignname = driver.findElement(By.name("Name"));
		assignname.click();
		assignname.sendKeys("Goutham");
		
		// Payment 
		driver.swipe(x1, startY1, x1, endY, 3000);
		driver.swipe(x1, startY1, x1, endY, 3000);
		driver.swipe(x1, startY1, x1, endY, 3000);
		driver.swipe(x1, startY1, x1, endY, 3000);
		driver.swipe(x1, startY1, x1, endY, 3000);
		driver.swipe(x1, startY1, x1, endY, 3000);
		appLib.delay(1000);
//		WebElement payment = driver.findElement(By.id("com.disney.wdpro.dlr:id/btn_add_credit_card"));
//		payment.click();
//		appLib.delay(1000);
//		
//		//		WebElement security = driver.findElement(By.id("com.disney.wdpro.dlr:id/et_security_code"));
//		security.click();
//		security.sendKeys("7240");
//		driver.swipe(x1, startY1, x1, endY, 2000);
//		
//		WebElement phone = driver.findElement(By.id("com.disney.wdpro.dlr:id/et_billing_phone"));
//		phone.click();
//		phone.sendKeys("2323434545");
//		
//		WebElement addbtn = driver.findElement(By.id("com.disney.wdpro.dlr:id/btn_save_payment"));
//		addbtn.click();
//		
		// Navigate to back to home screen
		driver.navigate().back();

		// Verify 'Park Info & Entry' screen is displayed
		Assert.assertEquals("Park Info & Entry", homeScreenTitle);
	}

	@BeforeTest
	private void appiumServerStart() throws MalformedURLException {
		appiumService = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder()
						.usingDriverExecutable(
								new File(
										"C:\\Program Files (x86)\\Appium\\node.exe"))
						.withAppiumJS(
								new File(
										"C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js")));
		// .withLogFile(new File("E:\\logs.txt")));
		if (appiumService.isRunning()) {
			System.out
					.println("Appium Server was running, hence Stoping existing server");
			appiumService.stop();
		}

		appiumService.start();
		System.out.println("Appium Server launched successfully");
		driver = new AndroidDriver<WebElement>(new URL(
				"http://127.0.0.1:4723/wd/hub"), createDesiredCapabilities());
		System.out.println(driver.getSessionId().toString());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	private DesiredCapabilities createDesiredCapabilities() {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("resetKeyboard", true);
		capabilities.setCapability("unicodeKeyboard", true);
		capabilities
				.setCapability("app",
						"C:\\Users\\koukg001\\Downloads\\Apk\\app_release_4.6_20170531.2_.apk");
		capabilities.setCapability("appPackage", "com.disney.wdpro.dlr");
		capabilities.setCapability("appActivity",
				"com.disney.wdpro.park.activities.SplashActivity");
		capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability("deviceName", "Google pixel");
		return capabilities;
	}

	@AfterTest
	public void teardown() {
		driver.quit();
		appiumService.stop();
		CommandLine command = new CommandLine("cmd");
		command.addArgument("/c");
		command.addArgument("Taskkill /F /IM node.exe");
	}
}
