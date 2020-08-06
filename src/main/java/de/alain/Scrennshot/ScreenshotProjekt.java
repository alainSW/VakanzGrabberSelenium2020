package de.alain.Scrennshot;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import de.alain.PageAndHTMLControl.Base;

public class ScreenshotProjekt extends Base {
	private static File scrFile;

	public ScreenshotProjekt(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void ScreenshotExecute(String jobTitel) throws IOException {

		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		CheckFileExist(jobTitel);

		// FileUtils.copyFile(scrFile, new File("c:\\tmp\\" + jobTitel + ".png"));
	}

	public static void CheckFileExist(String jobTitel) throws IOException {

		File file = new File("c:\\tmp\\" + jobTitel + ".png");
		file.getParentFile().mkdirs();
		System.out.println("Created file: Test Test Test Test Test test test");
		if (file.createNewFile()) {
			System.out.println("Created file: Test Test Test Test Test test test 1");
			FileUtils.copyFile(scrFile, new File("c:\\tmp\\" + jobTitel + ".png"));
			// FileUtils.forceDelete(new File("c:\\tmp\\" + jobTitel + ".png"));

		} else {
			file.delete();
			// FileUtils.forceDelete(new File("c:\\tmp\\" + jobTitel + ".png"));
			FileUtils.copyFile(scrFile, new File("c:\\tmp\\" + jobTitel + ".png"));

		}

	}

}
