abstract class CakeBuilderMaster implements CakeBuilder{
    protected Cake cake;

    public Cake getCake() {return cake;}
    public void createCake() {cake = new Cake();}

    public void setCakeShape(Shape shape) {cake.setCakeShape(shape);}
    public void addCakeLayer() {cake.addCakeLayer();}
    public void addMessage(String m) {cake.setMessage();}
}