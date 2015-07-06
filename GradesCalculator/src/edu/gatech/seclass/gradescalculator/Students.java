package edu.gatech.seclass.gradescalculator;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Students {
	private HashMap<String, Student> studentsTable = new HashMap<>();
	private XSSFWorkbook workBook;
	
	public Students(String studentsDB){
		try {
			//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
			FileInputStream file = new FileInputStream(new File(studentsDB));
			this.workBook = new XSSFWorkbook (file);
			//end borrowed code
			this.processStudentTable();
			this.processTeamTable();

		} catch (FileNotFoundException e) {
			System.out.println("File not found. Check that the student database is in the correct location.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		
		
	}
	
	private void processStudentTable(){
		XSSFSheet studentsSheet = this.workBook.getSheetAt(0);
		
		Iterator<Row> rowIterator = studentsSheet.iterator();
		Row row = rowIterator.next(); //Title row
		
	    while(rowIterator.hasNext()) {
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
            
	        Cell cell = cellIterator.next();
            String name = cell.getStringCellValue();
            
            cell = cellIterator.next();
            String gtID = String.format("%.0f", (cell.getNumericCellValue()));  
            
            cell = cellIterator.next();
            String email = cell.getStringCellValue();
            
            this.studentsTable.put(name, new Student(name, gtID, email));

	    }
	}
	
	private void processTeamTable(){
		
		XSSFSheet teamsSheet = this.workBook.getSheetAt(1);
		Iterator<Row> rowIterator = teamsSheet.iterator();
		
		Row row = rowIterator.next(); //Title row

		while(rowIterator.hasNext()) {
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        
	        if (cellIterator.hasNext()){
	            Cell cell = cellIterator.next();
	            
	            String teamName = cell.getStringCellValue();

	            while(cellIterator.hasNext()){
	            	cell = cellIterator.next();
	            	String name = cell.getStringCellValue();
	            	this.studentsTable.get(name).setTeam(teamName);
		        }
	        }
		}
	}
	
	public HashSet<Student> getAllStudents(){
		return new HashSet<Student>(this.studentsTable.values());
	}
	
	public Student getByName(String name){
		return this.studentsTable.get(name);
	}

	public Student getById(String id){
		for (Student s : this.studentsTable.values()) {
			if(s.getGtid().equals(id)){
				return s;
			}
		}
		
		return null;
	}
	
	public int getNumStudents(){
		return this.studentsTable.size();
	}
}
