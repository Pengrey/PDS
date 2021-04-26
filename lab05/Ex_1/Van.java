public class Van implements Vehicle{
    private final int maxPassangers = 4;
    private final int maxVolume = 1000;

    public int getMaxPassangers() {
        return this.maxPassangers;
    }

    public int getMaxVolume() {
        return this.maxVolume;
    }

    @Override
    public String toString() {
        return "Van";
    }
}