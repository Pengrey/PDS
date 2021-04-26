/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

abstract class CakeBuilderMaster implements CakeBuilder{
    protected Cake cake;

    public Cake getCake() {return cake;}            // Get the final cake   
    
    public void createCake() { cake = new Cake();}  // Create cake to work on top
    
    // Methods later used
    public abstract void addCreamLayer();
    public abstract void addTopLayer();
    public abstract void addTopping();
}