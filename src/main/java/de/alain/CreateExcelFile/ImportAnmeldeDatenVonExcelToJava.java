package de.alain.CreateExcelFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ImportAnmeldeDatenVonExcelToJava {

	// public static String[] loginParameter;

	public ImportAnmeldeDatenVonExcelToJava() {
		// TODO Auto-generated constructor stub
	}

	public static String[] getAnmeldeDatenToExcel() throws IOException {

		String loginParameter = "";

		File file = new File("D:\\_SeliniumVakanzGrabber\\Neuer Ordner\\anmeldeInformationen.xls");
		// Read XSL file
		FileInputStream inputStream = new FileInputStream(file);

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			// Get iterator to all cells of current row
			Iterator<Cell> cellIterator = row.cellIterator();

			System.out.println("row.getRowNum() :" + row.getRowNum());

			if (row.getRowNum() > 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					System.out.println("cell.getColumnIndex() :" + cell.getColumnIndex());
					System.out.println("cell.getStringCellValue() :" + cell.getStringCellValue());
					loginParameter += cell.getStringCellValue().toString() + " , ";
					// System.out.println(loginParameter[0] + " :" +
					// cell.getStringCellValue().toString());

				}

			}

		}

		System.out.println(loginParameter);
		return loginParameter.split(" , ");
	}

	public static String[] getProjektsuchbegriffToExcel() throws IOException {

		String loginParameter = "";

		File file = new File("D:\\_SeliniumVakanzGrabber\\Neuer Ordner\\anmeldeInformationen.xls");
		// Read XSL file
		FileInputStream inputStream = new FileInputStream(file);

		// Get the workbook instance for XLS file
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(1);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			// Get iterator to all cells of current row
			Iterator<Cell> cellIterator = row.cellIterator();

			System.out.println("row.getRowNum() :" + row.getRowNum());

			if (row.getRowNum() > 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					System.out.println("cell.getColumnIndex() :" + cell.getColumnIndex());
					System.out.println("cell.getStringCellValue() :" + cell.getStringCellValue());
					loginParameter += cell.getStringCellValue().toString() + " , ";
					// System.out.println(loginParameter[0] + " :" +
					// cell.getStringCellValue().toString());

				}

			}

		}

		System.out.println(loginParameter);
		return loginParameter.split(" , ");
	}

}
