import java.util.ArrayList;

public class Caixa extends ComponentsInterface {

    private ArrayList<ComponentsInterface> components;
    private double weight;
    private String name;

    public Caixa(String name, double weight){
        this.name = name;
        this.weight = weight;
        this.components = new ArrayList<ComponentsInterface>();
    }

    public void add(ComponentsInterface component){
        this.components.add(component);
    }

    public String getName() {
        return name;
    }

    public double getBoxWeight(){
        return this.weight;
    }

    public double getWeight() {
        double totalWeight = getBoxWeight();
        for (ComponentsInterface component : components) {
            totalWeight += component.getWeight();
        }
        return totalWeight;
    }

    public void draw(){
        System.out.println(indent.toString() +  "* Caixa '" + this.getName() + "' [ Weight: " + this.getBoxWeight() + " ; Total: " + this.getWeight() + "]");
        indent.append("   ");
        for (ComponentsInterface component : components) {
            component.draw();
        }
        indent.setLength(indent.length() - 3);
    }
}