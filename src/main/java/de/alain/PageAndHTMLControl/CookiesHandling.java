package de.alain.PageAndHTMLControl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookiesHandling extends Base {

	public CookiesHandling(WebDriver driver) {

		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyButtons']//a[contains(text(),'Akzeptieren » ')]")
	private WebElement cookiesButton;
	@FindBy(xpath = "//div[@id='CybotCookiebotDialogBodyButtons']//a[contains(text(),'Akzeptieren » ')]")
	private WebElement cookiesButtonExist;

	// cookiesButtoneingeben
	public void cookiesButton() {

		click(cookiesButton, 10);
	}

	// cookiesButton Existenz prüfen
	public String cookiesButtonExistPrüfen() {
		System.out.println(selectgetText(cookiesButtonExist, 10));
		return selectgetText(cookiesButtonExist, 10);
	}

	// Cookies Handling
	public void IscookiesButtonExistAcceptButtonDrücken() {
		new WebDriverWait(driver, 3);

		if (cookiesButtonExistPrüfen().equals("Akzeptieren »")) {
			System.out.println("Cookies existiert");
			cookiesButton();
			System.out.println("Cookies wurde azeptiert");
		} else {
			System.out.println("Cookies existiert nicht");
		}
	}
}
