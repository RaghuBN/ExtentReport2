package library;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Reusability {

	public static String capturedScreenShot(WebDriver driver, String screenShotName) {

		try {
			TakesScreenshot ts = (TakesScreenshot) driver;

			File source = ts.getScreenshotAs(OutputType.FILE);

			String dest = "D:\\SeleniumCompleteClass\\ExtentReports\\" + screenShotName + ".png";

			File destination = new File(dest);

			FileUtils.copyFile(source, destination);

			System.out.println("Captured ScreenShot Sucessfully");

			return dest;

		} catch (Exception e) {
			System.out.println("Exception While Taking Screen Shot" + e.getMessage());
			return e.getMessage();
		}

	}
}
