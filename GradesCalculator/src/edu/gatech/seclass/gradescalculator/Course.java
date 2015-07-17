package edu.gatech.seclass.gradescalculator;

import java.util.HashMap;
import java.util.HashSet;

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
		//return grades.getNumAssignments();
		return grades.getNumberOf(Grades.ASSIGNMENT_SHEET);

	}

	public int getNumProjects() {
		//return grades.getNumProjects();
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

	public void addProject(String string) {
		// TODO Auto-generated method stub
		
	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		
	}

	public void updateStudents(Students students) {
		// TODO Auto-generated method stub
		
	}

	public void addTeamGrades(String projectName,
			HashMap<String, Integer> grades) {
		// TODO Auto-generated method stub
		
	}

}
