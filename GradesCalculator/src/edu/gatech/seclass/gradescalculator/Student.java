package edu.gatech.seclass.gradescalculator;

public class Student {
	
	private String name;
	private String gtID;
	private int attendance;
	private String team;
	
	/*public Student (String name, String gtID, int attendance, String team){
		this.name = name;
		this.gtID = gtID;
		this.attendance = attendance;
		this.team = team;
	}*/
	
	public Student (String name, String gtID){
		this.name = name;
		this.gtID = gtID;
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

}
