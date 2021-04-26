class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;



    // Methods to set variables
    public void setCakeShape(Shape shape) {this.shape = shape;}
    public void setCakeLayer(String layer){this.cakeLayer = layer;}
    public void addCakeLayer(){this.numCakeLayers++;}
    public void setmidLayerCream(Cream layer){this.midLayerCream = layer;}
    public void settopLayerCream(Cream layer){this.topLayerCream = layer;}
    public void setTopping(Topping topping){this.topping = topping;}
    public void setMessage(String m){this.message = m;}
}