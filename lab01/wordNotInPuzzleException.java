/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class wordNotInPuzzleException extends Exception{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public wordNotInPuzzleException(String errorMessage){
        super(errorMessage);
    }
    
}
