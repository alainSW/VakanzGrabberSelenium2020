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

package de.alain.PageAndHTMLControl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import de.alain.BaseClass.Base;

public class Login extends Base {

	@FindBy(xpath = "//input[@id='username']")
	private WebElement email;
	@FindBy(xpath = "//body/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/label[1]")
	private WebElement emailEingabeFelderExist;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passwort;
	@FindBy(xpath = "//div[@class= 'form-group  required']//label[@for='password'][contains(text(), 'Passwort')]")
	private WebElement passwortEingabeFelderExist;

	@FindBy(xpath = "//input[@id='remember']")
	private WebElement eingeloggenBleiben;
	@FindBy(xpath = "//body/div[3]/div[2]/div[1]/div[1]/div[1]/div[2]/form[1]/div[5]/div[1]/div[1]/span[1]")
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
	public void typeMail(String inputText, ExtentTest logger) {

		if (!getLoginText().equals("Login")) {
			System.out.println("typeMail Not Exist");
			logger.log(Status.FATAL,
					"Der Login-Maske wird nicht angezeigt, deshalb kann es kein Email angegeben werden");
			return;
		} else {
			logger.log(Status.INFO,
					"Es wird die Email-Adresse " + inputText + " im Eingabefeld <Nutzername/E-Mail> eingeben");
			type(email, 10, inputText);
		}

	}

	// EmailEingabeFelder Existenz pr�fen
	public String EmailEingabeFelderExistenzPr�fen(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob das Eingabefeld: <Nutzername/E-Mail> existiert.");
		return selectgetText(emailEingabeFelderExist, 10);
	}

	// Passwort eingeben
	public void typePasswort(String inputText, ExtentTest logger) {
		if (!getLoginText().equals("Login")) {
			System.out.println("typePasswort Not Exist");
			logger.log(Status.FATAL,
					"Der Login-Maske wird nicht angezeigt, deshalb kann es kein Passwort angegeben werden");
			return;
		} else {
			logger.log(Status.INFO, "Es wird das Passwort XXXX im Eingabefeld <Passwort> eingeben");
			type(passwort, 10, inputText);
		}

	}

	// PasswortEingabeFelder Existenz pr�fen
	public String PasswortEingabeFelderExistenzPr�fen(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob das Eingabefeld: <Passwort> existiert.");
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
	public String EingeloggenBleibenCheckboxExistenzPr�fen(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob das Checkbox: <Eingeloggt bleiben> existiert.");
		return selectgetText(eingeloggenBleibenCheckboxExist, 10);
	}

	// Anmeldebutton dr�cken
	public void anmelden(ExtentTest logger) {

		if (!getLoginText().equals("Login")) {
			logger.log(Status.FATAL,
					"Der Login-Maske wird nicht angezeigt, deshalb kann der Anmelde-Button nicht gedrueckt werden");
			return;
		} else {
			logger.log(Status.INFO, "Der Anmeldebutton wird angedrueckt.");
			click(anmelden, 10);
		}

	}

	// AnmeldeButton Existenz pr�fen
	public String AnmeldeButtonExistenzPr�fen(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob der Checkbox: <Anmelde> existiert.");
		return selectgetAttributByValue(anmeldeButtonExist, 10);
	}

	// Login-Button dr�cken
	public void loginButtonDruecken(ExtentTest logger) {

		logger.log(Status.INFO, "Es wird geprueft, ob der Login-Button exitiert");
		if (!getLoginText().equals("Login")) {
			logger.log(Status.FAIL, "Login-Button existiert nicht, deshalb kann es nicht gedrueckt werden");
			return;
		} else {
			logger.log(Status.INFO, "Login-Button wird gedrueckt");
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
	public void isLoginButtonExist(ExtentTest logger) {
		new WebDriverWait(driver, 5);
		logger.log(Status.INFO, "Es wird geprueft, ob der Login-Button exitiert");
		if (getLoginText().equals("Login")) {
			System.out.println("Login Exist!");
			logger.log(Status.PASS, "Der Login-Button exitiert");
			// logoutButton();
			return;
		} else {
			System.out.println("Kein Login Exist!");
			logger.log(Status.FAIL, "Der Login-Button exitiert nicht");
			return;
		}
	}

	// Es wird gepr�ft, ob die Maske Login geladen ist?
	public void isLoginMaskeExist(ExtentTest logger) {
		new WebDriverWait(driver, 10);

		if (EmailEingabeFelderExistenzPr�fen(logger).equals("NUTZERNAME/E-MAIL")
				&& PasswortEingabeFelderExistenzPr�fen(logger).equals("PASSWORT")
				&& EingeloggenBleibenCheckboxExistenzPr�fen(logger).equals("Eingeloggt bleiben")
				&& AnmeldeButtonExistenzPr�fen(logger).equals("Anmelden �")) {

			System.out.println("Login Maske existiert!");

		} else {

			System.out.println("Login Maske existiert nicht!");
			logger.log(Status.FAIL, "Loginmaske nicht existiert!");

		}

	}

}
