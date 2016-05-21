package edu.gatech.seclass.gradescalculator;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Students {
	private HashMap<String, Student> studentsTable = new HashMap<>();//Maps a student name to a Student object
	private XSSFWorkbook workBook;
	
	public Students(String studentsDB){
		try {
			FileInputStream file = new FileInputStream(new File(studentsDB));
			this.workBook = new XSSFWorkbook (file);
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

		Row row = rowIterator.next(); //Skip the title row
		
	    while(rowIterator.hasNext()) {
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
	        Cell cell = cellIterator.next();

            String name = cell.getStringCellValue();
            
            cell = cellIterator.next();
            String gtID = String.format("%.0f", cell.getNumericCellValue());  //remove scientific notation from the double value pulled from the sheet
            
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
	            	this.studentsTable.get(name).setTeam(teamName);//get the student by name and then set the team name for that student
		        }
	        }
		}
	}
	
	public HashSet<Student> getAllStudents(){
		return new HashSet<Student>(this.studentsTable.values());//create and return a HashSet from HashMap's values (Student objects)
	}
	
	public Student getByName(String name){
		return this.studentsTable.get(name);
	}

	public Student getById(String id){
		for (Student s : this.studentsTable.values()) {
			if(s.getGtid().equals(id)){
				return s;//if student found return the Student object
			}
		}
		
		return null;//if student not found return null
	}
	
	public int getNumStudents(){
		return this.studentsTable.size();
	}

	public void addStudent(Student student) {
		this.studentsTable.put(student.getName(), student);
	}

	public void updateDB() {
		// TODO Auto-generated method stub
		
	}
}
