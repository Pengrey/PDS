public class JGaloImplementation implements JGaloInterface {
    private char player1;
    private char player2;
    private char currentPlayer;
    private char[][] puzzle = new char[3][3];
    private int plays;

    public JGaloImplementation(){
        this.player1 = 'X';
        this.player2 = 'O';
        this.currentPlayer = player1;
        this.plays = 0;
    }

    public JGaloImplementation(char player1){
        this.player1 = player1;
        this.currentPlayer = player1;
        if(player1 == 'X'){
            this.player2 = 'O';
        } else{
            this.player2 = 'X';
        }
        this.plays = 0;
    }

    public char getActualPlayer(){
        return currentPlayer;
    }

    public boolean setJogada(int lin, int col){
        // Check if position player wants to play is empty
        if(!Character.isLetter(this.puzzle[lin-1][col-1])){
            // Occupy position
            this.puzzle[lin-1][col-1] = this.currentPlayer;
            plays++;
            return true;
        }
        return false;
    }

    public boolean isFinished(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(this.puzzle[i][j] == this.currentPlayer){
                    // Check in each direction if there is a winner
                    for(Direction d: Direction.values()){
                        if(verifyDirection(d, 1, i, j)){
                            return true;
                        }
                    }
                }
            }
        }
        // Check if all positions are occupied
        if(plays == 9){
            currentPlayer = ' ';
            return true;
        }
        // Switch player if there isn't a final result yet
        currentPlayer = ((currentPlayer == player1) ? player2 : player1);
        return false;
    }
    public char checkResult(){
        return currentPlayer;
    }

    private boolean verifyDirection(Direction d, int searchCharIndex, int l, int c) {

        int row = d.getRowDirection();
        int column = d.getColDirection();

        if (searchCharIndex < 3) {
            // Condition for boundaries
            boolean isRowOutOfBound = l + row < 0 || l + row >= 3;
            boolean isColOutOfBound = c + column < 0 || c + column >= 3;

            if (isRowOutOfBound || isColOutOfBound) {
                return false;
            }
            // Continue analyzing current direction if it continues to have the same symbol
            if (this.puzzle[l + row][c + column] == this.currentPlayer) {
                searchCharIndex++;
                return verifyDirection(d, searchCharIndex, l + row, c + column);
            }
            return false;
        }
        return true;
    }
}
