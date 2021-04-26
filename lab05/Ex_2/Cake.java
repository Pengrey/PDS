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
    public void setCakeLayers(int layer){this.numCakeLayers = layer;}
    public void setmidLayerCream(Cream layer){this.midLayerCream = layer;}
    public void settopLayerCream(Cream layer){this.topLayerCream = layer;}
    public void setTopping(Topping topping){this.topping = topping;}
    public void setMessage(String m){this.message = m;}
    public int getCakeLayers() {return this.numCakeLayers;}

    public String toString(){
        String result;
        result = this.cakeLayer + " cake with " + this.numCakeLayers + " layers";
        if(this.numCakeLayers > 1)
            result += " and " + this.midLayerCream + " cream";
        result += ", topped with " + this.topLayerCream + " cream and " + this.topping + ". Message says: \"" + this.message + "\".";
        return result;
    }
}