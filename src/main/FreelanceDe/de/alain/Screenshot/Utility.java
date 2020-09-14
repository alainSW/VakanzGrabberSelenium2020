package de.alain.Screenshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility {

	public static String getScreenshot(WebDriver driver, String screenshotName) {
		// TakesScreenshot: Gibt einen Treiber an, der einen Screenshot erfassen und auf
		// unterschiedliche Weise speichern kann.
		TakesScreenshot ts = (TakesScreenshot) driver;

		// getScreenshotAs: Erfassen Sie den Screenshot, und speichern Sie ihn am
		// angegebenen Speicherort.

		File src = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/Screenshot/" + screenshotName + "_"
				+ System.currentTimeMillis() + ".png";

		File destination = new File(path);

		try {
			FileUtils.copyFile(src, destination);

		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;

	}

}
