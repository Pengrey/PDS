package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Soup {
	private int size;
	private int max_size = 40;
	private char[][] grid;
	private ArrayList<String> solutions = new ArrayList<String>();
	
	// creates word search from file
	public Soup(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		String curr_line;
		
		// read first line and get size
		curr_line = scanner.nextLine();
		size = curr_line.length();
		// check if size exceeds max size
		if (size > max_size) {
			System.out.println(String.format("Max size (%dx%d) exceded >:(", max_size, max_size));
			System.exit(1);
		}
		grid = new char[size][size];
		
		// process first line
		processLine(curr_line, 0);
		// scan remaining lines in puzzle
		for (int i = 1; i < size; i++) {
			processLine(scanner.nextLine(), i);
		}
		
		// get solutions
		scanner.useDelimiter(";|,| |\r\n|\n");
		while (scanner.hasNext()) {
			String word = scanner.next();
			// check if word is composed of alphabetical characters
			if (!word.matches("[a-zA-Z]*")) {
				System.out.println("Invalid characters found in words (non-alphabetical) >:(");
				System.exit(1);
			}
			// check if word is entirely uppercase
			if (word.matches("[A-Z]*")) {
				System.out.println("Invalid word (all uppercase) >:(");
				System.exit(1);
			}
			solutions.add(word);
		}
		scanner.close();
	}
	// creates new empty word search with given size
	public Soup(int dim) {
		// check if dim exceeds max size
		if (dim > max_size) {
			System.out.println(String.format("Max size (%dx%d) exceded >:(", max_size, max_size));
			System.exit(1);
		}
		
		size = dim;
		grid = new char[dim][dim];
	}
	
	private void processLine(String line, int count) {
		// check if the line is empty
		if (line.isEmpty()) {
			System.out.println("Invalid line found in puzzle (blank line) >:(");
			System.exit(1);
		}
		
		// check if line size is correct
		if (line.length() != size) {
			System.out.println("Invalid line found in puzzle >:(");
			System.exit(1);
		}
		// check if line is composed of uppercase alphabetical characters
		if (!line.matches("[A-Z]*")) {
			System.out.println("Invalid characters found in puzzle (lowercase or non-alphabetical) >:(");
			System.exit(1);
		}
		// add line to grid
		grid[count] = line.toCharArray();
	}
	
	@Override
	public String toString() {
		// there's something that concatenates more efficiently but i don't remember it
		String grid_string = "";
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				grid_string += grid[i][j];
			}
			grid_string += "\n";
		}
		return grid_string + solutions;
	}
}
