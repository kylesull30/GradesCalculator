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
	
	/* 
	* As per method description, every 100% statement coverage test suite reveals the fault.
	* As per subsumption relationship every 100% branch coverage test suite implies 
	* a particular 100% statement coverage test suite. The description implies
	* that all statement coverage test suites for this method reveal the fault.
	* Therefore every branch coverage test suite subsumes a statement coverage test suite 
	* that reveals the fault.
	* */ 
	
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
