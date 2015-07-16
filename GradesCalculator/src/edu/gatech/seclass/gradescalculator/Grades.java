package edu.gatech.seclass.gradescalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Grades {
	private XSSFWorkbook workBook;
	private FileInputStream file;
	private FileOutputStream fileOut;
	private String gradesDBPath;
	private HashMap<String, Integer> attendanceTable = new HashMap<>();//Maps a student name to an attendance Integer value
	private HashMap<String, ArrayList<Integer>> assignments = new HashMap<>();
	private HashMap<String, ArrayList<Integer>> projects = new HashMap<>();
	private HashMap<String, ArrayList<Integer>> teamProjects = new HashMap<>();

	
	public Grades(String gradesDB){

			this.gradesDBPath = gradesDB;
			
			this.loadAttendanceTable();
			this.loadAssignmentsTable();
			this.loadProjectsTable();
			this.loadTeamProjectsTable();


	}
	private void openFileForReading(){
		try {
			this.file = new FileInputStream(new File(this.gradesDBPath));
			this.workBook = new XSSFWorkbook (file);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void openFileForWriting(){
		try {
			this.file.close();
			this.fileOut = new FileOutputStream(new File(this.gradesDBPath));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeFileAfterWriting(){
		try {
			this.workBook = null;
			this.fileOut.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void closeFileAfterReading(){
		try {
			this.workBook = null;
			this.file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void loadProjectsTable() {
		this.openFileForReading();
		this.projects = null;
		this.projects = this.loadGrades(this.workBook.getSheetAt(2), false);
		this.closeFileAfterReading();

	}
	
	private void loadTeamProjectsTable() {
		this.openFileForReading();
		this.teamProjects = null;
		this.teamProjects = this.loadGrades(this.workBook.getSheetAt(3), true);
		this.closeFileAfterReading();

	}

	private void loadAssignmentsTable() {
		this.openFileForReading();
		this.assignments = null;
		this.assignments = this.loadGrades(this.workBook.getSheetAt(1), false);
		this.closeFileAfterReading();

	}
	private HashMap<String, ArrayList<Integer>> loadGrades(XSSFSheet assignmentsSheet, boolean isTeamLoad){
		HashMap<String, ArrayList<Integer>> gradesHashMap = new HashMap<>();
		
		Iterator<Row> rowIterator = assignmentsSheet.iterator();
		Row row = rowIterator.next(); //get assignment titles
				
		while(rowIterator.hasNext()) {
			//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
			row = rowIterator.next();
			Iterator<Cell> columnIterator = row.cellIterator();
		    Cell column = columnIterator.next();
		    //end borrowed code
		            
		    ArrayList<Integer> studentGrades = new ArrayList<>();
		    String key;
		    if(isTeamLoad){
		    	key = column.getStringCellValue();
		    }
		    else{
		    	key = String.format("%.0f", (column.getNumericCellValue()));//remove scientific notation from the double value pulled from the sheet
		    }       
		    while (columnIterator.hasNext()){
		            	
		         column = columnIterator.next();
		         int grade = (int)column.getNumericCellValue();//type cast the double value from the sheet to int            
		         studentGrades.add(grade);
		     }
		     //System.out.println("Adding " + key + studentGrades.size());       
		     gradesHashMap.put(key, studentGrades);
		            
		            
		}
		return gradesHashMap;
	}
	private void loadAttendanceTable(){
		this.openFileForReading();
		//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
		XSSFSheet attendanceSheet = this.workBook.getSheetAt(0);
		Iterator<Row> rowIterator = attendanceSheet.iterator();
		//end borrowed code
		this.attendanceTable = new HashMap<>();
		Row row = rowIterator.next(); //Skip the title row
		
	    while(rowIterator.hasNext()) {
			//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
	        row = rowIterator.next();
	        Iterator<Cell> cellIterator = row.cellIterator();
            Cell cell = cellIterator.next();
    		//end borrowed code

            String gtID = String.format("%.0f", (cell.getNumericCellValue()));//remove scientific notation from the double value pulled from the sheet
            cell = cellIterator.next();
            int attendance = (int)cell.getNumericCellValue();//type cast the double value from the sheet to int            
            this.attendanceTable.put(gtID, attendance);
	    }
	    this.closeFileAfterReading();
	}
	
	public int getNumAssignments(){
		this.openFileForReading();
		XSSFSheet assignmentSheet = this.workBook.getSheetAt(1); // Individual assignments sheet
		Iterator<Row> rowIterator = assignmentSheet.iterator();
		Row row = rowIterator.next();
		this.closeFileAfterReading();
		return row.getLastCellNum() -1;//Count all columns minus the name column*/
	}
	
	public int getNumProjects(){
		this.openFileForReading();
		XSSFSheet indivProjectSheet = this.workBook.getSheetAt(2);//Individual projects sheet
		Iterator<Row> rowIterator = indivProjectSheet.iterator();
		Row row = rowIterator.next();
		this.closeFileAfterReading();
		return row.getLastCellNum() -1;//Count all columns minus the name column
	}
	public int getAttendanceById(String gtID){
		this.loadAttendanceTable();
		return this.attendanceTable.get(gtID).intValue();//Change form Integer to int

	}

	public int getAverageAssignmentGrade(String gtid) {
		this.loadAssignmentsTable();
		int gradeSum = 0;
		ArrayList<Integer> studentGrades = this.assignments.get(gtid);
		
		for (int i = 0; i < studentGrades.size(); i++){
			gradeSum += studentGrades.get(i);
		}
		double avg = (double)gradeSum/(double)studentGrades.size();
		
		return (int)Math.round(avg);
	}
	
	public int getAverageProjectGrade(String gtid, String team) {
		int gradeSum = 0;
		ArrayList<Integer> studentGrades = this.projects.get(gtid);;
		ArrayList<Integer> teamGrades = this.teamProjects.get(team);

		this.loadTeamProjectsTable();
		this.loadProjectsTable();
		
		for (int i = 0; i < studentGrades.size(); i++){
			gradeSum += (studentGrades.get(i) * teamGrades.get(i));
		}
		double avg = ((double)gradeSum/(double)studentGrades.size())/100;
		return (int)Math.round(avg);
	}
	
	public void addAssignment(String title){
		this.openFileForReading();
		XSSFSheet assignmentSheet = this.workBook.getSheetAt(1);
		Iterator<Row> rowIterator = assignmentSheet.iterator();
		Row row = rowIterator.next();
		Cell newCell = row.createCell(row.getLastCellNum());
		newCell.setCellValue(title);
		try {
			this.openFileForWriting();
			this.workBook.write(this.fileOut);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeFileAfterWriting();

		
	}
	private int getRowNumber(XSSFSheet sheet, String name){
		//this.openFileForReading();
		int index = 99;
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next(); //get assignment titles
		Iterator<Cell> columnIterator = row.cellIterator();
	    Cell column = columnIterator.next();
	    
	    while(columnIterator.hasNext()){
	    	column = columnIterator.next();
	    	if (column.getStringCellValue().equals(name)){
	    		index = column.getColumnIndex();
	    		//column.getC
	    	}
	    }
	    
	    return index;
	}
	
	public void addGrades(String title, HashMap<Student, Integer> hashOfGrades, int sheetNum){
		this.openFileForReading();
		
		XSSFSheet sheet = this.workBook.getSheetAt(sheetNum);
		int gradeIndex = this.getRowNumber(sheet, title);
		this.closeFileAfterReading();
		
		for (Student s:hashOfGrades.keySet()){
			this.openFileForReading();
			sheet = this.workBook.getSheetAt(sheetNum);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next(); //get assignment titles
			while(rowIterator.hasNext()) {
				
				//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
				row = rowIterator.next();
				Iterator<Cell> columnIterator = row.cellIterator();
			    Cell column = columnIterator.next();
			    //end borrowed code
			    
			    if (String.format("%.0f", (column.getNumericCellValue())).equals(s.getGtid()))  {
			    	//move to column and change value
			    	
			    	if (row.getCell(gradeIndex) != null){
			    		Cell oldCell = row.getCell(gradeIndex);
			    		System.out.println("Changing " + s.getGtid()+ " and " + title + " from " + oldCell.getNumericCellValue() );

			    		oldCell.setCellValue((double)hashOfGrades.get(s));
			    		System.out.println(oldCell.getNumericCellValue());
				    	//row.getCell(assignmentIndex).setCellValue((double)hashOfGrades.get(s));
			    	}
			    	else{
			    		Cell newCell = row.createCell(gradeIndex);
			    		newCell.setCellValue((double)hashOfGrades.get(s));
			    	}
			    	try {
						this.openFileForWriting();
						this.workBook.write(this.fileOut);
						this.closeFileAfterWriting();
						this.openFileForReading();						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    }

			}
			this.closeFileAfterReading();

		   
		}
	}
	public void update() {
		this.loadAttendanceTable();
		this.loadAssignmentsTable();
		this.loadProjectsTable();
		this.loadTeamProjectsTable();
		
	}
}
