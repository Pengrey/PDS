package galo;

/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class Game implements JGaloInterface {
    private int[][] Jogadas;
    private char Jogador, Resultado;
    private int L1, L2, L3, V1, V2, V3, D1, D2; 

    /* Create object Game for later fill with plays, turns and a result*/
    public Game(char turn){
        this.Jogadas = new int[3][3];
        this.Jogador = turn;
        this.Resultado = ' ';
    }

    /* Get player of the current turn*/
	public char getActualPlayer(){
        return this.Jogador;
	}

    /* Sets plays by player and returns if the play can be made also switches turn to the next player*/
	public boolean setJogada(int lin, int col){
		if(Jogadas[lin - 1][col - 1] == 0){                                 // Check if play can be made
            if(this.getActualPlayer() == 'X'){                              // Check if players turn is X
			    Jogadas[lin - 1][col - 1] = 1;                              // Sets play to turn X
                this.Jogador = 'O';                                         // Switches turn to next player
            }else{
                Jogadas[lin - 1][col - 1] = -1;                             // Sets play to turn O
                this.Jogador = 'X';                                         // Switches turn to next player
            }
			return true;                                                    // Returns true if play can be made by player
		}else{
			return false;                                                   // Returns false if play cant be made by player
		}
	}

	public boolean isFinished(){
        Boolean ended = true;
        // Get results from horizontal plays
        L1 = this.Jogadas[0][0] + this.Jogadas[0][1] + this.Jogadas[0][2];
        L2 = this.Jogadas[1][0] + this.Jogadas[1][1] + this.Jogadas[1][2];
        L3 = this.Jogadas[2][0] + this.Jogadas[2][1] + this.Jogadas[2][2];

        // Get results from vertical plays
        V1 = this.Jogadas[0][0] + this.Jogadas[1][0] + this.Jogadas[2][0];
        V2 = this.Jogadas[0][1] + this.Jogadas[1][1] + this.Jogadas[2][1];
        V3 = this.Jogadas[0][2] + this.Jogadas[1][2] + this.Jogadas[2][2];

        // Get results from diagonal plays
        D1 = this.Jogadas[0][0] + this.Jogadas[1][1] + this.Jogadas[2][2];
        D2 = this.Jogadas[2][0] + this.Jogadas[1][1] + this.Jogadas[0][2];

        // Checks if player X has won the game in any given way of winning
        if(L1 == 3 || L2 == 3 || L3 == 3 || V1 == 3 || V2 == 3 || V3 == 3 || D1 == 3 || D2 == 3) this.Resultado = 'X'; 

        // Checks if player O has won the game in any given way of winning
        if(L1 == -3 || L2 == -3 || L3 == -3 || V1 == -3 || V2 == -3 || V3 == -3 || D1 == -3 || D2 == -3) this.Resultado = 'O';

        // Checks if game can have at least one more play to be made
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                if(this.Jogadas[i][j] == 0) ended = false;                  // If the game can have at least one more play to be made in might continue
            }
        }

        // Check if a player has alredy won the game
        if(this.Resultado != ' ') ended = true;
		return ended;
	}

    /* Get the current result of the game made*/
	public char checkResult(){
        return this.Resultado;
	}
}
