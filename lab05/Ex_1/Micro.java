/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class Micro implements Vehicle {
    private final int maxPassangers = 1;
    private final int maxVolume = 250;

    public int getMaxPassangers() {
        return this.maxPassangers;
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public String toString() {
        return "Micro car";
    }
    
}
