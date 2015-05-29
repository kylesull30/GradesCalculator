package edu.gatech.seclass;

public class MyString implements MyStringInterface {
	private String theString;
	private String theStringVowels;
	private int numVowels = 0;
	private static final String VOWELS_LIB = "aAeEiIoOuU";

	
	public MyString(){
		this.theString = new String();
		this.theStringVowels = new String();
	}
	@Override
	public void setString(String string) {
		this.theString = string;
	}

	@Override
	public String getString() {
		return this.theString;
	}

	@Override
	public String getVowels() {
		
		for (int i=0; i < this.theString.length(); i++){
			if (VOWELS_LIB.contains(Character.toString(this.theString.charAt(i)))){
				this.theStringVowels += Character.toString(this.theString.charAt(i));
			}
		}
		
		return this.theStringVowels;
	}

	@Override
	public int numberOfVowels() {
		
		this.numVowels = this.getVowels().length();
		
		return this.numVowels;
	}

	@Override
	public char getCharacter(int position) throws IllegalArgumentException,
			IllegalIndexException {
		if (position <= 0){
			throw new IllegalArgumentException();
		}
		else if (position > this.theString.length()){
			
			throw new IllegalIndexException();
		}
		else{
			return this.theString.charAt(position-1);
		}
	}

	@Override
	public void flipCaseInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, IllegalIndexException {
		
		if (startPosition <= 0 || endPosition <= 0 || startPosition > endPosition){
			throw new IllegalArgumentException();
		}
		else if (this.theString.length() < endPosition){
			throw new IllegalIndexException();
		}
		else{
			String newString = new String();
			String originalSubString = new String();
			
			for (int i=startPosition-1; i < endPosition; i++){
				if(Character.isUpperCase(this.theString.charAt(i))){
					newString += Character.toString(Character.toLowerCase(this.theString.charAt(i)));
				}
				else if (Character.isLowerCase(this.theString.charAt(i))){
					newString += Character.toString(Character.toUpperCase(this.theString.charAt(i)));
				}
				else{
					newString += this.theString.charAt(i);
				}
			}
			
			if (startPosition == 1 && endPosition == this.theString.length()){
				this.theString = newString;
			}
			else if (startPosition == 1 && endPosition < this.theString.length()){
				this.theString = newString + this.theString.substring(endPosition);
			}
			else if (startPosition > 1 && endPosition < this.theString.length()){
				this.theString = this.theString.substring(0, startPosition-1) + newString + this.theString.substring(endPosition);
			}
			else{
				this.theString = this.theString.substring(0, startPosition-1) + newString;
			}
			
		}

	}

}
