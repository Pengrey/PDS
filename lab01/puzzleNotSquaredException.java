/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class puzzleNotSquaredException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public puzzleNotSquaredException(String errorMessage){
        super(errorMessage);
    }
}
