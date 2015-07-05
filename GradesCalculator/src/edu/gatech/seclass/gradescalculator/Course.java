package edu.gatech.seclass.gradescalculator;

import java.util.HashSet;

public class Course {
	private Students students;
	private Grades grades;
	
	public Course(Students students, Grades grades){
		this.students = students;
		this.grades = grades;
	}

	public int getNumStudents() {
		return this.students.getNumStudents();
	}

	public int getNumAssignments() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumProjects() {
		// TODO Auto-generated method stub
		return 0;
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

}
