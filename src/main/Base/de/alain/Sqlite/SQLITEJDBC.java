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
			System.out.println("siiii");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS ProjektMerkmale " + " (ProjektNummer		INT NOT NULL, "
					+ " Title		  VARCHAR(60)   NOT NULL, " + " Suchbegriff		  VARCHAR(60)   NOT NULL, "
					+ " WebSeite		  VARCHAR(40)   NOT NULL, " + " GeplanterStart	VARCHAR(60), "
					+ " VorraussichtlichesEnde	VARCHAR(60), " + " ProjektOrt         VARCHAR(60), "
					+ " StundenSatz         VARCHAR(60), " + " Remote         VARCHAR(60), "
					+ " LetzteUpdate         VARCHAR(60), " + " ReferenzNummer         VARCHAR(60), "
					+ " ErstellDatum VARCHAR(20)   NOT NULL, " + " Projektbeschreibung         TEXT)";

			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table created successfully");
		System.out.println("seeee");
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
			System.out.println("sssssssss");

			stmt = c.createStatement();
			/*
			 * 
			 * String sql = "INSERT INTO ProjektMerkmale (ProjektNummer, Title, " +
			 * "Suchbegriff, Webseite, GeplanterStart,VorraussichtlichesEnde,ProjektOrt, " +
			 * "StundenSatz, Remote , LetzteUpdate , ReferenzNummer,  ErstellDatum, Projektbeschreibung)"
			 * + String.format(
			 * "VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');"
			 * , projektNummer, projektinformationen.getTitle(), Suchbegriff, Webseite,
			 * projektinformationen.getGeplanterStart(),
			 * projektinformationen.getVoraussichtlichesEnde(),
			 * projektinformationen.getProjektOrt(), projektinformationen.getStundenSatz(),
			 * projektinformationen.getRemote(), projektinformationen.getLetzteUpdate(),
			 * projektinformationen.getRefenrenzNummer(), CurrentDate,
			 * projektinformationen.getProjektbeschreibung()) ;
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
		System.out.println("fffffff");
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

			/*
			 * String sql =
			 * "SELECT projektNummer, Title,Suchbegriff, WebSeite, GeplanterStart, " +
			 * "VorraussichtlichesEnde, ProjektOrt, StundenSatz, Remote, LetzteUpdate, ReferenzNummer, ErstellDatum, Projektbeschreibung, ROW_NUMBER()  OVER(PARTITION BY projektNummer, Title, Suchbegriff, WebSeite, GeplanterStart, VorraussichtlichesEnde, ProjektOrt,StundenSatz, Remote, LetzteUpdate, ReferenzNummer, ErstellDatum, Projektbeschreibung "
			 * +
			 * "		ORDER BY projektNummer, Title, Suchbegriff, WebSeite, GeplanterStart, VorraussichtlichesEnde, ProjektOrt,StundenSatz, Remote, LetzteUpdate, ReferenzNummer, ErstellDatum, Projektbeschreibung ) AS RowNumber  FROM ProjektMerkmale;"
			 * ;
			 */

			String sql = "CREATE TABLE IF NOT EXISTS ProjektMerkmaleDuplicateUpDATE AS SELECT projektNummer, Title,Suchbegriff, WebSeite, GeplanterStart,"
					+ " VorraussichtlichesEnde, ProjektOrt, StundenSatz, Remote, LetzteUpdate, ReferenzNummer, ErstellDatum, Projektbeschreibung , COUNT(*)"
					+ " FROM ProjektMerkmale GROUP BY projektNummer, Title,Suchbegriff, WebSeite, GeplanterStart, VorraussichtlichesEnde, ProjektOrt, StundenSatz, Remote, LetzteUpdate, ReferenzNummer, ErstellDatum, Projektbeschreibung "
					+ "HAVING COUNT(*) > 1";

			stmt.executeUpdate(sql);
			c.commit();

			String sql1 = "CREATE TABLE IF NOT EXISTS ProjektMerkmaleDuplicateUpDATE2 AS SELECT DISTINCT ProjektMerkmale.projektNummer, ProjektMerkmale.Title, ProjektMerkmale.Suchbegriff,"
					+ " ProjektMerkmale.WebSeite, ProjektMerkmale.GeplanterStart, ProjektMerkmale.VorraussichtlichesEnde, ProjektMerkmale.ProjektOrt, ProjektMerkmale.StundenSatz, "
					+ "ProjektMerkmale.Remote, ProjektMerkmale.LetzteUpdate, ProjektMerkmale.ReferenzNummer, ProjektMerkmale.ErstellDatum, ProjektMerkmale.Projektbeschreibung "
					+ "FROM ProjektMerkmale LEFT JOIN ProjektMerkmaleDuplicateUpDATE ON "
					+ "ProjektMerkmale.projektNummer = ProjektMerkmaleDuplicateUpDATE.projektNummer "
					+ "AND ProjektMerkmale.Title = ProjektMerkmaleDuplicateUpDATE.Title "
					+ "AND ProjektMerkmale.Suchbegriff = ProjektMerkmaleDuplicateUpDATE.Suchbegriff "
					+ "AND ProjektMerkmale.WebSeite = ProjektMerkmaleDuplicateUpDATE.WebSeite "
					+ "AND ProjektMerkmale.GeplanterStart = ProjektMerkmaleDuplicateUpDATE.GeplanterStart "
					+ "AND ProjektMerkmale.VorraussichtlichesEnde = ProjektMerkmaleDuplicateUpDATE.VorraussichtlichesEnde "
					+ "AND ProjektMerkmale.ProjektOrt = ProjektMerkmaleDuplicateUpDATE.ProjektOrt "
					+ "AND ProjektMerkmale.StundenSatz = ProjektMerkmaleDuplicateUpDATE.StundenSatz "
					+ "AND ProjektMerkmale.Remote = ProjektMerkmaleDuplicateUpDATE.Remote "
					+ "AND ProjektMerkmale.LetzteUpdate = ProjektMerkmaleDuplicateUpDATE.LetzteUpdate "
					+ "AND ProjektMerkmale.ReferenzNummer = ProjektMerkmaleDuplicateUpDATE.ReferenzNummer "
					+ "AND ProjektMerkmale.ErstellDatum = ProjektMerkmaleDuplicateUpDATE.ErstellDatum "
					+ "AND ProjektMerkmale.Projektbeschreibung = ProjektMerkmaleDuplicateUpDATE.Projektbeschreibung";

			stmt.executeUpdate(sql1);
			c.commit();

			String sql2 = "DELETE FROM ProjektMerkmale WHERE ( ( SELECT ProjektMerkmale.projektNummer, ProjektMerkmale.Title, ProjektMerkmale.Suchbegriff, ProjektMerkmale.WebSeite , "
					+ "ProjektMerkmale.GeplanterStart , ProjektMerkmale.VorraussichtlichesEnde, ProjektMerkmale.ProjektOrt  , ProjektMerkmale.StundenSatz , "
					+ "ProjektMerkmale.Remote , ProjektMerkmale.LetzteUpdate , ProjektMerkmale.ReferenzNummer , ProjektMerkmale.ErstellDatum , ProjektMerkmale.Projektbeschreibung FROM ProjektMerkmale) "
					+ " IN (SELECT ProjektMerkmale.* FROM ProjektMerkmale LEFT JOIN  ProjektMerkmaleDuplicateUpDATE ON "
					+ "ProjektMerkmale.projektNummer = ProjektMerkmaleDuplicateUpDATE.projektNummer "
					+ "AND ProjektMerkmale.Title = ProjektMerkmaleDuplicateUpDATE.Title "
					+ "AND ProjektMerkmale.Suchbegriff = ProjektMerkmaleDuplicateUpDATE.Suchbegriff "
					+ "AND ProjektMerkmale.WebSeite = ProjektMerkmaleDuplicateUpDATE.WebSeite "
					+ "AND ProjektMerkmale.GeplanterStart = ProjektMerkmaleDuplicateUpDATE.GeplanterStart "
					+ "AND ProjektMerkmale.VorraussichtlichesEnde = ProjektMerkmaleDuplicateUpDATE.VorraussichtlichesEnde "
					+ "AND ProjektMerkmale.ProjektOrt = ProjektMerkmaleDuplicateUpDATE.ProjektOrt "
					+ "AND ProjektMerkmale.StundenSatz = ProjektMerkmaleDuplicateUpDATE.StundenSatz "
					+ "AND ProjektMerkmale.Remote = ProjektMerkmaleDuplicateUpDATE.Remote "
					+ "AND ProjektMerkmale.LetzteUpdate = ProjektMerkmaleDuplicateUpDATE.LetzteUpdate "
					+ "AND ProjektMerkmale.ReferenzNummer = ProjektMerkmaleDuplicateUpDATE.ReferenzNummer "
					+ "AND ProjektMerkmale.ErstellDatum = ProjektMerkmaleDuplicateUpDATE.ErstellDatum "
					+ "AND ProjektMerkmale.Projektbeschreibung = ProjektMerkmaleDuplicateUpDATE.Projektbeschreibung) );";

			stmt.executeUpdate(sql2);
			c.commit();

			String sql3 = "INSERT INTO ProjektMerkmale" + " SELECT * FROM ProjektMerkmaleDuplicateUpDATE2 ";

			stmt.executeUpdate(sql3);
			c.commit();

			/*
			 * ResultSet rs =
			 * stmt.executeQuery("SELECT * FROM ProjektMerkmale ORDER BY ROWID ASC");
			 * 
			 * while (rs.next()) { int projektNummer = rs.getInt("projektNummer"); String
			 * Title = rs.getString("Title"); String Suchbegriff =
			 * rs.getString("Suchbegriff"); String WebSeite = rs.getString("WebSeite");
			 * String GeplanterStart = rs.getString("GeplanterStart"); String
			 * VorraussichtlichesEnde = rs.getString("VorraussichtlichesEnde"); String
			 * ProjektOrt = rs.getString("ProjektOrt"); String StundenSatz =
			 * rs.getString("StundenSatz"); String Remote = rs.getString("Remote"); String
			 * LetzteUpdate = rs.getString("LetzteUpdate"); String ReferenzNummer =
			 * rs.getString("ReferenzNummer"); String ErstellDatum =
			 * rs.getString("ErstellDatum"); String Projektbeschreibung =
			 * rs.getString("Projektbeschreibung");
			 * 
			 * System.out.println("projektNummer = " + projektNummer);
			 * System.out.println("Title = " + Title); System.out.println("Suchbegriff = " +
			 * Suchbegriff); System.out.println("WebSeite = " + WebSeite);
			 * System.out.println("GeplanterStart = " + GeplanterStart);
			 * System.out.println("VorraussichtlichesEnde = " + VorraussichtlichesEnde);
			 * System.out.println("ProjektOrt = " + ProjektOrt);
			 * System.out.println("StundenSatz = " + StundenSatz);
			 * System.out.println("Remote = " + Remote);
			 * System.out.println("LetzteUpdate = " + LetzteUpdate);
			 * System.out.println("ReferenzNummer = " + ReferenzNummer);
			 * System.out.println("ErstellDatum = " + ErstellDatum);
			 * System.out.println("Projektbeschreibung = " + Projektbeschreibung);
			 * System.out.println(); } rs.close();
			 */
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}
}
