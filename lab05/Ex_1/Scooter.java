public class Scooter implements Vehicle {
    private final int maxPassangers = 1;
    private final int maxVolume = 0;

    public int getMaxPassangers() {
        return this.maxPassangers;
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public String toString() {
        return "Scooter";
    }
}