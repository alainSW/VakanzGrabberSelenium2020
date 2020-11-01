package de.alain.TestNGParallel;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import de.alain.CreateExcelFile.ExportProjektInformationenToExcel;
import de.alain.CreateExcelFile.ImportAnmeldeDatenVonExcelToJava;
import de.alain.LoggerHandling.ExtentLogging;
import de.alain.PageAndHTMLControl.CookiesHandling;
import de.alain.PageAndHTMLControl.Login;
import de.alain.PageAndHTMLControl.Projektmerkmale;
import de.alain.PageAndHTMLControl.Projektsuche;
import de.alain.ProjektStatitikUndInformationen.ProjektStatistik;
import de.alain.ProjektmaskeNavigierenUndProjektmerkmaleHolen.ProjektMaskeNavigationUndProjektMerkmaleHolen;
import de.alain.Screenshot.Utility;
import de.alain.Sqlite.SQLITEJDBC;

@Listeners({ de.alain.LoggerHandling.listenerTesting.class })
public class TestParallelClassOne {
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
	public String dataProviderParameter = "";

	private String FirstElementDataProvider = "";

	private String LastElementDataProvider = "";

	private String Webseite = "";

	public ExtentReports extent;
	public ExtentTest logger;

	// Sqlite 26.10.2020
	private SQLITEJDBC sqlite;

