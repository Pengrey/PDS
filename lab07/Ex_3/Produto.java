public class Produto extends ComponentsInterface{

    String name;
    double weight;

    public Produto(String name, double weight){
        this.name = name;
        this.weight = weight;
    }

    public double draw() {
        System.out.println(indent.toString() + this.toString());
        return this.getWeight();
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return " '" + this.getName() + "' - Weight: " + this.getWeight();
    }
    
}
