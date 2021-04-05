package lab01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class WSGenerator {
	
	public static void main(String[] args) {
		// validar argumentos
		int sflag = 0;
		int iflag = 0;
		int oflag = 0;
		int soupDim = 0;
		String rfile = null;
		String wfile = null;
		
		
		for(int i = 0; i < args.length; i += 2) {
			if(i + 1 == args.length) {
				System.out.println("ERROR: Invalid inputs :(");
				System.exit(1);
			}
			switch(args[i]) {
				case ("-s"):
					sflag++;
					if (!args[i + 1].matches("\\d+")) {
						System.out.println("ERROR: size must an integer :(");
						System.exit(1);
					}
					soupDim = Integer.parseInt(args[i + 1]);
					break;
				case ("-i"):
					iflag++;
					rfile = args[i + 1];
					break;
				case ("-o"):
					oflag++;
					wfile = args[i + 1];
					break;
				default:
					System.out.println("ERROR: Invalid Input :(");
					System.out.println("USAGE: WSGenerator -i <words file> -s <size> [-o] [output file]");
					System.exit(1);
			}
		}
		
		if(iflag != 1 || sflag != 1 || oflag > 1) {
			System.out.println("ERROR: Invalid Inputs :(");
			System.out.println("USAGE: WSGenerator -i <words file> -s <size> [-o] [output file]");
			System.exit(1);
		}
		
		
		if (soupDim < 2 || soupDim > 40 ) {
			System.out.println("ERROR: size must be in interval [2, 40] :(");
			System.exit(1);
		}
		
		try {
			// ler lista de palavras
			ArrayList<String> words = new ArrayList<String>();
			Scanner parser = new Scanner(new File(rfile));
			parser.useDelimiter(";|,| |\n|\r\n");
			
			while (parser.hasNext()) {
				String word = parser.next().toLowerCase();
				// verificar se a palavra  repetida
				if (words.contains(word)) {
					parser.close();
					System.out.println("ERROR: duplicate word in file :(");
					System.exit(1);
				}
				else words.add(word);
			}
			parser.close();
			
			WordSoupe soup = new WordSoupe(soupDim); // inicializar sopa
			
			Random rng = new Random();
			for (String word : words) {
				// verificar se a palavra cabe na sopa
				// e se couber na diagonal?
				// dum-dum moment dinis
				if (word.length() > soupDim) {
					System.out.println("ERROR: word too big :(");
					System.exit(1);
				}
				
				boolean set_flag;
				int n_tries = 0;
				do {
					// escolher uma direo aleatria e definir limites de colunas e linhas
					Direction dir = null;
					int rowRange = 0, colRange = 0;
					Point start = null;
					
					switch (rng.nextInt(4)) { // nmero aleatrio entre [0, 4[
						case (0):
							// determinar aleatoriamente o sentido da palavra
							rowRange = soupDim - word.length() + 1;
							colRange = soupDim;
							start = new Point(rng.nextInt(rowRange), rng.nextInt(colRange));
							
							if (rng.nextBoolean()) {
								dir = Direction.UP;
								start.setY(soupDim - start.getY() - 1);
							}
							else dir = Direction.DOWN;
	
							break;
						case (1):
							rowRange = soupDim;
							colRange = soupDim - word.length() + 1;
							start = new Point(rng.nextInt(rowRange), rng.nextInt(colRange));
							
							if (rng.nextBoolean()) {
								dir = Direction.LEFT;
								start.setX(soupDim - start.getX() - 1);
							}
							else dir = Direction.RIGHT;
							
							break;
						case (2):
							rowRange = soupDim - word.length() + 1;
							colRange = soupDim - word.length() + 1;
							start = new Point(rng.nextInt(rowRange), rng.nextInt(colRange));
							
							if (rng.nextBoolean()) {
								dir = Direction.UPLEFT;
								start.setY(soupDim - start.getY() - 1);
								start.setX(soupDim - start.getX() - 1);
							}
							else dir = Direction.DOWNRIGHT;
							
							break;
						case (3):
							rowRange = soupDim - word.length() + 1;
							colRange = soupDim - word.length() + 1;
							start = new Point(soupDim - rng.nextInt(rowRange) - 1,  rng.nextInt(colRange));
							
							if (rng.nextBoolean()) {
								dir = Direction.DOWNLEFT;
								start.setY(soupDim - start.getY() - 1);
								start.setX(soupDim - start.getX() - 1);
							}
							else dir = Direction.UPRIGHT;
							
							break;
						default:
							// what
							System.out.println("ERROR: what");
							System.exit(1);
					}
					
					// random placement
					Solution sol = new Solution(word);
					
					sol.setStartingPos(start);
					sol.setEndPos(new Point(start.getY() + dir.getPonto().getY()*(word.length()-1),
					start.getX() + dir.getPonto().getX()*(word.length()-1)));
					sol.setDirection(dir);
					
					//System.out.println("word len: " + word.length());
					//System.out.println("rowRange: " + rowRange);
					//System.out.println("colRange: " + colRange);
					//System.out.println("rnd Start: " + sol.getStartingPos());
					//System.out.println("rnd End: " + sol.getEndPos());
					//System.out.println("rnd Dir: " + dir);
					
					set_flag = soup.insertSolution(sol);
					n_tries++;
				} while (!set_flag && n_tries < 400);
				
			}
			
			int n_tries = 0;
			//preencher o espao restante da sopa
			while(true) {
				Point endPoint = new Point(0,0);
				
				do {
					if (soup.getLetter(soup.getPonto()) == '.') {
						char letter;
						int low = 65;
						int high = 90;
						
						letter = (char) (rng.nextInt(high - low) + low);
						soup.setLetter(soup.getPonto(), letter);
					}
					soup.nextPoint();		
				}while(!(soup.getPonto().equals(endPoint)));
				soup.nextPoint();
				
				//call with 2nd argumment true for seeing the puzzle solved
				if(WSSolver.solve(soup,false)) {
					break;
				}
				
				//If the off case that the solver can't find a solution
				if(n_tries > 99) {
					System.out.println("Can't generate solution :(");
					System.exit(1);
				}
				n_tries++;
			} 

			if( oflag == 1) {
				PrintWriter saveFile = null;
				
				if (!wfile.endsWith(".txt")) {
					wfile += ".txt";
				}
				saveFile = new PrintWriter(wfile);
				
				for(int y = 0; y < soup.getTamanho(); y++) {
					for(int x = 0; x < soup.getTamanho(); x++) {
						saveFile.write(soup.getLetter(y, x));
					}
					saveFile.write("\n");
				}
				for(Solution sol : soup.getSolutions()) {
					saveFile.write(sol.getWord());
					saveFile.write(";");
				}
				
				saveFile.close();
			}
			else {
				soup.printSoup();
			}
		}
		catch (FileNotFoundException e) {
			System.err.print("ERROR: file not found :(");
			System.exit(1);
		}
		
	}
}
