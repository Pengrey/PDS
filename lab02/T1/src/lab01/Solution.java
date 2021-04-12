package lab01;

public class Solution {
	
	private String word;
	private int wordSize;
	private Point startingPos;
	private Point endPos;
	private Direction direction;
	private boolean present;
	private boolean palindrome;
	private int count;
	
	public Solution(String word) {
		this.word = word;
		wordSize = word.length();
		present = false;
		
		String rev = new StringBuilder(word).reverse().toString();
		palindrome = this.word.equalsIgnoreCase(rev);
		count = 0;
		
	}


	@Override
	public String toString() {
		String s = String.format("%-14s %2d (%d,%d) %-10s",word,wordSize,startingPos.getY(),startingPos.getX(),direction);
		return  s;
	}


	public boolean isPresent() {
		return present;
	}


	public void setPresent(boolean present) {
		this.present = present;
	}


	public int getCount() {
		return count;
	}
	
	public void incrementCount() {
		count++;
	}


	public String getWord() {
		return word;
	}


	public void setWord(String word) {
		this.word = word;
		this.wordSize = word.length();
	}


	public int getWordSize() {
		return wordSize;
	}


	public Point getStartingPos() {
		return startingPos;
	}


	public void setStartingPos(Point startingPos) {
		this.startingPos = startingPos;
	}


	public Direction getDirection() {
		return direction;
	}


	public void setDirection(Direction direction) {
		this.direction = direction;
	}


	public Point getEndPos() {
		return endPos;
	}

	// no me parece que isto deva ser possvel, o endPos  determinado pelo startingPos, direction, e wordSize -diogo
	// Na resoluo da sopa  determinado o endPos da palavra, por isso d jeito ter este setter para no ter de estar a calcular de novo dentro da classe
	public void setEndPos(Point endPos) {
		this.endPos = endPos;
	}


	public boolean isPalindrome() {
		return palindrome;
	}	
	
}
