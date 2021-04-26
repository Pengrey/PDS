/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class Place {
    String place;

    public Place(String place){
        this.place = place;
    }

    @Override
    public String toString() {
        return " " + this.place;
    }
}
