package extentReports2;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import library.Reusability;

public class VerifyPageTitle {

	ExtentReports report;
	ExtentTest logger;
	WebDriver driver;

	@Test
	public void VerifyTitle() throws Exception {

		report = new ExtentReports("D:\\SeleniumCompleteClass\\SeleniumExtentReports4\\Screenshots\\Extent.html");

		logger = report.startTest("Varify Page Title Using Assert");

		System.setProperty("webdriver.chrome.driver",
				"D:\\Selenium\\Selenium Browsers Jars\\Chrome\\Chrome 84\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.login.hiox.com/login?referrer=easycalculation.com");
		driver.manage().window().maximize();

		logger.log(LogStatus.INFO, "Browser Launch Sucessfully");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.id("log_email")).sendKeys("9740673180");
		logger.log(LogStatus.INFO, "Enter User In EditBox");

		driver.findElement(By.id("log_password")).sendKeys("raghubn@123");
		logger.log(LogStatus.INFO, "Enter Password in Editbox");

		driver.findElement(By.xpath("//input[@name='log_submit']")).click();
		logger.log(LogStatus.INFO, "Click on Sign in Button");

		Thread.sleep(5000);
		Assert.assertTrue(driver.getTitle().contains("Free Online Math Calculator and Converter123"),
				"User Not able to login Sucessfully - Invalid Credentails");

		System.out.println("User Able to login Sucessfully - Valid Credentails");

		logger.log(LogStatus.PASS, "User Sucessfully Login Application");

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotpath = Reusability.capturedScreenShot(driver, result.getName());
			String image = logger.addScreenCapture(screenShotpath);
			logger.log(LogStatus.FAIL, image);
		}
          report.endTest(logger);
          report.flush();
          
          driver.get("D:\\SeleniumCompleteClass\\ExtentReports2\\TestReport\\ExtentReport.html");
	}
}
