package galo;

public class Game implements JGaloInterface {
    private int[][] Jogadas;
    private char Jogador, Resultado;
    private int L1, L2, L3, V1, V2, V3, D1, D2; 
    public Game(char turn){
        this.Jogadas = new int[3][3];
        this.Jogador = turn;
        this.Resultado = ' ';
    }

	public char getActualPlayer(){
        return this.Jogador;
	}

	public boolean setJogada(int lin, int col){
		if(Jogadas[lin - 1][col - 1] == 0){
            if(this.getActualPlayer() == 'X'){
			    Jogadas[lin - 1][col - 1] = 1;
                this.Jogador = 'O';
            }else{
                Jogadas[lin - 1][col - 1] = -1;
                this.Jogador = 'X';
            }
			return true;
		}else{
			return false;
		}
	}

	public boolean isFinished(){
        Boolean ended = true;
        L1 = this.Jogadas[0][0] + this.Jogadas[0][1] + this.Jogadas[0][2];
        L2 = this.Jogadas[1][0] + this.Jogadas[1][1] + this.Jogadas[1][2];
        L3 = this.Jogadas[2][0] + this.Jogadas[2][1] + this.Jogadas[2][2];
        V1 = this.Jogadas[0][0] + this.Jogadas[1][0] + this.Jogadas[2][0];
        V2 = this.Jogadas[0][1] + this.Jogadas[1][1] + this.Jogadas[2][1];
        V3 = this.Jogadas[0][2] + this.Jogadas[1][2] + this.Jogadas[2][2];
        D1 = this.Jogadas[0][0] + this.Jogadas[1][1] + this.Jogadas[2][2];
        D2 = this.Jogadas[2][0] + this.Jogadas[1][1] + this.Jogadas[0][2];
        if(L1 == 3 || L2 == 3 || L3 == 3 || D1 == 3 || D2 == 3 || V1 == 3 || V2 == 3 || V3 == 3) this.Resultado = 'X'; 
        if(L1 == -3 || L2 == -3 || L3 == -3 || D1 == -3 || D2 == -3 || V1 == -3 || V2 == -3 || V3 == -3) this.Resultado = 'O';
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(this.Jogadas[i][j] == 0) ended = false;
            }
        }
        if(this.Resultado != ' ') ended = true;
		return ended;
	}

	public char checkResult(){
        return this.Resultado;
	}
}