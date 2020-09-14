package de.alain.TestCaseRecorderJunit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.alain.CreateExcelFile.ExportProjektInformationenToExcel;
import de.alain.CreateExcelFile.ImportAnmeldeDatenVonExcelToJava;
import de.alain.PageAndHTMLControl.CookiesHandling;
import de.alain.PageAndHTMLControl.Login;
import de.alain.PageAndHTMLControl.Projektmerkmale;
import de.alain.PageAndHTMLControl.Projektsuche;
import de.alain.ProjektStatitikUndInformationen.ProjektStatistik;
import de.alain.ProjektmaskeNavigierenUndProjektmerkmaleHolen.ProjektMaskeNavigationUndProjektMerkmaleHolen;

@Execution(ExecutionMode.CONCURRENT)

public class ReorderTestFall {

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

	@Before
	public void setUp() throws Exception {
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

	@Test
	public void test_1() throws InterruptedException, IOException {
		stackTrace = new Throwable().getStackTrace();

		System.out.println(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]);
		ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel();

		// Aktion: Browser-> Web Seite anklicken
		login.visit("https://www.freelance.de");
		Thread.sleep(3000);

		// Cookies Handling
		cookiesHandling.IscookiesButtonExistAcceptButtonDr�cken();

		// Aktion: Login
		login.isLoginButtonExist(); // Es wird gepr�ft, ob man bereit eingeloggt? Wenn Ja, dann wird man ausgeloggt.
		login.loginButtonDruecken();
		login.isLoginMaskeExist(); // Es wird gepr�ft, ob die Maske-Login existiert
		login.typeMail(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]); // User-Name//E-Mail eingeben
		login.typePasswort(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[1]);// Passwort eingeben
		login.anmelden(); // Anmeldebutton dr�cken

		// Es wird gepr�ft, ob man korrekt eingeloggt ist?
		assertTrue(login.isEmailVisible());

		// Freelance.de Maske: Projekt finden
		projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
		projektsuche.clickFreelanceDe();
		projektsuche.typeProjekt("msg");// leiter // Testautomatisierer
		projektsuche.clickSuche();

		// Maske navigieren und Projektinformationen aussortieren
		ProjektMaskeNavigationUndProjektMerkmaleHolen.ProjektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
				driver, projektmerkmale, "msg");

		// Projektinformationen in der Datenbank �bertragen
		ExportProjektInformationenToExcel.TextMethodeName(
				projektsuche.projektErgebnisse() + "_" + stackTrace[0].getMethodName(), "msg",
				driver.getTitle().toString());
		ExportProjektInformationenToExcel.Excel();
	}

	@Test
	public void test_2() throws InterruptedException, IOException {
		stackTrace = new Throwable().getStackTrace();

		System.out.println(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]);
		ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel();

		// Aktion: Browser-> Web Seite anklicken
		login.visit("https://www.freelance.de");
		Thread.sleep(3000);

		// Cookies Handling
		cookiesHandling.IscookiesButtonExistAcceptButtonDr�cken();

		// Aktion: Login
		login.isLoginButtonExist(); // Es wird gepr�ft, ob man bereit eingeloggt? Wenn Ja, dann wird man ausgeloggt.
		login.loginButtonDruecken();
		login.isLoginMaskeExist(); // Es wird gepr�ft, ob die Maske-Login existiert
		login.typeMail(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0]); // User-Name//E-Mail eingeben
		login.typePasswort(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[1]);// Passwort eingeben
		login.anmelden(); // Anmeldebutton dr�cken

		// Es wird gepr�ft, ob man korrekt eingeloggt ist?
		assertTrue(login.isEmailVisible());

		// Freelance.de Maske: Projekt finden
		projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
		projektsuche.clickFreelanceDe();
		projektsuche.typeProjekt("Testautomatisierer");// leiter // Testautomatisierer
		projektsuche.clickSuche();

		// Maske navigieren und Projektinformationen aussortieren
		ProjektMaskeNavigationUndProjektMerkmaleHolen.ProjektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
				driver, projektmerkmale, "Testautomatisierer");

		// Projektinformationen in der Datenbank �bertragen
		ExportProjektInformationenToExcel.TextMethodeName(
				projektsuche.projektErgebnisse() + "_" + stackTrace[0].getMethodName(), "Testautomatisierer",
				driver.getTitle().toString());
		ExportProjektInformationenToExcel.Excel();
	}

	@After
	public void tearDown() throws Exception {
		Thread.sleep(3000);
		driver.quit();
	}

}
