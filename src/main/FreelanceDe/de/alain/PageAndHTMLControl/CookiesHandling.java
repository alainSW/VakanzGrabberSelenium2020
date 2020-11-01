package de.alain.PageAndHTMLControl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import de.alain.BaseClass.Base;

public class CookiesHandling extends Base {

	public CookiesHandling(WebDriver driver) {

		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBody']//div[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelectionWrapper']//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection'][contains(text(),'Auswahl bestätige')]")
	private WebElement cookiesButton;
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBody']//div[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelectionWrapper']//a[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowallSelection'][contains(text(),'Auswahl bestätigen')]")
	private WebElement cookiesButtonExist;

	// cookiesButtoneingeben
	public void cookiesButton(ExtentTest logger) {
		logger.log(Status.INFO, "Cookies-Button wird gedrueckt");
		click(cookiesButton, 10);
	}

	// cookiesButton Existenz prüfen
	public String cookiesButtonExistPrüfen(ExtentTest logger) {
		// System.out.println(selectgetText(cookiesButtonExist, 10));

		logger.log(Status.INFO, "Es wird geprueft, ob der Cookies Button existiert");
		return selectgetText(cookiesButtonExist, 10);
	}

	// Cookies Handling
	public void IscookiesButtonExistAcceptButtonDrücken(ExtentTest logger) {
		new WebDriverWait(driver, 3);

		if (cookiesButtonExistPrüfen(logger).equals("Auswahl bestätigen")) {
			System.out.println("Cookies-Button existiert");
			logger.log(Status.PASS, "Cookies-Button existiert");
			cookiesButton(logger);
			System.out.println("Cookies-Button ist erolgreich betaetigt worden");
		} else {
			System.out.println("Cookies existiert nicht");
			logger.log(Status.INFO, "Cookies-Meldung existiert nicht");
		}
	}
}
