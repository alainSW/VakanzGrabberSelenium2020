package de.alain.CreateExcelFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import de.alain.ProjektStatitikUndInformationen.ProjektInformationen;
import de.alain.ProjektmaskeNavigationUndProjektmerkmaleHolen.ProjektMaskeNavigationUndProjektMerkmaleHolen;

public class ExportProjektInformationenToExcel {

	private static String variable;

	private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBold(true);
		HSSFCellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	public static String TextMethodeName(String MethodeName, String FileName, String getTitleURL) {

		return variable = MethodeName + " " + FileName + " " + getTitleURL;

	}

	public static void Excel() throws IOException {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Projekt_ " + variable.split(" ")[0]);

		List<ProjektInformationen> list = ProjektMaskeNavigationUndProjektMerkmaleHolen.listProjektInformationen();

		int rownum = 0;
		Cell cell;
		Row row;
		//
		HSSFCellStyle style = createStyleForTitle(workbook);

		row = sheet.createRow(rownum);

		// ProjektNum
		cell = row.createCell(0, CellType.STRING);
		cell.setCellValue("ProjektNummer");
		cell.setCellStyle(style);
		// title
		cell = row.createCell(1, CellType.STRING);
		cell.setCellValue("Title");
		cell.setCellStyle(style);
		// geplanterStart
		cell = row.createCell(2, CellType.STRING);
		cell.setCellValue("GeplanterStart");
		cell.setCellStyle(style);
		// vorraussichtlichesEnde
		cell = row.createCell(3, CellType.STRING);
		cell.setCellValue("VorraussichtlichesEnde");
		cell.setCellStyle(style);
		// projektOr
		cell = row.createCell(4, CellType.STRING);
		cell.setCellValue("ProjektOrt");
		cell.setCellStyle(style);
		// stundenSatz
		cell = row.createCell(5, CellType.STRING);
		cell.setCellValue("StundenSatz");
		cell.setCellStyle(style);

		// Remote
		cell = row.createCell(6, CellType.STRING);
		cell.setCellValue("Remote");
		cell.setCellStyle(style);
		// letzteUpdate
		cell = row.createCell(7, CellType.STRING);
		cell.setCellValue("LetzteUpdate");
		cell.setCellStyle(style);
		// referenzNummer
		cell = row.createCell(8, CellType.STRING);
		cell.setCellValue("ReferenzNummer");
		cell.setCellStyle(style);
		// projektbeschreibung
		cell = row.createCell(9, CellType.STRING);
		cell.setCellValue("Projektbeschreibung");
		cell.setCellStyle(style);

		// Data
		for (ProjektInformationen pr : list) {
			rownum++;
			row = sheet.createRow(rownum);
			System.out.println(rownum);
			System.out.println(pr);
			// ProjektNum (A)
			cell = row.createCell(0, CellType.NUMERIC);
			cell.setCellValue(rownum);
			// title (B)
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(pr.getTitle());
			// geplanterStart (C)
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(pr.getGeplanterStart());
			// VorraussichtlichesEnde (D)
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(pr.getVoraussichtlichesEnde());
			// ProjektOrt (E)
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(pr.getProjektOrt());
			// StundenSatz (F)
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue(pr.getStundenSatz());

			// Remote (G)
			cell = row.createCell(6, CellType.STRING);
			cell.setCellValue(pr.getRemote());

			// LetzteUpdate (H)
			cell = row.createCell(7, CellType.STRING);
			cell.setCellValue(pr.getLetzteUpdate());
			// ReferenzNummer (I)
			cell = row.createCell(8, CellType.STRING);
			cell.setCellValue(pr.getRefenrenzNummer());
			// Projektbeschreibung (J)
			cell = row.createCell(9, CellType.STRING);
			cell.setCellValue(pr.getProjektbeschreibung());

		}

		//
		File file = new File("D:\\_SeliniumVakanzGrabber\\Neuer Ordner\\" + variable.split(" ")[2] + "_Projekt_"
				+ variable.split(" ")[1] + " " + CurrentDateTime() + ".xls");

		file.getParentFile().mkdirs();

		if (file.createNewFile()) {

			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			System.out.println("Created file: " + file.getAbsolutePath());
			workbook.close();

		} else {

			System.out.println("Achtung Datei existiert bereit");
			file.delete();

			System.out.println("Die Datei wurde erfolgreich gelöscht.");
			FileOutputStream outFile = new FileOutputStream(file);
			workbook.write(outFile);
			System.out.println("Created file: " + file.getAbsolutePath());
			workbook.close();
		}

	}

	public static String CurrentDateTime() {

		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");

		Date date = new Date();

		System.out.println(formatter.format(date));
		return formatter.format(date).toString();

	}
}
