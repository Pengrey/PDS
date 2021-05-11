public class Bebida extends Produto{

    public Bebida(String name, double weight) {
        super(name, weight);
    }

    public String toString() {
        return "Bebida " + super.toString();
    }
    
}
