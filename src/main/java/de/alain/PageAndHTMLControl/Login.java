/* Ersteller: Alain Samen Wnagueu
 * 
 * 
 * Beschreibung: Handling der Maske Login
 * 
 * Diese Klasse ermöglicht das Geschäftsprozesses der Login-Maske abzuwickeln.
 * 
 * Folgende Aktionen werden durchgeführt: 
 * 
 * - Überprüfung der Existenz des Login-Buttons
 * - Login-Buttons drücken
 * - Logout-Buttons drücken
 * - Überprüfung der Existenz des Email-Eingabefelders
 * - Überprüfung der Existenz des Passwort-Eingabefelders
 * - Überprüfung der Existenz des Checkbox eingeloggen Bleiben?
 * - Überprüfung der Existenz des Anmelde-Buttons
 * - Email eingeben
 * - Passwort eingeben
 * - Passwort eingeben
 * - Checkbox eingeloggen Bleiben? abwählen
 *  
 *  
 * 
 * 
 */

package de.alain.PageAndHTMLControl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends Base {

	@FindBy(xpath = "//input[@id='username']")
	private WebElement email;
	@FindBy(xpath = "//div[@class='form-group  required']//label[contains(text(),'Nutzername/E-Mail')]")
	private WebElement emailEingabeFelderExist;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwort;
	@FindBy(xpath = "//div[@class='form-group  required']//label[contains(text(),'Passwort')]")
	private WebElement passwortEingabeFelderExist;

	@FindBy(xpath = "//input[@id='remember']")
	private WebElement eingeloggenBleiben;
	@FindBy(xpath = "//div[@class='col-sm-12  no-padding-left']//span[contains(@class,'checkbox-label')]")
	private WebElement eingeloggenBleibenCheckboxExist;

	@FindBy(xpath = "//input[@id='login']")
	private WebElement anmelden;
	@FindBy(xpath = "//input[@id='login']")
	private WebElement anmeldeButtonExist;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//a[@class='nav-top-link'][contains(text(),'Login')]")
	private WebElement loginButton;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//a[@class='visible-lg-block'][contains(text(),'Willkommen alains..')]")
	private WebElement einloggenMessageVisible;

	@FindBy(xpath = "//ul[@class='nav nav-pills hidden-xs']//li[@class='dropdown hidewhen768 active']//ul[@class='dropdown-menu']//li//a[@class='nav-top-link'][contains(text(),'Logout')]")
	private WebElement logoutButton;

	// Webdriver und Seite initialisieren
	public Login(WebDriver driver) {
		super(driver);
		// visit("https://www.freelance.de");
	}

	// Email-Adresse eingeben
	public void typeMail(String inputText) {

		if (!getLoginText().equals("Login")) {
			System.out.println("typeMail Not Exist");
			return;
		} else {
			type(email, 10, inputText);
		}

	}

	// EmailEingabeFelder Existenz prüfen
	public String EmailEingabeFelderExistenzPrüfen() {
		System.out.println(selectgetText(emailEingabeFelderExist, 10));
		return selectgetText(emailEingabeFelderExist, 10);
	}

	// Passwort eingeben
	public void typePasswort(String inputText) {
		if (!getLoginText().equals("Login")) {
			System.out.println("typePasswort Not Exist");
			return;
		} else {
			type(passwort, 10, inputText);
		}

	}

	// PasswortEingabeFelder Existenz prüfen
	public String PasswortEingabeFelderExistenzPrüfen() {
		System.out.println(selectgetText(passwortEingabeFelderExist, 10));
		return selectgetText(passwortEingabeFelderExist, 10);
	}

	// Checkbox: nicht eingeloggt bleiben auswählen
	public void eingeloggtBleiben() {
		if (!getLoginText().equals("Login")) {
			System.out.println("eingeloggt Bleiben Checkbox Not Exist");
			return;
		} else {
			click(eingeloggenBleiben, 10);
		}

	}

	// eingeloggt bleiben Checkbox Existenz prüfen
	public String EingeloggenBleibenCheckboxExistenzPrüfen() {
		System.out.println(selectgetText(eingeloggenBleibenCheckboxExist, 10));
		return selectgetText(eingeloggenBleibenCheckboxExist, 10);
	}

	// Anmeldebutton drücken
	public void anmelden() {

		if (!getLoginText().equals("Login")) {
			return;
		} else {
			click(anmelden, 10);
		}

	}

	// AnmeldeButton Existenz prüfen
	public String AnmeldeButtonExistenzPrüfen() {
		System.out.println(selectgetAttributByValue(anmeldeButtonExist, 10));
		return selectgetAttributByValue(anmeldeButtonExist, 10);
	}

	// Login-Button drücken
	public void loginButton() {
		if (!getLoginText().equals("Login")) {
			return;
		} else {
			click(loginButton, 10);
		}

	}

	// Textname: LoginButton entnehmen
	public String getLoginText() {

		return selectgetText(loginButton, 10);
	}

	// Ist man eingeloggt?
	public Boolean isEmailVisible() {
		return elementsIsVisible(einloggenMessageVisible, 10);
	}

	// Willkommen-Listenfeld drücken
	public void willkommenUser() {
		click(einloggenMessageVisible, 10);
	}

	// LogOut-Button drücken
	public void logoutButton() {
		click(logoutButton, 10);
	}

	// Ist man eingeloggt? Wenn Ja dann wird man ausgeloggt
	public void isEingeloggtOrNot() {
		new WebDriverWait(driver, 3);

		if (getLoginText().equals("Login")) {
			System.out.println("Login Exist!");
			// logoutButton();
			return;
		} else {
			System.out.println("Kein Login Exist!");
			return;
		}
	}

	// Es wird geprüft, ob die Maske Login geladen ist?
	public void isLoginMaskeExist() {
		new WebDriverWait(driver, 3);

		if (EmailEingabeFelderExistenzPrüfen().equals("NUTZERNAME/E-MAIL")
				&& PasswortEingabeFelderExistenzPrüfen().equals("PASSWORT")
				&& EingeloggenBleibenCheckboxExistenzPrüfen().equals("Eingeloggt bleiben")
				&& AnmeldeButtonExistenzPrüfen().equals("Anmelden »")) {

			System.out.println("Login Maske existiert nicht!");

		} else {

			System.out.println("Login Maske existiert!");

		}

	}

}
