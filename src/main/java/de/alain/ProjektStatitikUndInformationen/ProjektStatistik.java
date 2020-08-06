package de.alain.ProjektStatitikUndInformationen;

import org.openqa.selenium.WebDriver;

import de.alain.PageAndHTMLControl.Base;

public class ProjektStatistik extends Base {

	public static int gesamteProjektAnzahl = 0;
	public static int endProjektMaskeNummer = 0;
	public static int anfangProjektMaskeNummer = 0;
	public static int anzahlProjektProMaske = 0;
	public static double pageTotal = 0;

	public ProjektStatistik(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public static int GesamteProjekt(String projektErgebnisStatistik) {
		// Diese Methode ermöglicht die Ermittlung der gesamten Projekte
		String[] hilsvariable;
		String input = "";
		String[] wert; // Pojekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split("von ");
		gesamteProjektAnzahl = Integer.parseInt(hilsvariable[1]);
		return gesamteProjektAnzahl;

	}

	public static int EndProjektMaskeNummer(String projektErgebnisStatistik) {
		// Diese Methode ermöglicht die Ermittlung der Position der letzten Projektmaske
		String[] hilsvariable;
		String input = "";
		String[] wert; // Projekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split(" von");
		wert = hilsvariable[0].split("-");
		endProjektMaskeNummer = Integer.parseInt(wert[1]);
		return endProjektMaskeNummer;

	}

	public static int AnfangProjektMaskeNummer(String projektErgebnisStatistik) {
		// Diese Methode ermöglicht die Ermittlung der Position der ersten Projektmaske
		String[] hilsvariable;
		String input = "";
		String[] wert; // Pojekt Total
		input = projektErgebnisStatistik;
		hilsvariable = input.split(" von");
		wert = hilsvariable[0].split("-");
		anfangProjektMaskeNummer = Integer.parseInt(wert[0].split(" ")[1]);
		return anfangProjektMaskeNummer;

	}

	public static int AnzahlProjektProMaske(String projektErgebnisStatistik) {
		// Diese Methode ermöglicht die Ermmitlung der gesamte Projekte pro Seitee

		anzahlProjektProMaske = EndProjektMaskeNummer(projektErgebnisStatistik)
				- AnfangProjektMaskeNummer(projektErgebnisStatistik) + 1;
		return anzahlProjektProMaske;

	}

	public static double AnzahlDerGeneriertenMaskeJeProjektsuche(String projektErgebnisStatistik) {
		// Diese Methode ermöglicht die Ermittlung der Anzahl der Projektsuche pro Maske
		Double projektPoSeite = (double) EndProjektMaskeNummer(projektErgebnisStatistik);
		Double projektTotal = (double) GesamteProjekt(projektErgebnisStatistik);
		Double ergebnis = (double) 0;
		ergebnis = projektTotal / projektPoSeite;
		pageTotal = ergebnis;
		return pageTotal;

	}

}
