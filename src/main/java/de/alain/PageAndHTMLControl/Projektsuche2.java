/* Ersteller: Alain Samen Wnagueu
 * Datum: 20.07.2020
 * letzte Update: 
 * 
 * Projektsuche:
 * 
 * Diese Klasse ermöglicht uns auf die Seite Freelance.de einen Suchbegriff einzugeben und die Suche zu starten .
 * 
 *
 * 
 */

package de.alain.PageAndHTMLControl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Projektsuche2 extends Base {

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
	@FindBy(xpath = "//span[@class='fa fa-angle-right']")
	private WebElement ProjektMaskeNavigation;

	// Anzahl von Projekten
	@FindBy(xpath = "//div[@class='pull-left']//div[@id='pagination']")
	// @FindBy(xpath = "//body/div/div/div/div/div/div/div/div/p[1]")
	// body/div/div/div/div/div/div/div/div/p[1]
	private static WebElement AnzahlGefundenProjekten;

	// Anzahl von Projekten
	@FindBy(xpath = "//div[@class='pull-left']//div[@id='pagination']")
	// @FindBy(xpath = "//body/div/div/div/div/div/div/div/div/p[1]")
	// body/div/div/div/div/div/div/div/div/p[1]
	private static WebElement AnzahlGefundenProjekten2;

	// List der Projekte pro Maske wird hier ausgeliefert
	@FindBy(xpath = "//div[@class='project-list']")
	private WebElement ProjektlisteProMaske;

	// ProjektErgebnisse
	@FindBy(xpath = "//input[@id='__search_freetext']")
	private WebElement ProjektErgebnisse;

	public Projektsuche2(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// Freelance Seite
	public void clickFreelanceDe() {
		click(ProjektFreelancePage, 10);

	}

	// Projekt eingeben
	public void typeProjekt(String inputText) {
		type(ProjektFinden, 10, inputText);
	}

	// Projekt finden Eingabefelder Existenz prüfen
	public String projektFindenEingabeFelderExist() {
		System.out.println(selectgetAttributByPlaceholder(ProjektFindenEingabeFelderExist, 10));
		return selectgetAttributByPlaceholder(ProjektFindenEingabeFelderExist, 10);
	}

	// Projektsuche drücken
	public void clickSuche() {
		click(ProjektSucheButton, 10);
	}

	// Projekt Suche Button Existenz prüfen
	public String projektSucheButtonExist() {
		System.out.println(selectgetText(ProjektSucheButtonExist, 10));
		return selectgetText(ProjektSucheButtonExist, 10);
	}

	// ProjektMaske navigieren
	public void clickPageProjekt() {
		click(ProjektMaskeNavigation, 10);
	}

	// AnzahlProjekte Global Übersicht
	public static String anzahlProjekt() {
		System.out.println("anzahlProjekt  :" + selectgetText(AnzahlGefundenProjekten, 10));
		return selectgetText(AnzahlGefundenProjekten, 10);
	}

	// AnzahlProjekte Global Übersicht
	public static String anzahlProjekt2() {
		System.out.println("anzahlProjekt  :" + selectgetText(AnzahlGefundenProjekten2, 10));
		return selectgetText(AnzahlGefundenProjekten2, 10);
	}

	// List der Projekte pro Maske

	public String projektliste() {
		return selectgetText(ProjektlisteProMaske, 10);
	}

	// ProProjektErgebnissejekt
	public String projektErgebnisse() {
		System.out.println(selectgetAttributByValue(ProjektErgebnisse, 10));
		return selectgetAttributByValue(ProjektErgebnisse, 10);
	}

}
