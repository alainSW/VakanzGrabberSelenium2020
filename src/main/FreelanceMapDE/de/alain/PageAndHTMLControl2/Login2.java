/* Ersteller: Alain Samen Wnagueu
 * 
 * 
 * Beschreibung: Handling der Maske Login
 * 
 * Diese Klasse erm�glicht das Gesch�ftsprozesses der Login-Maske abzuwickeln.
 * 
 * Folgende Aktionen werden durchgef�hrt: 
 * 
 * - �berpr�fung der Existenz des Login-Buttons
 * - Login-Buttons dr�cken
 * - Logout-Buttons dr�cken
 * - �berpr�fung der Existenz des Email-Eingabefelders
 * - �berpr�fung der Existenz des Passwort-Eingabefelders
 * - �berpr�fung der Existenz des Checkbox eingeloggen Bleiben?
 * - �berpr�fung der Existenz des Anmelde-Buttons
 * - Email eingeben
 * - Passwort eingeben
 * - Passwort eingeben
 * - Checkbox eingeloggen Bleiben? abw�hlen
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
	private WebElement loginButton; // hier hei�t es Anmelden

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

	// EmailEingabeFelder Existenz pr�fen
	public String EmailEingabeFelderExistenzPr�fen() {
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

	// PasswortEingabeFelder Existenz pr�fen
	public String PasswortEingabeFelderExistenzPr�fen() {
		// System.out.println(selectgetText(passwortEingabeFelderExist, 10));
		return selectgetText(passwortEingabeFelderExist, 10);
	}

	// Checkbox: nicht eingeloggt bleiben ausw�hlen
	public void eingeloggtBleiben() {
		if (!getLoginText().equals("Login")) {
			System.out.println("eingeloggt Bleiben Checkbox Not Exist");
			return;
		} else {
			click(eingeloggenBleiben, 10);
		}

	}

	// eingeloggt bleiben Checkbox Existenz pr�fen
	public String EingeloggenBleibenCheckboxExistenzPr�fen() {
		// System.out.println(selectgetText(eingeloggenBleibenCheckboxExist, 10));
		return selectgetText(eingeloggenBleibenCheckboxExist, 10);
	}

	// Anmeldebutton dr�cken
	public void anmelden() {

		if (!getLoginText().equals("Login")) {
			return;
		} else {
			click(anmelden, 10);
		}

	}

	// AnmeldeButton Existenz pr�fen
	public String AnmeldeButtonExistenzPr�fen() {
		// System.out.println(selectgetAttributByValue(anmeldeButtonExist, 10));
		return selectgetAttributByValue(anmeldeButtonExist, 10);
	}

	// Login-Button dr�cken
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

	// Willkommen-Listenfeld dr�cken
	public void willkommenUser() {
		click(einloggenMessageVisible, 10);
	}

	// LogOut-Button dr�cken
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

	// Es wird gepr�ft, ob die Maske Login geladen ist?
	public void isLoginMaskeExist() {
		new WebDriverWait(driver, 3);

		if (EmailEingabeFelderExistenzPr�fen().equals("Benutzername oder E-Mail")
				&& PasswortEingabeFelderExistenzPr�fen().equals("Passwort")
				&& EingeloggenBleibenCheckboxExistenzPr�fen().equals("angemeldet bleiben")
				&& AnmeldeButtonExistenzPr�fen().equals("Anmelden bei freelancermap")) {

			System.out.println("Login Maske existiert nicht!");

		} else {

			System.out.println("Login Maske existiert!");

		}

	}

}
