package edu.gatech.seclass.gradescalculator;

//Borrowed from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
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
	//private File dbFile;
	private HashMap<String, Student> studentsTable = new HashMap<>();
	private XSSFWorkbook workBook;
	
	public Students(String studentsDB){
		try {
			//Begin borrowed code
			FileInputStream file = new FileInputStream(new File(studentsDB));
			this.workBook = new XSSFWorkbook (file);
			//end borrowed code
			this.processStudentTable();
			this.processTeamTable();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
            //System.out.println(name);
            cell = cellIterator.next();
           String gtID = String.format("%.0f", (cell.getNumericCellValue()));            
            this.studentsTable.put(name, new Student(name, gtID));

	    }
	}
	
	private void processTeamTable(){
		
		XSSFSheet teamsSheet = this.workBook.getSheetAt(1);
		Iterator<Row> rowIterator = teamsSheet.iterator();
		Row row = rowIterator.next(); //Title row
		//System.out.println(row.cellIterator().next().getStringCellValue());

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
		
		HashSet<Student> studSet = new HashSet<Student>(this.studentsTable.values());
		return studSet;
	}
	
	public Student getByName(String name){
		return this.studentsTable.get(name);
	}

	public Student getById(String id){
		for (Student s : this.studentsTable.values()) {
			//System.out.println(s.getGtid());
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
