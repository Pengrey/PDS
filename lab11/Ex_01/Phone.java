public class Phone {
    private double price;
    private double memory;
    private double camera;
    private String processor;

    public Phone(double pri, double mem, double cam, String pro){
        this.price = pri;
        this.memory = mem;
        this.camera = cam;
        this.processor = pro;
    }

    public double getCamera() {
        return camera;
    }
    
    public double getMemory() {
        return memory;
    }

    public double getPrice() {
        return price;
    }

    public String getProcessor() {
        return processor;
    }
    
    @Override
    public String toString() {
        return "Price: " + price + " Memory: " + memory + " Camera: " + camera + " Processor: " + processor;
    }
}
