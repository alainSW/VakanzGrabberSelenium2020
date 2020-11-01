package de.alain.ProjektStatitikUndInformationen;

public class ProjektInformationen {

	private String title;
	private String geplanterStart;
	private String voraussichtlichesEnde;
	private String projektOrt;
	private String stundenSatz;
	private String letzteUpdate;
	private String refenrenzNummer;
	private String projektbeschreibung;
	private String remote;

	public ProjektInformationen(String tit, String geStart, String vorausEnde, String projOrt, String stdsatz,
			String remot, String letUp, String refNumber, String projBeschreibung) {

		title = tit;
		geplanterStart = geStart;
		voraussichtlichesEnde = vorausEnde;
		projektOrt = projOrt;
		stundenSatz = stdsatz;
		remote = remot;
		letzteUpdate = letUp;
		refenrenzNummer = refNumber;
		projektbeschreibung = projBeschreibung;

	}

	// title
	public String getTitle() {
		return title;
	}

	public void setTitle(String value) {
		title = value;
	}

	// geplanterStart
	public String getGeplanterStart() {
		return geplanterStart;
	}

	public void setGeplanterStart(String value) {
		geplanterStart = value;
	}

	// voraussichtlichesEnde
	public String getVoraussichtlichesEnde() {
		return voraussichtlichesEnde;
	}

	public void setVoraussichtlichesEnde(String value) {
		voraussichtlichesEnde = value;
	}

	// projektOrt
	public String getProjektOrt() {
		return projektOrt;
	}

	public void settundenSatz(String value) {
		projektOrt = value;
	}

	// stundenSatz
	public String getStundenSatz() {
		return stundenSatz;
	}

	public void setStundenSatz(String value) {
		stundenSatz = value;
	}

	// letzteUpdate

	public String getLetzteUpdate() {
		return letzteUpdate;
	}

	public void setLetzteUpdate(String value) {
		letzteUpdate = value;
	}

	// refenrenzNummer
	public String getRefenrenzNummer() {
		return refenrenzNummer;
	}

	public void setRefenrenzNummer(String value) {
		refenrenzNummer = value;
	}

	// projektbeschreibung
	public String getProjektbeschreibung() {
		return projektbeschreibung;
	}

	public void setProjektbeschreibung(String value) {
		projektbeschreibung = value;
	}

	// remote
	public String getRemote() {
		return remote;
	}

	public void setRemote(String value) {
		remote = value;
	}

	public String toString() {

		return String.format(
				"Projekttitel: %s\nGeplanter Start:  %s\nVoraussichtliches Ende:  %s\nProjektOrt:  %s\nStundenSatz:  %s\nRemoteArbeit:  %s\nLetzte Update:  %s\nRefenrenz Nummer:  %s\nProjektbeschreibung:  %s\n",
				this.getTitle(), this.getGeplanterStart(), this.getVoraussichtlichesEnde(), this.getProjektOrt(),
				this.getStundenSatz(), this.getRemote(), this.getLetzteUpdate(), this.getRefenrenzNummer(),
				this.getProjektbeschreibung());

	}
}
