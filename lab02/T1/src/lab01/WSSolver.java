package lab01;
import java.util.*;
import java.io.*;

public class WSSolver {
	
	public static boolean complementing(Direction dirA, Direction dirB) {
		int y = Math.abs(dirA.getPonto().getY()) - Math.abs(dirB.getPonto().getY());
		int x = Math.abs(dirA.getPonto().getX()) - Math.abs(dirB.getPonto().getX());
		
		double amp = Math.sqrt(Math.pow(y,2) + Math.pow(x,2));
		
		
		if( amp == 0 || amp == 2) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	public static boolean overlap(Point startA, Point startB, Point endA, Point endB, Direction dirA, Direction dirB) {
		
		int flg = 0;
		
		if(!complementing(dirA,dirB)) {
			return false;
		}
		else {
			for(Point p = startA; !p.equals(endA.addDir(dirA)); p = p.addDir(dirA) ) {
				if(p.equals(startB) || p.equals(endB)) {
					flg++;
				}
			}
			return flg == 2;
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		//Verificação de argumentos
		if (args.length < 1 || args.length > 2) {
			System.exit(1);
		}
		
		
		
		//Abrir e ler ficheiros
		Scanner file = null;
		try{
			System.out.println(args[0]);
			file = new Scanner(new File(args[0]));
			file.useDelimiter("\r\n|;|,| |\n");
		}
		catch(IOException e){
			System.out.println("Ocorreu um erro");
			System.exit(1);
		}
		
		String currentLine = file.next();		//Linha a analisar
		//Verificar a 1ª linha
		if(!currentLine.equals(currentLine.toUpperCase())){
			System.out.println("Sopa de letras inválida >:(");
			System.exit(1);
		}
		
		
		int puzzleSize = currentLine.length();  //Tamanho do puzzle
		
		//Validar o tamanho do puzzle
		if (puzzleSize > 40) {
			System.out.println("Sopa de letras Enorme >:(");
			System.exit(1);
		}
		
		ArrayList<String> puzzle = new ArrayList<String>();		//Array com todas as linhas do puzzle
		puzzle.add(currentLine);								//Adicionar 1ª linha
		int count = 1;											//Contador para ver quantas linhas já foram analizadas
		
		while(file.hasNext()){
			currentLine = file.next();
			//Se a linha não realiza os critérios para pertencer ao puzzle
			if( !(currentLine.equals(currentLine.toUpperCase()) && currentLine.length() == puzzleSize) ){
				break;
			}
			else {
				puzzle.add(currentLine);
			}
			count++;
		}
		
		//Verificar se o puzzle é quadrado
		if (count != puzzleSize) {
			System.out.println("Sopa de letras não é quadrada >:(");
			System.exit(1);
		}
		
		//Gerar sopa
		WordSoupe sopa = new WordSoupe(puzzleSize);
		
		//Array com todas as soluções
		while(file.hasNext()){
			//Validar as soluções
			if(currentLine.equals(currentLine.toUpperCase())){
				System.out.println("Solução Inválida (Letras Maiúsculas) >:(");
				System.exit(1);
			}
			else {
				sopa.addSolution( new Solution(currentLine));
			}
			currentLine = file.next();
		}
		
		//Validar as soluções
		if(currentLine.equals(currentLine.toUpperCase())){
			System.out.println("Solução Inválida (Letras Maiúsculas) >:(");
			System.exit(1);
			
		}
		else {
			sopa.addSolution( new Solution(currentLine));
		}
	
		//Adicionar o puzzle
		sopa.addPuzzle(puzzle);
		
		solve(sopa,true);
		
	}
		
		public static boolean solve(WordSoupe sopa , boolean pflg) {
			//
			// Resolver a Sopa
			//
			//sopa.sortSolutions();
			ArrayList<Solution> solutions = new ArrayList<Solution>(sopa.getSolutions());
			Collections.sort(solutions, Comparator.comparing(Solution::getWordSize));
			Collections.reverse(solutions);
			
			/*for(Solution sol: sopa.getSolutions()) {
				System.out.println(sol.getWord());
			}*/
			
			Point endPoint = new Point(sopa.getTamanho()-1,sopa.getTamanho()-1); //Ponto para sinalizar o fim da sopa de letras	
			
			int n = 0;
	
			
			for ( Solution sol : solutions) {
				//System.out.println(sol.getWord());
				while(!(sopa.getPonto().equals(endPoint))) {
					for( Direction dir : Direction.values()) {
						Point trans = new Point(sopa.getPonto().getY() + dir.getPonto().getY()*(sol.getWordSize()-1),
								sopa.getPonto().getX() + dir.getPonto().getX()*(sol.getWordSize()-1));
						
						if(trans.getY() < sopa.getTamanho() 
								&& trans.getX() < sopa.getTamanho() 
								&& trans.getY() >= 0 
								&& trans.getX() >= 0) {
							
							String w = ""; //String a avaliar
							for(int i = 0; i < sol.getWordSize(); i++) {
								w = w + sopa.getLetter(sopa.getPonto().getY() + dir.getPonto().getY()*i,
										sopa.getPonto().getX() + dir.getPonto().getX()*i);
							}
							
							//Se encontrou uma palavra que dê match
							if(w.equalsIgnoreCase(sol.getWord())) {	
								//Verificar se a palavra encontrada dá overlap com outras
								boolean overlapping = false;
								for(int i = 0; i < n; i++) {
									overlapping = overlap(solutions.get(i).getStartingPos(),
											sopa.getPonto(),solutions.get(i).getEndPos(),
											trans,solutions.get(i).getDirection(),dir);
									if(overlapping) {
										break;
									}
								}
								//Se a solução já estiver presente, não for um palindromo e
								//a palavra encontrada não estiver a dar overlap -> solução duplicada
								if(sol.isPresent() && (sol.isPalindrome() && sol.getCount() > 2) && !overlapping) {
									System.out.println("Solução duplicada >:(");
									//System.out.println("sol: " + sol);
									//sopa.printSoup();
									//System.out.println();
									//sopa.printSolved();
									//System.out.println(sopa.getPonto());
									return false;
								}
								//Se a solução não está presente ou é um palindromo válido e não há overlap
								else if (!overlapping && (!sol.isPresent() || (sol.isPalindrome() && sol.getCount() == 1))) {
										sol.setPresent(true);
										Point p = new Point(sopa.getPonto());
										sol.setStartingPos(p);
										sol.setDirection(dir);
										sol.setEndPos(trans);
										sopa.solveSolution(sol);
										sol.incrementCount();
								}
							}
						}
					}
					sopa.nextPoint();
				}
				sopa.nextPoint();
				n++;
				
				if(!sol.isPresent()) {
					System.out.println("Solution not Found");
					System.exit(1);
				}
				
			}
			if(pflg) {
				for (Solution sol : sopa.getSolutions()) {
					System.out.println(sol);
				}
				sopa.printSolved();
			}
				
			return true;
			
	}
}

