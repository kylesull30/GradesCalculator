package edu.gatech.seclass.gradescalculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Grades {
	private XSSFWorkbook workBook;
	private FileInputStream file;
	private FileOutputStream fileOut;
	private String gradesDBPath;
	private HashMap<String, Integer> attendanceTable = new HashMap<>();
	private HashMap<String, ArrayList<Integer>> assignments = new HashMap<>();
	private HashMap<String, ArrayList<Integer>> projects = new HashMap<>();
	private HashMap<String, ArrayList<Integer>> teamProjects = new HashMap<>();
	static final int ATTENDANCE_SHEET = 0;
	static final int ASSIGNMENT_SHEET = 1;
	static final int INDIV_PROJ_SHEET = 2;
	static final int GROUP_PROJ_SHEET = 3;
	static final String DEFAULT_FORMULA = "AT * 0.2 + AA * 0.4 + AP * 0.4";
	private String formula;


	
	public Grades(String gradesDB){
			this.gradesDBPath = gradesDB;
			this.formula = DEFAULT_FORMULA;
			this.loadDB();
	}
	
	public void loadDB() {
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
			if (this.file != null) this.file.close();
			this.fileOut = new FileOutputStream(new File(this.gradesDBPath));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
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
		this.projects = this.loadGrades(this.workBook.getSheetAt(INDIV_PROJ_SHEET), false);
		this.closeFileAfterReading();

	}
	
	private void loadTeamProjectsTable() {
		this.openFileForReading();
		this.teamProjects = null;
		this.teamProjects = this.loadGrades(this.workBook.getSheetAt(GROUP_PROJ_SHEET), true);
		this.closeFileAfterReading();

	}

	private void loadAssignmentsTable() {
		this.openFileForReading();
		this.assignments = null;
		this.assignments = this.loadGrades(this.workBook.getSheetAt(ASSIGNMENT_SHEET), false);
		this.closeFileAfterReading();

	}
	private HashMap<String, ArrayList<Integer>> loadGrades(XSSFSheet sheet, boolean isTeamLoad){
		HashMap<String, ArrayList<Integer>> gradesHashMap = new HashMap<>();
		
		Iterator<Row> rowIterator = sheet.iterator();
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
		XSSFSheet attendanceSheet = this.workBook.getSheetAt(ATTENDANCE_SHEET);
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
		
	public int getNumberOf(int sheetNum){
		this.openFileForReading();
		XSSFSheet indivProjectSheet = this.workBook.getSheetAt(sheetNum);
		Iterator<Row> rowIterator = indivProjectSheet.iterator();
		Row row = rowIterator.next();
		int answer = row.getLastCellNum() -1;
		this.closeFileAfterReading();
		return answer;
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
		XSSFSheet assignmentSheet = this.workBook.getSheetAt(ASSIGNMENT_SHEET);
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
	private int getRowNumber(int sheetNum, String name){
		this.openFileForReading();
		XSSFSheet sheet = this.workBook.getSheetAt(sheetNum);
		int index = 99;
		
		Iterator<Row> rowIterator = sheet.iterator();
		Row row = rowIterator.next(); //get assignment titles
		Iterator<Cell> columnIterator = row.cellIterator();
	    Cell column = columnIterator.next();
	    
	    while(columnIterator.hasNext()){
	    	column = columnIterator.next();
	    	if (column.getStringCellValue().equals(name)){
	    		index = column.getColumnIndex();
	    	}
	    }
	    
		this.closeFileAfterReading();

	    return index;
	}
	
	public void addGrades(String title, HashMap<Student, Integer> hashOfGrades, int sheetNum){
		int gradeIndex = this.getRowNumber(sheetNum, title);
		XSSFSheet sheet = null;
		
		for (Student s:hashOfGrades.keySet()){
			this.openFileForReading();

			sheet = this.workBook.getSheetAt(sheetNum);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next(); 
			
			while(rowIterator.hasNext()) {
				//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
				row = rowIterator.next();
				Iterator<Cell> columnIterator = row.cellIterator();
			    Cell column = columnIterator.next();
			    //end borrowed code
			    
			    if (String.format("%.0f", (column.getNumericCellValue())).equals(s.getGtid())){
			    	this.addOrModifyCell(row, gradeIndex, hashOfGrades.get(s));   	
			    }
			}
			this.closeFileAfterReading();
		}
	}
	
	public void addTeamGrades(String title, HashMap<String, Integer> hashOfGrades, int sheetNum){
		int gradeIndex = this.getRowNumber(sheetNum, title);
		XSSFSheet sheet = null;
		
		for (String s:hashOfGrades.keySet()){
			this.openFileForReading();

			sheet = this.workBook.getSheetAt(sheetNum);
			Iterator<Row> rowIterator = sheet.iterator();
			Row row = rowIterator.next(); 
			
			while(rowIterator.hasNext()) {
				//Begin borrowed code from http://viralpatel.net/blogs/java-read-write-excel-file-apache-poi/
				row = rowIterator.next();
				Iterator<Cell> columnIterator = row.cellIterator();
			    Cell column = columnIterator.next();
			    //end borrowed code
			    
			    if (column.getStringCellValue().equals(s)){
			    	this.addOrModifyCell(row, gradeIndex, hashOfGrades.get(s));   	
			    }
			}
			this.closeFileAfterReading();
		}
	}
	
	private void addOrModifyCell (Row row, int gradeIndex, int newGrade){
		if (row.getCell(gradeIndex) != null){ //Modify existing
    		Cell oldCell = row.getCell(gradeIndex);
    		oldCell.setCellValue((double)newGrade);
    	}
    	else{//create new
    		Cell newCell = row.createCell(gradeIndex);
    		newCell.setCellValue((double)newGrade);
    	}
    	
    	try {
			this.openFileForWriting();
			this.workBook.write(this.fileOut);
			this.closeFileAfterWriting();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public String getFormula() {
		return this.formula;
	}
	
	
	private String replaceOperands(String gtID, String team){

		int studentAttendance = this.attendanceTable.get(gtID);
		int assingmentAverage = this.getAverageAssignmentGrade(gtID); 
		int projectAverage = this.getAverageProjectGrade(gtID, team);
		
		String formulaAttendanceReplaced = this.formula.replace("AT", Integer.toString(studentAttendance));
		String formulaAssignmentsReplaced = formulaAttendanceReplaced.replace("AA", Integer.toString(assingmentAverage));
		String formulaProjectsReplaced = formulaAssignmentsReplaced.replace("AP", Integer.toString(projectAverage));
		
		return formulaProjectsReplaced;

	}
	
	public int evaluateFormula(String gtID, String team) {
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("JavaScript");
		String replacedFormula = this.replaceOperands(gtID, team);
		
		try {
			double realFormulaValue = (Double)engine.eval(replacedFormula);
			double roundedFormulaValue = Math.round(realFormulaValue);
			return (int) roundedFormulaValue;
		} catch (ScriptException e) {
			throw new GradeFormulaException("You have entered an invalid grade formula.");
		}

	}

	public void addProject(String title) {
		this.openFileForReading();
		XSSFSheet idivProjSheet = this.workBook.getSheetAt(INDIV_PROJ_SHEET);
		Iterator<Row> rowIterator = idivProjSheet.iterator();
		Row row = rowIterator.next();
		Cell newCell = row.createCell(row.getLastCellNum());
		newCell.setCellValue(title);
		
		XSSFSheet teamProjSheet = this.workBook.getSheetAt(GROUP_PROJ_SHEET);
		Iterator<Row> rowIterator2 = teamProjSheet.iterator();
		Row row2 = rowIterator2.next();
		Cell newCell2 = row2.createCell(row.getLastCellNum());
		newCell2.setCellValue(title);
		
		try {
			this.openFileForWriting();
			this.workBook.write(this.fileOut);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.closeFileAfterWriting();		
	}
	
}
