package com.axa.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.axa.models.User;

public class ExcelReader {

	public static List<User> readExcel(FileInputStream file) {

		List<User> resultUsers = new ArrayList<User>();
		try {
			// Get the workbook instance
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			// Get the first sheet from the workbook
			HSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				Iterator<Cell> cellIterator = row.iterator();
				if (row.getRowNum() != 0) {
					User user = new User();
					user.setName(row.getCell(0).getStringCellValue());
					user.setSalary((int) row.getCell(1).getNumericCellValue());
					user.setAccount(row.getCell(2).getStringCellValue());
					resultUsers.add(user);
				}
				
			}

			file.close();
			FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Yannick.Pire\\Desktop\\test.xls"));
			workbook.write(out);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultUsers;

	}
}
