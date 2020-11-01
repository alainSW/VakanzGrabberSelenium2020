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

package de.alain.PageAndHTMLControl2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import de.alain.BaseClass.Base;

public class Login2 extends Base {

	@FindBy(xpath = "//input[@id='login']")
	private WebElement email;
	@FindBy(xpath = "//label[contains(text(),'Benutzername oder E-Mail')]")
	private WebElement emailEingabeFelderExist;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwort;
	@FindBy(xpath = "//label[contains(text(),'Passwort')]")
	private WebElement passwortEingabeFelderExist;

	@FindBy(xpath = "//input[@id='cookie']")
	private WebElement eingeloggenBleiben;
	@FindBy(xpath = "//div[@class='row']//label[1]")
	private WebElement eingeloggenBleibenCheckboxExist;

	@FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	private WebElement anmelden;
	@FindBy(xpath = "//button[@class='btn btn-primary btn-block']")
	private WebElement anmeldeButtonExist;

	@FindBy(xpath = "//div[@class='stickynav-tease hidden-sm hidden-xs']//a[1]")
	private WebElement loginButton; // hier heißt es Anmelden

	@FindBy(xpath = "//a[@class='logout']")
	private WebElement einloggenMessageVisible;

	@FindBy(xpath = "//a[@class='logout']")
	private WebElement logoutButton;

	// Webdriver und Seite initialisieren
	public Login2(WebDriver driver) {
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
		// System.out.println(selectgetText(emailEingabeFelderExist, 10));
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
		// System.out.println(selectgetText(passwortEingabeFelderExist, 10));
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
		// System.out.println(selectgetText(eingeloggenBleibenCheckboxExist, 10));
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
		// System.out.println(selectgetAttributByValue(anmeldeButtonExist, 10));
		return selectgetAttributByValue(anmeldeButtonExist, 10);
	}

	// Login-Button drücken
	public void loginButtonDruecken() {
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
	public void isLoginButtonExist() {
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

		if (EmailEingabeFelderExistenzPrüfen().equals("Benutzername oder E-Mail")
				&& PasswortEingabeFelderExistenzPrüfen().equals("Passwort")
				&& EingeloggenBleibenCheckboxExistenzPrüfen().equals("angemeldet bleiben")
				&& AnmeldeButtonExistenzPrüfen().equals("Anmelden bei freelancermap")) {

			System.out.println("Login Maske existiert nicht!");

		} else {

			System.out.println("Login Maske existiert!");

		}

	}

}
