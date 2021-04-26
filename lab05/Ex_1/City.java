public class City implements Vehicle {
    private final int maxPassangers = 3;
    private final int maxVolume = 250;

    public int getMaxPassangers() {
        return this.maxPassangers;
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public String toString() {
        return "City car";
    }
}
