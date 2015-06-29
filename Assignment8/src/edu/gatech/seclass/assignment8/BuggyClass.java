package edu.gatech.seclass.assignment8;

public class BuggyClass {

	public BuggyClass(){
		
	}
	
	public int method1(int a){
		
		String str = null;
		
		if(a > 0)
		{
			str = "greater";
		}
		else if (a < 0){
			str = "less";
		}
		
		return str.length();
		
	}
}
