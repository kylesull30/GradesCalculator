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
		return grades.getNumAssignments();
	}

	public int getNumProjects() {
		return grades.getNumProjects();
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

	public void updateGrades(Grades grades2) {
		this.grades.update();
	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> newGrades) {
		this.grades.addGrades(assignmentName, newGrades, 1);
		
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
		this.grades.addGrades(projectName, contributions, 2);
		
	}

}
