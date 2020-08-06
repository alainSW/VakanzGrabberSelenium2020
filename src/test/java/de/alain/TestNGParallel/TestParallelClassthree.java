package de.alain.TestNGParallel;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import de.alain.CreateExcelFile.ExportProjektInformationenToExcel;
import de.alain.CreateExcelFile.ImportAnmeldeDatenVonExcelToJava;
import de.alain.PageAndHTMLControl.CookiesHandling;
import de.alain.PageAndHTMLControl.Login;
import de.alain.PageAndHTMLControl.Projektmerkmale;
import de.alain.PageAndHTMLControl.Projektsuche;
import de.alain.ProjektStatitikUndInformationen.ProjektStatistik;
import de.alain.ProjektmaskeNavigationUndProjektmerkmaleHolen.ProjektMaskeNavigationUndProjektMerkmaleHolen;

public class TestParallelClassthree {

	private WebDriver driver;
	public String pricejeansBlues;

	private Login login;

	private Projektsuche projektsuche;

	private Projektmerkmale projektmerkmale;

	private CookiesHandling cookiesHandling;

	ProjektStatistik projektStatistik;

	ExportProjektInformationenToExcel exportProjektInformationenToExcel;

	ProjektMaskeNavigationUndProjektMerkmaleHolen projektMaskeNavigationUndProjektMerkmaleHolen;

	ImportAnmeldeDatenVonExcelToJava importAnmeldeDatenVonExcelToJava;
	StackTraceElement[] stackTrace;

	@BeforeMethod
	public void beforeMethod() {

		if (driver == null) {

			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Executeable\\geckodriver.exe");
			driver = new FirefoxDriver();
			// driver.manage().deleteAllCookies();
			// Thread.sleep(7000);

			login = new Login(driver);
			projektsuche = new Projektsuche(driver);
			projektmerkmale = new Projektmerkmale(driver);
			cookiesHandling = new CookiesHandling(driver);

			driver.manage().window().maximize();
		}
	}

	@DataProvider
	public Object[][] dataProviderMethod1() throws IOException {
		return new Object[][] { { ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[0] },
				{ ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[1] } };
	}

	@Test
	public void test_1(String data) throws InterruptedException, IOException {
		stackTrace = new Throwable().getStackTrace();

		System.out.println(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]);
		ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel();

		// Aktion: Browser-> Web Seite anklicken
		login.visit("https://www.freelance.de");
		Thread.sleep(3000);

		// Cookies Handling
		cookiesHandling.IscookiesButtonExistAcceptButtonDrücken();

		// Aktion: Login
		login.isEingeloggtOrNot(); // Es wird geprüft, ob man bereit eingeloggt? Wenn Ja, dann wird man ausgeloggt.
		login.loginButton();
		login.isLoginMaskeExist(); // Es wird geprüft, ob die Maske-Login existiert
		login.typeMail(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]); // User-Name//E-Mail eingeben
		login.typePasswort(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[1]);// Passwort eingeben
		login.anmelden(); // Anmeldebutton drücken

		// Es wird geprüft, ob man korrekt eingeloggt ist?
		assertTrue(login.isEmailVisible());

		// Freelance.de Maske: Projekt finden
		projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
		projektsuche.clickFreelanceDe();
		projektsuche.typeProjekt(data);// leiter // Testautomatisierer
		projektsuche.clickSuche();

		// Maske navigieren und Projektinformationen aussortieren
		ProjektMaskeNavigationUndProjektMerkmaleHolen.ProjektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
				driver, projektmerkmale, data);

		// Projektinformationen in der Datenbank übertragen
		ExportProjektInformationenToExcel.TextMethodeName(
				projektsuche.projektErgebnisse() + "_" + stackTrace[0].getMethodName(), data,
				driver.getCurrentUrl().split("https://www.")[1].split("/")[0].toString());
		ExportProjektInformationenToExcel.Excel();
	}

	@DataProvider
	public Object[][] dataProviderMethod2() throws IOException {
		return new Object[][] { { ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[2] },
				{ ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[3] } };
	}

	@Test
	public void test_2(String data) throws InterruptedException, IOException {
		stackTrace = new Throwable().getStackTrace();

		System.out.println(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]);
		ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel();

		// Aktion: Browser-> Web Seite anklicken
		login.visit("https://www.freelance.de");
		Thread.sleep(3000);

		// Cookies Handling
		cookiesHandling.IscookiesButtonExistAcceptButtonDrücken();

		// Aktion: Login
		login.isEingeloggtOrNot(); // Es wird geprüft, ob man bereit eingeloggt? Wenn Ja, dann wird man ausgeloggt.
		login.loginButton();
		login.isLoginMaskeExist(); // Es wird geprüft, ob die Maske-Login existiert
		login.typeMail(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]); // User-Name//E-Mail eingeben
		login.typePasswort(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[1]);// Passwort eingeben
		login.anmelden(); // Anmeldebutton drücken

		// Es wird geprüft, ob man korrekt eingeloggt ist?
		assertTrue(login.isEmailVisible());

		// Freelance.de Maske: Projekt finden
		projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
		projektsuche.clickFreelanceDe();
		projektsuche.typeProjekt(data);// leiter // Testautomatisierer
		projektsuche.clickSuche();

		// Maske navigieren und Projektinformationen aussortieren
		ProjektMaskeNavigationUndProjektMerkmaleHolen.ProjektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
				driver, projektmerkmale, data);

		// Projektinformationen in der Datenbank übertragen
		ExportProjektInformationenToExcel.TextMethodeName(
				projektsuche.projektErgebnisse() + "_" + stackTrace[0].getMethodName(), data,
				driver.getCurrentUrl().split("https://www.")[1].split("/")[0].toString());
		ExportProjektInformationenToExcel.Excel();
	}

	@AfterMethod
	public void afterMethod() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