	@BeforeTest
	public void setup() {
		System.out.println("login to google");

		ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/learn_automation1.html");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Host Name", "LocaHost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Testautomatisierer");

	}

	@BeforeMethod
	public void beforeMethod() throws InterruptedException, IOException {
		if (driver == null) {

			System.setProperty("webdriver.firefox.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\Executeable\\geckodriver.exe");

			driver = new FirefoxDriver();

			/*
			 * System.setProperty("webdriver.chrone.driver", System.getProperty("user.dir")
			 * + "\\src\\test\\resources\\Executeable\\chromedriver.exe"); driver = new
			 * ChromeDriver(); // driver.manage().deleteAllCookies(); ChromeOptions options
			 * = new ChromeOptions(); options.addArguments("--start-maximized"); driver =
			 * new ChromeDriver(options);
			 **/
			Thread.sleep(1000);

			login = new Login(driver);
			projektsuche = new Projektsuche(driver);
			projektmerkmale = new Projektmerkmale(driver);
			cookiesHandling = new CookiesHandling(driver);

			// SQLITE-Datenbank Klasse instanzieren
			sqlite = new SQLITEJDBC();
			// vVerbindung zum Datanbank erstellen
			sqlite.SQLITEJDBCConnectToDatabase();
			// Tabelle erstellen
			sqlite.SQLITEJDBCCreateTable();
			sqlite.SQLITEJDBCDelete();

			driver.manage().window().maximize();

		}
	}

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() throws IOException {
		Webseite = ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[0];
		FirstElementDataProvider = ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[1];
		LastElementDataProvider = ImportAnmeldeDatenVonExcelToJava.getProjektsuchbegriffToExcel()[2];

		return new Object[][] { { FirstElementDataProvider }, { LastElementDataProvider } };
	}

	@Test(dataProvider = "data-provider")
	public void test_1(String data) throws InterruptedException, IOException {

		stackTrace = new Throwable().getStackTrace();

		logger = extent.createTest(stackTrace[0].getMethodName() + "_" + data);
		dataProviderParameter = data;

		if (dataProviderParameter.equals(FirstElementDataProvider)) {
			// Aktion: Browser-> Web Seite anklicken

			logger.log(Status.INFO, "Oeffnen der Webseite : " + Webseite);
			login.visit(Webseite);
			logger.log(Status.PASS, "WebSeite ist erfolgreich geoeffnen.");
			Thread.sleep(3000);

			// Cookies Handling
			logger.log(Status.INFO, "Cookies bestaetigen : ");
			cookiesHandling.IscookiesButtonExistAcceptButtonDrücken(logger);
			logger.log(Status.PASS, "Cookies ist erfolgreich betaetigt worden. ");

			// Aktion: Login
			logger.log(Status.INFO, "Login-Button: Existenz pruefen ");
			login.isLoginButtonExist(logger);
			logger.log(Status.PASS, "Login-Button existiert");

			logger.log(Status.INFO, "Login-Button: druecken");
			login.loginButtonDruecken(logger);
			logger.log(Status.PASS, "Login-Button ist erfolgreich gedrueckt worden. ");

			logger.log(Status.INFO, "Login-Maske: Existenz pruefen ");
			login.isLoginMaskeExist(logger); // Es wird geprueft, ob die Maske-Login existiert
			logger.log(Status.PASS, "Login-Maske existiert");

			logger.log(Status.INFO, "Anmeldename eingeben ");
			login.typeMail(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[0], logger); // User-Name//E-Mail
																									// // eingeben
			logger.log(Status.PASS, "Anmeldename ist erfolgreich eingegeben.");

			logger.log(Status.INFO, "Passwort eingeben ");
			login.typePasswort(ImportAnmeldeDatenVonExcelToJava.getAnmeldeDatenToExcel()[1], logger);// Passwort //
																										// eingeben
			logger.log(Status.PASS, "Passwort ist erfolgreich eingegeben worden.");

			logger.log(Status.INFO, "Anmelde-Button druecken ");
			login.anmelden(logger); // Anmeldebutton druecken
			logger.log(Status.INFO, "Anmelde-Button ist erfolgreich gedrueckt worden.");

		}

		// Es wird geprueft, ob man korrekt eingeloggt ist?
		logger.log(Status.INFO, "Pruefe, ob man eingeloggt ist. ");
		Assert.assertTrue(login.isEmailVisible());
		logger.log(Status.PASS, "man ist erfolgreich engeloggt   .");

		// Freelance.de Maske: Projekt finden
		// projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist();
		projektsuche.clickFreelanceDe();

		logger.log(Status.INFO, "Pruefe, ob Projekt Suchfelder existiert");
		projektsuche.isProjektFindenEingabenfelderExistAndprojektSucheButtonExist(logger);
		logger.log(Status.PASS, "Projekt Suchfelde existiert worden");

		logger.log(Status.INFO, "Projekt Suchbegriff eingeben");
		projektsuche.typeProjekt(data, logger);// leiter // Testautomatisierer
		logger.log(Status.PASS, "Projekt Suchbegriff ist erfolgreich eingeben  worden");

		logger.log(Status.INFO, "Suche-Button druecken");
		projektsuche.clickSuche(logger);
		logger.log(Status.PASS, "Suche-Button ist erfolgreich gedrueckt worden");

		// Maske navigieren und Projektinformationen aussortieren

		logger.log(Status.INFO, "Projektsergebnisse durchklicken");
		ProjektMaskeNavigationUndProjektMerkmaleHolen.ProjektmaskeAnklickeUndMaskeInformationenAssortieren(projektsuche,
				driver, projektmerkmale, data);
		logger.log(Status.PASS, "Projektsergebnisse sind erfolgreich anklickt worden");

		// Projektinformationen in der Datenbank uebertragen
		ExportProjektInformationenToExcel.TextMethodeName(
				projektsuche.projektErgebnisse() + "_" + stackTrace[0].getMethodName(), data,
				driver.getCurrentUrl().split("https://www.")[1].split("/")[0].toString());

		logger.log(Status.INFO, "Projektsergebnisse in Excel exportiert");
		ExportProjektInformationenToExcel.Excel(); // Das Insert der Daten in der SQLITE passiert in diesem Klasse
		logger.log(Status.PASS, "Export der Daten ist erfolgreich gewesen");
	}

	@AfterMethod
	public void afterMethod(ITestResult result) throws InterruptedException, IOException {

		String ScreenshotPath = Utility.getScreenshot(driver, result.getName());

		ExtentLogging.getResultWithScreenshotByFailTest(result, logger, ScreenshotPath, driver, dataProviderParameter);

		if (dataProviderParameter.equals(LastElementDataProvider)) {
			Thread.sleep(3000);
			driver.quit();
		}

	}

	@AfterTest
	public void EndTest() {
		// Query Select betätigen und Ergebnisse zeigen
		sqlite.SQLITEJDBCSelect();
		extent.flush();

	}

}
