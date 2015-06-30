package edu.gatech.seclass.assignment8;

public class BuggyClass {

	public BuggyClass(){
		
	}
	
	public int method1(int a){
		
		String str = null;
		
		if(a > 0){
			str = "greater";
		}
		else if (a < 0){
			str = "less";
		}
		
		return str.length();
		
	}
	
	// As per method description, every statement coverage test suite reveals the fault.
	// As per subsumption relationship every branch coverage test suite implies 
	// a particular statement coverage test suite. Therefore every branch coverage
	// test suite must contain a statement coverage test suite that reveals the fault.
	public void method2(){
			
		System.out.println("such a method cannot be created");
		
	}
	
	public int method3(int x, String str){
		String str2 = null;
		
		if (x > 0){
			str2 = str;
		}
		else if (x < 0){
			str2 = str;
		}
		
		return str.length();
	}
}
