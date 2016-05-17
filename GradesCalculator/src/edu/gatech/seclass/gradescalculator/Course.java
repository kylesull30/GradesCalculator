package edu.gatech.seclass.gradescalculator;

import java.util.HashMap;
import java.util.HashSet;

import javax.script.ScriptException;

public class Course {
	private Students students;
	private Grades grades;	
	
	public Course(Students students, Grades grades){
		this.students = students;
		this.grades = grades;
		this.processAttendance();
	}
	
	private void processAttendance(){
		HashSet<Student> studentsRoster = this.students.getAllStudents();
		for (Student s : studentsRoster) {
            s.setAttendance(grades.getAttendanceById(s.getGtid()));
        }
	}

	public int getNumStudents() {
		return this.students.getNumStudents();
	}

	public int getNumAssignments() {
		return grades.getNumberOf(Grades.ASSIGNMENT_SHEET);

	}

	public int getNumProjects() {
		return grades.getNumberOf(Grades.INDIV_PROJ_SHEET);
	}

	public HashSet<Student> getStudents() {
		return this.students.getAllStudents();
	}

	public Student getStudentByName(String name) {
		return this.students.getByName(name);
	}

	public Student getStudentByID(String id) {
		return this.students.getById(id);
	}

	public void addAssignment(String title) {
		this.grades.addAssignment(title);
	}

	public void updateGrades(Grades newGrades) {
		this.grades.loadDB();
	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> newGrades) {
		this.grades.addGrades(assignmentName, newGrades, Grades.ASSIGNMENT_SHEET);
		
	}

	public int getAverageAssignmentsGrade(Student student) {
		return this.grades.getAverageAssignmentGrade(student.getGtid());
	}

	public int getAverageProjectsGrade(Student student) {
		String team = this.getStudentByID(student.getGtid()).getTeam();
		return this.grades.getAverageProjectGrade(student.getGtid(), team);
	}

	public void addIndividualContributions(String projectName,
			HashMap<Student, Integer> contributions) {
		this.grades.addGrades(projectName, contributions, Grades.INDIV_PROJ_SHEET);
		
	}

	public void addProject(String project) {
		this.grades.addProject(project);
	}

	public void addStudent(Student student) {
		this.students.addStudent(student);
	}

	public void updateStudents(Students students) {
		this.students.updateDB();
	}

	public void addTeamGrades(String projectName,
			HashMap<String, Integer> grades) {
		this.grades.addTeamGrades(projectName, grades, Grades.GROUP_PROJ_SHEET);
	}

	public String getFormula() {
		return this.grades.getFormula();
	}

	public void setFormula(String formula) {
		this.grades.setFormula(formula);
	}

	public String getEmail(Student student) {
		return student.getEmail();
	}

	public int getAttendance(Student student) {
		return student.getAttendance();
	}

	public int getOverallGrade(Student student)throws GradeFormulaException {
			
		return grades.evaluateFormula(student.getGtid(), this.getStudentByID(student.getGtid()).getTeam());
			
		
	}

	public String getTeam(Student student) {
		return student.getTeam();
	}

}
