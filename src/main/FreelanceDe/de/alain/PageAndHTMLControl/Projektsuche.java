/* Ersteller: Alain Samen Wnagueu
 * Datum: 20.07.2020
 * letzte Update: 
 * 
 * Projektsuche:
 * 
 * Diese Klasse erm�glicht uns auf die Seite Freelance.de einen Suchbegriff einzugeben und die Suche zu starten .
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

public class Projektsuche extends Base {

	// Seite Projekt-Freelance
	@FindBy(xpath = "//a[contains(@class,'header-logo')]//img")
	private WebElement ProjektFreelancePage;

	// ProjektFinden eingeben
	@FindBy(xpath = "//input[@placeholder='Projekte finden']")
	private WebElement ProjektFinden;
	@FindBy(xpath = "//div[@class='input-group']//input[@placeholder='Projekte finden']")
	private WebElement ProjektFindenEingabeFelderExist;

	// Projektsuche Button
	@FindBy(xpath = "//section[1]//div[1]//article[1]//div[1]//form[1]//div[1]//span[1]//button[1]")
	private WebElement ProjektSucheButton;
	@FindBy(xpath = "//section[1]//div[1]//article[1]//div[1]//form[1]//div[1]//span[1]//button[1]")
	private WebElement ProjektSucheButtonExist;

	// ProjektMaske navigieren
	@FindBy(xpath = "//span[@class='far fa-angle-right']")
	private WebElement ProjektMaskeNavigation;

	// Anzahl von Projekten
	@FindBy(xpath = "//div[@class='pull-left']//div[@id='pagination']")
	// @FindBy(xpath = "//body/div/div/div/div/div/div/div/div/p[1]")
	// body/div/div/div/div/div/div/div/div/p[1]
	private static WebElement AnzahlGefundenProjekten;

	// List der Projekte pro Maske wird hier ausgeliefert
	@FindBy(xpath = "//div[@class='project-list']")
	private WebElement ProjektlisteProMaske;

	// ProjektErgebnisse
	@FindBy(xpath = "//input[@id='__search_freetext']")
	private WebElement ProjektErgebnisse;

	public Projektsuche(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Freelance Seite
	public void clickFreelanceDe() {
		click(ProjektFreelancePage, 10);

	}

	// Projekt eingeben
	public void typeProjekt(String inputText, ExtentTest logger) {
		logger.log(Status.INFO, "Es wird der Suchbegriff " + inputText + " im Eingabefeld <Projekt finden> eingeben");
		type(ProjektFinden, 10, inputText);
	}

	// Projekt finden Eingabefelder Existenz pr�fen
	public String projektFindenEingabeFelderExist(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob das Eingabefeld: <Projektfinden> existiert.");
		return selectgetAttributByPlaceholder(ProjektFindenEingabeFelderExist, 10);
	}

	// Projektsuche dr�cken
	public void clickSuche(ExtentTest logger) {
		logger.log(Status.INFO, "Der Suchbutton wrd gedrueckt.");
		click(ProjektSucheButton, 10);
	}

	// Projekt Suche Button Existenz pr�fen
	public String projektSucheButtonExist(ExtentTest logger) {
		logger.log(Status.INFO, "Es wird geprueft, ob der Button: <Suche> existiert.");
		return selectgetText(ProjektSucheButtonExist, 10);
	}

	// ProjektMaske navigieren
	public void clickPageProjekt() {
		click(ProjektMaskeNavigation, 10);
	}

	// AnzahlProjekte Global �bersicht
	public static String anzahlProjekt() {
		// System.out.println("anzahlProjekt :" + selectgetText(AnzahlGefundenProjekten,
		// 10));
		return selectgetText(AnzahlGefundenProjekten, 10);
	}

	// List der Projekte pro Maske

	public String projektliste() {
		// System.out.println("selectgetText(ProjektlisteProMaske, 10) 1 \n :" +
		// selectgetText(ProjektlisteProMaske, 10));
		return selectgetAttributById(ProjektlisteProMaske, 10);
	}

	// ProProjektErgebnissejekt
	public String projektErgebnisse() {
		// System.out.println(selectgetAttributByValue(ProjektErgebnisse, 10));
		return selectgetAttributByValue(ProjektErgebnisse, 10);
	}

	// Pr�fe Eingabenfelder zum Projekt suchen und Button suche existiert

	public void isProjektFindenEingabenfelderExistAndprojektSucheButtonExist(ExtentTest logger) {
		new WebDriverWait(driver, 3);

		if (projektFindenEingabeFelderExist(logger).equals("Projekte finden")
				&& projektSucheButtonExist(logger).equals("SUCHE")) {

			System.out.println("Eingabefelder: Projekt finden und Suche Button existiert");

		} else {
			System.out.println("Eingabefelder: Projekt finden und Suche Button existiert nicht");
			logger.log(Status.FAIL, "Eingabefelder: Projekt finden und Suche Button existiert nicht");
		}

	}

}
