package de.alain.LoggerHandling;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

/* Ersteller: Alain Samen Wangueu
 * Datum: 03.09.2020
 * Version: 00
 * 
 * Kapital: ExtentReport
 * 
 * Diese Klasse beinhaltet die verschiedenen Methoden um einen ausführliche Report in Selenium mit TestNG zu erstellen.
 * 
 *  Step 1:  Fügen Sie die Abhängigkeit in Ihrer Maven-Datei "pom.xml" für Bereichsberichte hinzu. 
 *  
 *  dependency>
        <groupId>com.aventstack</groupId>
        <artifactId>extentreports</artifactId>
        <version>3.0.6</version>
    </dependency>
    
    
    Step 2: Konfigurieren Sie das maven surefire Plugin wie folgt in pom.xml 
    
    <build>
    <defaultGoal>clean test</defaultGoal>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.1</version>
            <configuration>
                <source>${jdk.level}</source>
                <target>${jdk.level}</target>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.19.1</version>
            <configuration>
                <suiteXmlFiles>
                    <suiteXmlFile>testng.xml</suiteXmlFile>
                </suiteXmlFiles>
            </configuration>
        </plugin>
    </plugins>
	</build>
 * 
 * Seite: https://mvnrepository.com/artifact/org.apache.maven.plugins/maven-compiler-plugin/3.8.1
 * 
 * 
 * 
 * 
 * */

public class ExtentLogging {

	@Test
	public static void getResultWithScreenshotByFailTest(ITestResult getMethodName, ExtentTest logger,
			String ScreenshotPath, WebDriver driver) throws IOException {
		// Diese Methode ergibt das finale Ergebnis jedes TestSteps
		if (getMethodName.getStatus() == ITestResult.FAILURE) {

			logger.fail(getMethodName.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromPath(ScreenshotPath).build());
			logger.log(Status.FAIL, MarkupHelper
					.createLabel(getMethodName.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
			logger.fail(getMethodName.getThrowable());
		} else if (getMethodName.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS,
					MarkupHelper.createLabel(getMethodName.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else if (getMethodName.getStatus() == ITestResult.SKIP) {
			logger.log(Status.SKIP,
					MarkupHelper.createLabel(getMethodName.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
		}

	}

}
