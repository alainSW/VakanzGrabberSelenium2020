package de.alain.ProjektmaskeNavigationUndProjektmerkmaleHolen;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import de.alain.PageAndHTMLControl.Base;
import de.alain.PageAndHTMLControl.Projektmerkmale;
import de.alain.PageAndHTMLControl.Projektsuche;
import de.alain.ProjektStatitikUndInformationen.ProjektInformationen;
import de.alain.ProjektStatitikUndInformationen.ProjektStatistik;
import de.alain.Scrennshot.ScreenshotProjekt;

public class ProjektMaskeNavigationUndProjektMerkmaleHolen extends Base {

	// Initialisierung Projektsinformationsmenge
	public static ProjektInformationen[] projektInformationen;

	public ProjektMaskeNavigationUndProjektMerkmaleHolen(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static void ProjektmaskeAnklickeUndMaskeInformationenAssortieren(Projektsuche projektsuche, WebDriver driver,
			Projektmerkmale projektmerkmale, String Suchbegriff) throws InterruptedException, IOException {

		/*
		 * Diese Methode ermöglicht von der ersten Projektmaske bis zur letzten
		 * Projektseite zu blättern.
		 * 
		 */
		int GesamteProjekt = 0;
		GesamteProjekt = ProjektStatistik.GesamteProjekt(Projektsuche.anzahlProjekt());

		System.out.println("Projektsuche.anzahlProjekt() :" + Projektsuche.anzahlProjekt());
		projektInformationen = new ProjektInformationen[GesamteProjekt];
		int projektMaskeNummer = 1;
		double sss = ProjektStatistik.AnzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.anzahlProjekt());
		System.out.println(sss);

		System.out.println("projektInformationen Länge: " + projektInformationen.length);

		if (ProjektStatistik
				.AnzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.anzahlProjekt()) == projektMaskeNummer) {
			System.out.println("nur eine Seite ");
			projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);

		} else {
			System.out.println("mehere Seite " + sss);
			// projektClick(projektSuche, driver, projektDescription, i);

			while (projektMaskeNummer < sss + 1) {
				System.out.println(
						ProjektStatistik.AnzahlDerGeneriertenMaskeJeProjektsuche(Projektsuche.anzahlProjekt()));
				System.out.println(AnzahlProjektJeMaske(projektsuche));

				if (projektMaskeNummer == 1) {
					projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);
				} else {
					projektsuche.clickPageProjekt();
					projektClick(projektsuche, driver, projektmerkmale, projektMaskeNummer, Suchbegriff);
				}

				projektMaskeNummer++;
			}

		}
		listProjektInformationen();

	}

	public static int AnzahlProjektJeMaske(Projektsuche projektsuche) {

		/*
		 * Mit dieser Methode wird die Anzahl der Projekte je Projektmaske ermiitteln.
		 */

		int wert1;
		System.out.println("AnzahlProjektJeMaske.projektliste()  :  "
				+ ProjektStatistik.AnzahlProjektProMaske(Projektsuche.anzahlProjekt()));
		wert1 = ProjektStatistik.AnzahlProjektProMaske(Projektsuche.anzahlProjekt());

		System.out.println("AnzahlProjektJeMaske(projektsuche):  " + wert1);
		return wert1;

	}

	public static void projektClick(Projektsuche projektsuche, WebDriver driver, Projektmerkmale projektmerkmale,
			int projektMaskeNummer, String suchbegriff) throws InterruptedException, IOException {

		/*
		 * Diese Methode ermöglicht die Projekte je Maske anzuklicken
		 * 
		 * 
		 * Variablen Definition:
		 * 
		 * 
		 * k = prokektNummer, i = seitennummer,
		 * 
		 * 
		 */

		// k = prokektNummer
		int prokektNummer = 1;
		System.out.println("AnzahlProjektJeMaske(projektsuche) +" + AnzahlProjektJeMaske(projektsuche));

		if (projektMaskeNummer == 1) {
			// int k = 1;
			System.out.println(projektMaskeNummer);

			while (prokektNummer < AnzahlProjektJeMaske(projektsuche) + 1) {
				System.out.println(AnzahlProjektJeMaske(projektsuche) + 1);
				System.out.println(prokektNummer);
				WebElement projektId = driver
						.findElement(By.xpath("(//a[contains(@id,\"project_link\")])[" + prokektNummer + "]"));

				projektId.click();
				System.out.println("Start: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				// ProjektInformationenSortierenByErsteSeite(prokektNummer);
				ProjektInformationenSortierenAbDerZweiteSeite(prokektNummer, projektMaskeNummer);

				System.out.println("Ende: " + String.valueOf(prokektNummer) + " "
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

				TimeUnit.SECONDS.sleep(1);

				ScreenshotProjekt.ScreenshotExecute(suchbegriff + "_" + Integer.toString(prokektNummer));
				driver.navigate().back();

				prokektNummer++;
			}
		}

		if (projektMaskeNummer > 1) {
			System.out.println(
					AnzahlProjektJeMaske(projektsuche) + ProjektStatistik.GesamteProjekt(Projektsuche.anzahlProjekt()));
			System.out.println("prokektNummer " + prokektNummer + " i " + projektMaskeNummer);

			while (prokektNummer < AnzahlProjektJeMaske(projektsuche) + 1) {

				WebElement projektId = driver
						.findElement(By.xpath("(//*[contains(@id,\"project_link\")])[" + prokektNummer + "]"));
				projektId.click();

				System.out.println(Projektmerkmale.OriginalTitle_GeplanterStart().length() + " "
						+ Projektmerkmale.OriginalTitle_VoraussichtlichesEnde().length() + " "
						+ Projektmerkmale.OriginalTitle_ProjektOrt().length() + " "
						+ Projektmerkmale.OriginalTitle_StundenSatz().length() + " "
						+ Projektmerkmale.OriginalTitle_Remote().length() + " "
						+ Projektmerkmale.OriginalTitle_LetzteUpdate().length() + " "
						+ Projektmerkmale.OriginalTitle_RefenrenzNummer().length());

				ProjektInformationenSortierenAbDerZweiteSeite(prokektNummer, projektMaskeNummer);

				TimeUnit.SECONDS.sleep(1);

				driver.navigate().back();

				prokektNummer++;
			}
		}

	}

	public static List<ProjektInformationen> listProjektInformationen() {
		/*
		 * Dieser Methode wandelt die Array-Projektinformationen in einer Liste um.
		 * 
		 * 
		 */

		List<ProjektInformationen> list = Arrays.asList(projektInformationen);

		System.out.println(list.get(2).getRefenrenzNummer());

		return list;
	}

	// 10.07.2020
	/*
	 * public static void ProjektInformationenSortierenByErsteSeite(int
	 * prokektNummer) {
	 * 
	 * 
	 * 1. Projektmaske
	 * 
	 * Diese Methode ermöglicht die Projektinformationen bei der ersten Projektmaske
	 * zu sortieren.
	 * 
	 * 
	 * 
	 * String a1 = Projektmerkmale.OriginalTitle_GeplanterStart(); String a2 =
	 * Projektmerkmale.OriginalTitle_VoraussichtlichesEnde(); String a3 =
	 * Projektmerkmale.OriginalTitle_ProjektOrt(); String a4 =
	 * Projektmerkmale.OriginalTitle_StundenSatz(); String a5 =
	 * Projektmerkmale.OriginalTitle_Remote(); String a6 =
	 * Projektmerkmale.OriginalTitle_LetzteUpdate(); String a7 =
	 * Projektmerkmale.OriginalTitle_RefenrenzNummer();
	 * 
	 * String projektTitle = Projektmerkmale.ProjektTitle(); String geplanterStart =
	 * Projektmerkmale.GeplanterStart(); String voraussichtlichesEnde =
	 * Projektmerkmale.VoraussichtlichesEnde(); String projektOrt =
	 * Projektmerkmale.ProjektOrt(); String stundenSatz =
	 * Projektmerkmale.StundenSatz(); String remote = Projektmerkmale.Remote();
	 * String letzteUpdat = Projektmerkmale.LetzteUpdate(); String refenrenzNummer =
	 * Projektmerkmale.RefenrenzNummer(); String projektbeschreibung =
	 * Projektmerkmale.Projektbeschreibung();
	 * 
	 * // Fall 1: Stundensatz, TRemote, letztes Update, Referenznummer if
	 * (a1.equals("Geplanter Start") && a2.equals("Voraussichtliches Ende") &&
	 * a3.equals("Projektort") && a4.equals("Stundensatz") &&
	 * a5.equals("Remote-Einsatz möglich") && a6.equals("Letztes Update") &&
	 * a7.equals("Referenz-Nummer")) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(projektTitle, geplanterStart, voraussichtlichesEnde,
	 * projektOrt, stundenSatz, remote, letzteUpdat, refenrenzNummer,
	 * projektbeschreibung);
	 * 
	 * System.out.println("aaa1"); }
	 * 
	 * // Fall 4: Stundensatz, letztes Update, Referenznummer
	 * System.out.println("Kein Remote 1: " + String.valueOf(prokektNummer) + " " +
	 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * != 9 && a5.length() == 9 && a6.length() != 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(projektTitle, geplanterStart, voraussichtlichesEnde,
	 * projektOrt, stundenSatz, refenrenzNummer, remote, letzteUpdat,
	 * projektbeschreibung);
	 * 
	 * System.out.println("aaa4");
	 * 
	 * System.out.println("Kein Remote 2: " + String.valueOf(prokektNummer) + " " +
	 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	 * 
	 * return; }
	 * 
	 * System.out.println("Kein Remote 3: " + String.valueOf(prokektNummer) + " " +
	 * LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	 * 
	 * // Fall 2: Stundensatz, TRemote, letztes Update, if (a1.length() != 9 &&
	 * a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
	 * && a6.length() != 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * 
	 * System.out.println("aaa2"); return; }
	 * 
	 * // Fall 3: Stundensatz, TRemote, Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * != 9 && a5.length() != 9 && a6.length() == 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.Remote(), Projektmerkmale.RefenrenzNummer(),
	 * Projektmerkmale.LetzteUpdate(), Projektmerkmale.Projektbeschreibung());
	 * 
	 * System.out.println("aaa3"); return; }
	 * 
	 * // Fall 5: TRemote, letztes Update, Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() != 9 && a6.length() != 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.RefenrenzNummer(),
	 * Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(),
	 * Projektmerkmale.LetzteUpdate(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa5"); return; }
	 * 
	 * // Fall 6: Stundensatz, TRemote
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * != 9 && a5.length() != 9 && a6.length() == 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa6"); return; }
	 * 
	 * // Fall 7: Stundensatz, Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * != 9 && a5.length() == 9 && a6.length() == 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.LetzteUpdate(), Projektmerkmale.RefenrenzNummer(),
	 * Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa7"); return; }
	 * 
	 * // Fall 8: Stundensatz, letztes Update if (a1.length() != 9 && a2.length() !=
	 * 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9 && a6.length()
	 * != 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.LetzteUpdate(), Projektmerkmale.Remote(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa8"); return; }
	 * 
	 * // Fall 9: TRemote, letztes Update
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() != 9 && a6.length() != 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa9"); return;
	 * 
	 * }
	 * 
	 * // Fall 10: TRemote, Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() != 9 && a6.length() == 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.StundenSatz(), Projektmerkmale.RefenrenzNummer(),
	 * Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa10"); return; }
	 * 
	 * // Fall 11: letztes Update, Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() == 9 && a6.length() != 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa12"); return; }
	 * 
	 * // Fall 12: Stundensatz,
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * != 9 && a5.length() == 9 && a6.length() == 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa13"); return; }
	 * 
	 * // Fall 13: TRemote,
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() != 9 && a6.length() == 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.Remote(),
	 * Projektmerkmale.StundenSatz(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa4"); return; }
	 * 
	 * // Fall 14: letztes Update
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() == 9 && a6.length() != 9 && a7.length() == 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.Remote(), Projektmerkmale.StundenSatz(),
	 * Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa14"); return; }
	 * 
	 * // Fall 15: Referenznummer
	 * 
	 * if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length()
	 * == 9 && a5.length() == 9 && a6.length() == 9 && a7.length() != 9) {
	 * 
	 * projektInformationen[prokektNummer - 1] = new
	 * ProjektInformationen(Projektmerkmale.ProjektTitle(),
	 * Projektmerkmale.GeplanterStart(), Projektmerkmale.VoraussichtlichesEnde(),
	 * Projektmerkmale.ProjektOrt(), Projektmerkmale.RefenrenzNummer(),
	 * Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
	 * Projektmerkmale.StundenSatz(), Projektmerkmale.Projektbeschreibung());
	 * System.out.println("aaa15"); return; }
	 * 
	 * }
	 */

	// 13.07.2020
	public static void ProjektInformationenSortierenAbDerZweiteSeite(int prokektNummer, int projektMaskeNummer) {

		/*
		 * ab der 1. Projektmaske
		 * 
		 * Diese Methode ermöglicht die Projektinformationen ab der ersten Projektmaske
		 * zu sortieren.
		 * 
		 * 
		 */

		String a1 = Projektmerkmale.OriginalTitle_GeplanterStart();
		String a2 = Projektmerkmale.OriginalTitle_VoraussichtlichesEnde();
		String a3 = Projektmerkmale.OriginalTitle_ProjektOrt();
		String a4 = Projektmerkmale.OriginalTitle_StundenSatz();
		String a5 = Projektmerkmale.OriginalTitle_Remote();
		String a6 = Projektmerkmale.OriginalTitle_LetzteUpdate();
		String a7 = Projektmerkmale.OriginalTitle_RefenrenzNummer();

		String projektTitle = Projektmerkmale.ProjektTitle();
		String geplanterStart = Projektmerkmale.GeplanterStart();
		String voraussichtlichesEnde = Projektmerkmale.VoraussichtlichesEnde();
		String projektOrt = Projektmerkmale.ProjektOrt();
		String stundenSatz = Projektmerkmale.StundenSatz();
		String remote = Projektmerkmale.Remote();
		String letzteUpdat = Projektmerkmale.LetzteUpdate();
		String refenrenzNummer = Projektmerkmale.RefenrenzNummer();
		String projektbeschreibung = Projektmerkmale.Projektbeschreibung();

		// Fall 1: Stundensatz, TRemote, letztes Update, Referenznummer

		if (a1.equals("Geplanter Start") && a2.equals("Voraussichtliches Ende") && a3.equals("Projektort")
				&& a4.equals("Stundensatz") && a5.equals("Remote-Einsatz möglich") && a6.equals("Letztes Update")
				&& a7.equals("Referenz-Nummer")) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, remote, letzteUpdat,
					refenrenzNummer, projektbeschreibung);

			System.out.println("aaa1");

		}

		// Fall 4: Stundensatz, letztes Update, Referenznummer

		System.out.println("Kein Remote 1: " + String.valueOf(prokektNummer) + " "
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, refenrenzNummer,
					remote, letzteUpdat, projektbeschreibung);

			System.out.println("aaa4");

			System.out.println("Kein Remote 2: " + String.valueOf(prokektNummer) + " "
					+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

			return;
		}

		System.out.println("Kein Remote 3: " + String.valueOf(prokektNummer) + " "
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

		// Fall 2: Stundensatz, TRemote, letztes Update,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());

			System.out.println("aaa2");
			return;
		}

		// Fall 3: Stundensatz, TRemote, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(), Projektmerkmale.RefenrenzNummer(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.Projektbeschreibung());

			System.out.println("aaa3");
			return;
		}

		// Fall 5: TRemote, letztes Update, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa5");
			return;
		}

		// Fall 6: Stundensatz, TRemote

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa6");
			return;
		}

		// Fall 7: Stundensatz, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.LetzteUpdate(), Projektmerkmale.RefenrenzNummer(),
					Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa7");
			return;
		}

		// Fall 8: Stundensatz, letztes Update
		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.LetzteUpdate(), Projektmerkmale.Remote(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa8");
			return;
		}

		// Fall 9: TRemote, letztes Update

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() != 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa9");
			return;

		}

		// Fall 10: TRemote, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.StundenSatz(), Projektmerkmale.RefenrenzNummer(),
					Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa10");
			return;
		}

		// Fall 11: letztes Update, Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.RefenrenzNummer(), Projektmerkmale.StundenSatz(),
					Projektmerkmale.Remote(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa12");
			return;
		}

		// Fall 12: Stundensatz,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() != 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa13");
			return;
		}

		// Fall 13: TRemote,

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() != 9
				&& a6.length() == 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(), Projektmerkmale.Remote(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.LetzteUpdate(), Projektmerkmale.RefenrenzNummer(),
					Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa4");
			return;
		}

		// Fall 14: letztes Update

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() != 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.LetzteUpdate(), Projektmerkmale.Remote(), Projektmerkmale.StundenSatz(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa14");
			return;
		}

		// Fall 15: Referenznummer

		if (a1.length() != 9 && a2.length() != 9 && a3.length() != 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() != 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					Projektmerkmale.ProjektTitle(), Projektmerkmale.GeplanterStart(),
					Projektmerkmale.VoraussichtlichesEnde(), Projektmerkmale.ProjektOrt(),
					Projektmerkmale.RefenrenzNummer(), Projektmerkmale.Remote(), Projektmerkmale.LetzteUpdate(),
					Projektmerkmale.StundenSatz(), Projektmerkmale.Projektbeschreibung());
			System.out.println("aaa15");
			return;
		}

		if (a1.length() == 9 && a2.length() == 9 && a3.length() == 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, remote, letzteUpdat,
					refenrenzNummer, projektbeschreibung);

			System.out.println("aaa16");
			return;

		}

		// Fall 16:

		if (a1.length() == 9 && a2.length() == 9 && a3.length() == 9 && a4.length() == 9 && a5.length() == 9
				&& a6.length() == 9 && a7.length() == 9) {

			projektInformationen[(projektMaskeNummer - 1) * 20 + prokektNummer - 1] = new ProjektInformationen(
					projektTitle, geplanterStart, voraussichtlichesEnde, projektOrt, stundenSatz, remote, letzteUpdat,
					refenrenzNummer, projektbeschreibung);

			System.out.println("aaa16");
			return;

		}

		/*
		 * 
		 * // Fall 1: Stundensatz, TRemote, letztes Update, Referenznummer if
		 * (ProjektDescription.OriginalTitle_GeplanterStart().equals("Geplanter Start")
		 * && ProjektDescription.OriginalTitle_VoraussichtlichesEnde().
		 * equals("Voraussichtliches Ende") &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().equals("Projektort") &&
		 * ProjektDescription.OriginalTitle_StundenSatz().equals("Stundensatz") &&
		 * ProjektDescription.OriginalTitle_Remote().equals("Remote-Einsatz möglich") &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().equals("Letztes Update") &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().equals("Referenz-Nummer"))
		 * {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * }
		 * 
		 * // Fall 4: Stundensatz, letztes Update, Referenznummer if
		 * (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Remote(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aa42"); }
		 * 
		 * // Fall 2: Stundensatz, TRemote, letztes Update,
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa22"); }
		 * 
		 * // Fall 3: Stundensatz, TRemote, Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.RefenrenzNummer(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa32"); }
		 * 
		 * // Fall 4: Stundensatz, letztes Update, Referenznummer
		 * 
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Remote(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aa42"); }
		 * 
		 * 
		 * // Fall 5: TRemote, letztes Update, Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.RefenrenzNummer(), ProjektDescription.StundenSatz(),
		 * ProjektDescription.Remote(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa52"); }
		 * 
		 * // Fall 6: Stundensatz, TRemote
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa62"); }
		 * 
		 * // Fall 7: Stundensatz,letztes Update,
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.Remote(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aaa72"); }
		 * 
		 * // Fall 8: Stundensatz, Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.LetzteUpdate(),
		 * ProjektDescription.RefenrenzNummer(), ProjektDescription.Remote(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa82"); }
		 * 
		 * // Fall 9: TRemote, letztes Update
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.StundenSatz(),
		 * ProjektDescription.Remote(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aaa92"); }
		 * 
		 * // Fall 10: TRemote, Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.StundenSatz(),
		 * ProjektDescription.RefenrenzNummer(), ProjektDescription.Remote(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa102"); }
		 * 
		 * // Fall 11: letztes Update, Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa112"); }
		 * 
		 * // Fall 12: Stundensatz,
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() != 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.Remote(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa122"); }
		 * 
		 * // Fall 13: TRemote,
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() != 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.Remote(), ProjektDescription.StundenSatz(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aaa132"); }
		 * 
		 * // Fall 14: letztes Update
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() != 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() == 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.Remote(),
		 * ProjektDescription.StundenSatz(), ProjektDescription.RefenrenzNummer(),
		 * ProjektDescription.Projektbeschreibung()); System.out.println("aaa142"); }
		 * 
		 * // Fall 15: Referenznummer
		 * 
		 * if (ProjektDescription.OriginalTitle_GeplanterStart().length() != 9 &&
		 * ProjektDescription.OriginalTitle_VoraussichtlichesEnde().length() != 9 &&
		 * ProjektDescription.OriginalTitle_ProjektOrt().length() != 9 &&
		 * ProjektDescription.OriginalTitle_StundenSatz().length() == 9 &&
		 * ProjektDescription.OriginalTitle_Remote().length() == 9 &&
		 * ProjektDescription.OriginalTitle_LetzteUpdate().length() == 9 &&
		 * ProjektDescription.OriginalTitle_RefenrenzNummer().length() != 9) {
		 * 
		 * projektInformationen[i * 10 + k - 1] = new
		 * ProjektInformationen(ProjektDescription.ProjektTitle(),
		 * ProjektDescription.GeplanterStart(),
		 * ProjektDescription.VoraussichtlichesEnde(), ProjektDescription.ProjektOrt(),
		 * ProjektDescription.RefenrenzNummer(), ProjektDescription.Remote(),
		 * ProjektDescription.LetzteUpdate(), ProjektDescription.StundenSatz(),
		 * ProjektDescription.Projektbeschreibung());
		 * 
		 * System.out.println("aaa152"); }
		 * 
		 */
	}

}
