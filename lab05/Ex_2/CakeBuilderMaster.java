abstract class CakeBuilderMaster implements CakeBuilder{
    protected Cake cake;

    public Cake getCake() {return cake;}
    
    public void createCake() { cake = new Cake();}
    
    public abstract void addCreamLayer();
    public abstract void addTopLayer();
    public abstract void addTopping();
}