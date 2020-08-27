package de.alain.CreateExcelFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ImportAnmeldeDatenVonExcelToJava2 {

	static Object[][] loginParameter1 = null;
	static Object[][] loginParameter = null;

	@Test(dataProvider = "data-provider")
	public void fillUderForm(String webseite, String projektBegriff) {
		// System.out.println("webseite : " + webseite);
		// System.out.println("projektBegriff : " + projektBegriff);
	}

	@DataProvider(name = "data-provider")
	public Object[][] dataProviderMethod() throws IOException {

		Object[][] data = getProjektsuchbegriffToExcel();

		return data;
	}

	public static Object[][] getAnmeldeDatenToExcel() throws IOException {

		// Object[][] loginParameter = null;

		File file = new File("D:\\_SeliniumVakanzGrabber\\Neuer Ordner\\anmeldeInformationen.xls");
		// Read XSL file
		FileInputStream inputStream = new FileInputStream(file);

		// Get the workbook instance for XLS file
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(0);

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.hasNext();
		Row rowHeader = rowIterator.next();

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator1 = sheet.iterator();

		loginParameter1 = new Object[sheet.getLastRowNum()][2];

		System.out.println("+++++++  " + sheet.getLastRowNum() + " " + rowHeader.getLastCellNum());

		while (rowIterator1.hasNext()) {

			Row row = rowIterator1.next();
			// Get iterator to all cells of current row
			Iterator<Cell> cellIterator = row.cellIterator();

			// System.out.println("row.getRowNum() :" + row.getRowNum());

			if (row.getRowNum() > 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					// System.out.println("cell.getColumnIndex() :" + cell.getColumnIndex());
					// System.out.println("cell.getStringCellValue() :" +
					// cell.getStringCellValue());

					System.out.println(row.getRowNum() - 1 + "  " + cell.getColumnIndex());

					loginParameter1[row.getRowNum() - 1][cell.getColumnIndex()] = cell.getStringCellValue();

					System.out.println(loginParameter1[row.getRowNum() - 1][cell.getColumnIndex()]
							+ "     CellIdexNum :" + cell.getColumnIndex());

				}

			}

		}

		return loginParameter;
	}

	public static Object[][] getProjektsuchbegriffToExcel() throws IOException {

		// Object[][] loginParameter = null;

		File file = new File("D:\\_SeliniumVakanzGrabber\\Neuer Ordner\\anmeldeInformationen.xls");
		// Read XSL file
		FileInputStream inputStream = new FileInputStream(file);

		// Get the workbook instance for XLS file
		@SuppressWarnings("resource")
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

		// Get first sheet from the workbook
		HSSFSheet sheet = workbook.getSheetAt(1);
		sheet.getLastRowNum();
		sheet.getHeader();
		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator = sheet.iterator();

		Row rowHeader = rowIterator.next();
		Iterator<Cell> cellIterator = rowHeader.cellIterator();
		Cell cell1 = cellIterator.next();

		// Get iterator to all the rows in current sheet
		Iterator<Row> rowIterator1 = sheet.iterator();

		loginParameter = new Object[sheet.getLastRowNum()][2];

		System.out.println("+++++++  " + sheet.getLastRowNum() + " " + sheet.getHeader());

		while (rowIterator1.hasNext()) {

			Row row = rowIterator1.next();
			// Get iterator to all cells of current row
			Iterator<Cell> cellIterator1 = row.cellIterator();

			// System.out.println("row.getRowNum() :" + row.getRowNum());

			if (row.getRowNum() > 0) {
				while (cellIterator1.hasNext()) {
					Cell cell = cellIterator1.next();

					// System.out.println("cell.getColumnIndex() :" + cell.getColumnIndex());
					// System.out.println("cell.getStringCellValue() :" +
					// cell.getStringCellValue());
					System.out.println(row.getRowNum() - 1 + "  " + cell.getColumnIndex());
					loginParameter[row.getRowNum() - 1][cell.getColumnIndex()] = cell.getStringCellValue();

					System.out.println(loginParameter[row.getRowNum() - 1][cell.getColumnIndex()] + "     CellIdexNum :"
							+ cell.getColumnIndex());

				}

			}

		}

		return loginParameter;
	}

	public static void main(String[] args) throws IOException {

		ImportAnmeldeDatenVonExcelToJava2.getAnmeldeDatenToExcel();
		ImportAnmeldeDatenVonExcelToJava2.getProjektsuchbegriffToExcel();
	}

}
