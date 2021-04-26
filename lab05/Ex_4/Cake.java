class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;


    
    public void setCakeShape(Shape shape) {this.shape = shape;}
    public void addCakeLayer(){this.numCakeLayers++;}
    public void addTopping(){this.topping = topping;}
    public void addMessage(String m){this.message = m;}
}