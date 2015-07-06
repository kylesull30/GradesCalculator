package edu.gatech.seclass.gradescalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Grades {
	private XSSFWorkbook workBook;
	private HashMap<String, Integer> attendanceTable = new HashMap<>();

	public Grades(String gradesDB){
		try {
			//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
			FileInputStream file = new FileInputStream(new File(gradesDB));
			this.workBook = new XSSFWorkbook (file);
			//end borrowed code
			
			this.loadAttendanceTable();

		} catch (FileNotFoundException e) {
			System.out.println("File not found. Check that the database is in the correct location.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadAttendanceTable(){
		XSSFSheet attendanceSheet = this.workBook.getSheetAt(0);
		
		Iterator<Row> rowIterator = attendanceSheet.iterator();
		Row row = rowIterator.next(); //Title row
		
	    while(rowIterator.hasNext()) {
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
            String gtID = String.format("%.0f", (cell.getNumericCellValue()));
            cell = cellIterator.next();
            int attendance = (int)cell.getNumericCellValue();            
            this.attendanceTable.put(gtID, attendance);
	    }
	}
	
	public int getNumAssignments(){
		XSSFSheet assignmentSheet = this.workBook.getSheetAt(1);
		Iterator<Row> rowIterator = assignmentSheet.iterator();
		Row row = rowIterator.next();
		return row.getLastCellNum() -1;
	}
	
	public int getNumProjects(){
		XSSFSheet indivProjectSheet = this.workBook.getSheetAt(2);
		Iterator<Row> rowIterator = indivProjectSheet.iterator();
		Row row = rowIterator.next();
		return row.getLastCellNum() -1;
	}
	public int getAttendanceById(String gtID){
		
		return this.attendanceTable.get(gtID).intValue();

	}
}
