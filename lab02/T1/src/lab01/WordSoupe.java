package lab01;

import java.util.ArrayList;


public class WordSoupe {
	
	private char[][] sopa;
	private int tamanho;
	private Point ponto;
	private char[][] solved;
	private ArrayList<Solution> solutions = new ArrayList<Solution>();
	
	public WordSoupe(int tamanho) {
		this.tamanho = tamanho;
		sopa = new char[tamanho][tamanho];
		solved = new char[tamanho][tamanho];
		int i,j;
		for(i = 0; i < tamanho; i++) {
			for(j = 0; j < tamanho; j++) {
				sopa[i][j] = '.';
				solved[i][j] = '.';
			}
		}
		ponto = new Point(0,0);
	}
	
	public void addPuzzle(ArrayList<String> puzzle) {
		int i,j;
		for(i = 0; i < tamanho; i++) {
			for(j = 0; j < tamanho; j++) {
				sopa[i][j] = puzzle.get(i).charAt(j);
			}
		}
	}
	
	public void solveSolution(Solution sol) {
		Point pStart = sol.getStartingPos();
		Point pEnd = sol.getEndPos().addDir(sol.getDirection());
		int y,x;
		int i = 0;
		
		//System.out.println("START:" + sol.getStartingPos());
		//System.out.println("END:" + sol.getEndPos());
		
		//Meter as palavras na sopa resolvida
		while(!pStart.equals(pEnd)) {
			y = pStart.getY();
			x = pStart.getX();
			//System.out.println("(" + y + "," + x + ")");
			//System.out.println(sol.getDirection());
			solved[y][x] = sol.getWord().charAt(i++);
			pStart = pStart.addDir(sol.getDirection());
		}
	}
	
	public boolean insertSolution(Solution sol) {
		Point pStart = sol.getStartingPos();
		Point pEnd = sol.getEndPos().addDir(sol.getDirection());
		int y,x;
		int i = 0;
		
		// verificar se a palavra dá overlap com alguma solução já presente
		int overlap_flg = 0;
		for (Solution s : solutions) {
			Point startA, startB, endA, endB;
			Direction dirA;
			if (s.getWordSize() > sol.getWordSize()) {
				startA = s.getStartingPos();
				dirA = s.getDirection();
				endA = s.getEndPos().addDir(s.getDirection());
				startB = pStart;
				endB = pEnd;
			}
			else {
				dirA = sol.getDirection();
				startA = pStart;
				endA = pEnd;
				startB = s.getStartingPos();
				endB = s.getEndPos().addDir(sol.getDirection());
			}
			/*System.out.println(sol.getWord());
			System.out.println(s.getWord());
			System.out.println("START: " + startA);
			System.out.println("END: " + endA);
			System.out.println("DIR: " + dirA);*/
			for(Point p = startA; !p.equals(endA); p = p.addDir(dirA) ) {
				//System.out.println(p);
				if(p.equals(startB) || p.equals(endB)) {
					overlap_flg++;
				}
			}
		}
		if (overlap_flg == 2) return false; 
		
		// primeira passagem para verificar se a palavra pode ser inserida
		while(!pStart.equals(pEnd)) {
			y = pStart.getY();
			x = pStart.getX();
			char curr_char = sol.getWord().toUpperCase().charAt(i++);
			
			if (sopa[y][x] != '.' && sopa[y][x] != curr_char) {
				return false;
			}
			pStart = pStart.addDir(sol.getDirection());
		}
		
		// segunda passagem para inserir a palavra na sopa
		i = 0;
		pStart = sol.getStartingPos();
		while(!pStart.equals(pEnd)) {
			y = pStart.getY();
			x = pStart.getX();
			char curr_char = sol.getWord().toUpperCase().charAt(i++);
			sopa[y][x] = curr_char;
			pStart = pStart.addDir(sol.getDirection());
		}
		solutions.add(sol);
		return true;
	}
	
	public void nextPoint() {
		if (ponto.getX() + 1 == tamanho && ponto.getY() + 1 == tamanho) {
			ponto.setX(0);
			ponto.setY(0);
		}
		else if (ponto.getX() + 1 == tamanho) {
			ponto.setX(0);
			ponto.setY(ponto.getY() + 1);
		}
		else {
			ponto.setX(ponto.getX() + 1);
		}
	}
	
	public char getLetter(int y, int x) {
		return sopa[y][x];
	}
	
	public char getLetter(Point p) {
		return sopa[p.getY()][p.getX()];
	}
	

	public int getTamanho() {
		return tamanho;
	}
	
	
	public ArrayList<Solution> getSolutions(){
		return solutions;
	}
	
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public Point getPonto() {
		return ponto;
	}

	public void setPonto(Point ponto) {
		this.ponto = ponto;
	}
	
	public void setLetter(Point p, char c) {
		this.sopa[p.getY()][p.getX()] = c;
	}
	
	
	public void addSolution(Solution sol) {
		solutions.add(sol);
	}
		
	
	public void printSoup() {
		for(int y = 0; y < tamanho; y++) {
			for(int x = 0; x < tamanho; x++) {
				System.out.print(sopa[y][x]);
			}
			System.out.print("\n");
		}
		for(Solution sol : solutions) {
			System.out.print(sol.getWord());
			System.out.print(";");
		}
	}
	
	public void printSolved() {
		int i,j;
		for(i = 0; i < tamanho; i++) {
			for(j = 0; j < tamanho; j++) {
				System.out.print(solved[i][j]);
			}
			System.out.print("\n");
		}
	}
	
}
