package de.alain.PageAndHTMLControl2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.alain.BaseClass.Base;

public class CookiesHandling2 extends Base {

	public CookiesHandling2(WebDriver driver) {

		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//a[@class='cc_btn cc_btn_accept_all']")
	private WebElement cookiesButton;
	@FindBy(xpath = "//a[@class='cc_btn cc_btn_accept_all']")
	private WebElement cookiesButtonExist;

	// cookiesButtoneingeben
	public void cookiesButton() {

		click(cookiesButton, 10);
	}

	// cookiesButton Existenz prüfen
	public String cookiesButtonExistPrüfen() {
		// System.out.println(selectgetText(cookiesButtonExist, 10));
		return selectgetText(cookiesButtonExist, 10);
	}

	// Cookies Handling
	public void IscookiesButtonExistAcceptButtonDrücken() {
		new WebDriverWait(driver, 3);

		if (cookiesButtonExistPrüfen().equals("Auswahl bestätigen")) {
			System.out.println("Cookies existiert");
			cookiesButton();
			System.out.println("Cookies wurde azeptiert");
		} else {
			System.out.println("Cookies existiert nicht");
		}
	}
}
