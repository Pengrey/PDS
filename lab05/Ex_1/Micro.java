//=================Done by=========================
//          Rodrigo Lima nMec 98475
//          Gonçalo Machado nMec 98359
//=================================================

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