//=================Done by=========================
//          Rodrigo Lima nMec 98475
//          Gonçalo Machado nMec 98359
//=================================================

public class Family implements Vehicle{
    private final int maxPassangers = 4;
    private final int maxVolume = 600;

    public int getMaxPassangers() {
        return this.maxPassangers;
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public String toString() {
        return "Family car";
    }
}
