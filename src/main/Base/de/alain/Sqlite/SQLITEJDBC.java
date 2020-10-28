package de.alain.Sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import de.alain.ProjektStatitikUndInformationen.ProjektInformationen;

public class SQLITEJDBC {

	public void SQLITEJDBCConnectToDatabase() {
		Connection c = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	public void SQLITEJDBCCreateTable() {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ProjektMerkmale " + " (ProjektNummer		INT NOT NULL, "
					+ " Title		  VARCHAR(20)   NOT NULL, " + " Suchbegriff		  VARCHAR(40)   NOT NULL, "
					+ " WebSeite		  VARCHAR(40)   NOT NULL, " + " GeplanterStart	VARCHAR(20), "
					+ " VorraussichtlichesEnde	VARCHAR(20), " + " ProjektOrt         VARCHAR(20), "
					+ " StundenSatz         VARCHAR(20), " + " Remote         VARCHAR(20), "
					+ " LetzteUpdate         VARCHAR(20), " + " ReferenzNummer         VARCHAR(20), "
					+ " ErstellDatum VARCHAR(20)   NOT NULL, " + " Projektbeschreibung         TEXT)";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
	}

	public void SQLITEJDBCInsertData(ProjektInformationen projektinformationen, int projektNummer, String Suchbegriff,
			String Webseite, String CurrentDate) {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			/*
			 * String sql =
			 * "INSERT INTO COMPANY (ProjektNummer,Title, Suchbegriff, GeplanterStart,VorraussichtlichesEnde,ProjektOrt, StundenSatz, Remote , LetzteUpdate , ReferenzNummer,  ErstellDatum, Projektbeschreibung) "
			 * + "VALUES (" + projektNummer + ", " + projektinformationen.getTitle() + ", "
			 * + Suchbegriff + ", " + projektinformationen.getGeplanterStart() + ", " +
			 * projektinformationen.getVoraussichtlichesEnde() + ", " +
			 * projektinformationen.getProjektOrt() + ", " +
			 * projektinformationen.getStundenSatz() + ", " +
			 * projektinformationen.getRemote() + ", " +
			 * projektinformationen.getLetzteUpdate() + ", " +
			 * projektinformationen.getRefenrenzNummer() + ", " + CurrentDateTime() + ", " +
			 * projektinformationen.getProjektbeschreibung() + ");";
			 */

			String sql = "INSERT INTO ProjektMerkmale (ProjektNummer, Title, "
					+ "Suchbegriff, Webseite, GeplanterStart,VorraussichtlichesEnde,ProjektOrt, "
					+ "StundenSatz, Remote , LetzteUpdate , ReferenzNummer,  ErstellDatum, Projektbeschreibung)"
					+ String.format(
							"VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
							projektNummer, projektinformationen.getTitle(), Suchbegriff, Webseite,
							projektinformationen.getGeplanterStart(), projektinformationen.getVoraussichtlichesEnde(),
							projektinformationen.getProjektOrt(), projektinformationen.getStundenSatz(),
							projektinformationen.getRemote(), projektinformationen.getLetzteUpdate(),
							projektinformationen.getRefenrenzNummer(), CurrentDate,
							projektinformationen.getProjektbeschreibung());

			stmt.executeUpdate(sql);

			stmt.close();
			c.commit();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void SQLITEJDBCSelect() {

		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT DISTINCT * FROM ProjektMerkmale ORDER BY ROWID ASC");

			while (rs.next()) {
				int projektNummer = rs.getInt("projektNummer");
				String Title = rs.getString("Title");
				String Suchbegriff = rs.getString("Suchbegriff");
				String WebSeite = rs.getString("WebSeite");
				String GeplanterStart = rs.getString("GeplanterStart");
				String VorraussichtlichesEnde = rs.getString("VorraussichtlichesEnde");
				String ProjektOrt = rs.getString("ProjektOrt");
				String StundenSatz = rs.getString("StundenSatz");
				String Remote = rs.getString("Remote");
				String LetzteUpdate = rs.getString("LetzteUpdate");
				String ReferenzNummer = rs.getString("ReferenzNummer");
				String ErstellDatum = rs.getString("ErstellDatum");
				String Projektbeschreibung = rs.getString("Projektbeschreibung");

				System.out.println("projektNummer = " + projektNummer);
				System.out.println("Title = " + Title);
				System.out.println("Suchbegriff = " + Suchbegriff);
				System.out.println("WebSeite = " + WebSeite);
				System.out.println("GeplanterStart = " + GeplanterStart);
				System.out.println("VorraussichtlichesEnde = " + VorraussichtlichesEnde);
				System.out.println("ProjektOrt = " + ProjektOrt);
				System.out.println("StundenSatz = " + StundenSatz);
				System.out.println("Remote = " + Remote);
				System.out.println("LetzteUpdate = " + LetzteUpdate);
				System.out.println("ReferenzNummer = " + ReferenzNummer);
				System.out.println("ErstellDatum = " + ErstellDatum);
				System.out.println("Projektbeschreibung = " + Projektbeschreibung);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	public void SQLITEJDBCDelete() {
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:projektsInformationen.db");
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sql = "DELETE FROM ProjektMerkmale where ROWID = 2;";

			stmt.executeQuery(sql);
			c.commit();

			ResultSet rs = stmt.executeQuery("SELECT * FROM ProjektMerkmale ORDER BY ROWID ASC");

			while (rs.next()) {
				int projektNummer = rs.getInt("projektNummer");
				String Title = rs.getString("Title");
				String Suchbegriff = rs.getString("Suchbegriff");
				String WebSeite = rs.getString("WebSeite");
				String GeplanterStart = rs.getString("GeplanterStart");
				String VorraussichtlichesEnde = rs.getString("VorraussichtlichesEnde");
				String ProjektOrt = rs.getString("ProjektOrt");
				String StundenSatz = rs.getString("StundenSatz");
				String Remote = rs.getString("Remote");
				String LetzteUpdate = rs.getString("LetzteUpdate");
				String ReferenzNummer = rs.getString("ReferenzNummer");
				String ErstellDatum = rs.getString("ErstellDatum");
				String Projektbeschreibung = rs.getString("Projektbeschreibung");

				System.out.println("projektNummer = " + projektNummer);
				System.out.println("Title = " + Title);
				System.out.println("Suchbegriff = " + Suchbegriff);
				System.out.println("WebSeite = " + WebSeite);
				System.out.println("GeplanterStart = " + GeplanterStart);
				System.out.println("VorraussichtlichesEnde = " + VorraussichtlichesEnde);
				System.out.println("ProjektOrt = " + ProjektOrt);
				System.out.println("StundenSatz = " + StundenSatz);
				System.out.println("Remote = " + Remote);
				System.out.println("LetzteUpdate = " + LetzteUpdate);
				System.out.println("ReferenzNummer = " + ReferenzNummer);
				System.out.println("ErstellDatum = " + ErstellDatum);
				System.out.println("Projektbeschreibung = " + Projektbeschreibung);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}
