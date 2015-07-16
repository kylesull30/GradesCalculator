package edu.gatech.seclass.gradescalculator;

import java.util.HashMap;

public class Student {
	
	private String name;
	private String gtID;
	private int attendance;
	private String team;
	private String email;// not tested by the CourseTest suite but listed in the story card.

	
	public Student (String name, String gtID, String email){
		this.name = name;
		this.gtID = gtID;
		this.email = email;
	}

	public Student(String name2, String gtID2, Course course) {
		// TODO Auto-generated constructor stub
		this.name = name2;
		this.gtID = gtID2;
	}

	public String getName() {
		return this.name;
	}

	public String getGtid() {
		return this.gtID;
	}

	public int getAttendance() {
		return this.attendance;
	}
	
	public void setAttendance(int attendance){
		this.attendance = attendance;
	}

	public String getTeam() {
		return this.team;
	}
	
	public void setTeam(String team){
		this.team = team;
	}
	
	public String getEmail(){// not tested by the CourseTest suite but listed in the story card.
		return this.email;
	}

}
