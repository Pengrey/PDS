public class YogurtCakeBuilder extends CakeBuilderMaster{

    public void addCreamLayer() {if(cake.getCakeLayers() > 1) cake.setmidLayerCream(Cream.Vanilla);}
    public void addTopLayer() {cake.settopLayerCream(Cream.Red_Berris) ;}
    public void addTopping() {cake.setTopping(Topping.Chocolate);}

    public void addMessage(String string){cake.setMessage(string);}
    public void addCakeLayer(){
        cake.setCakeLayers(cake.getCakeLayers() + 1);
        if(cake.getCakeLayers() == 1) cake.setCakeLayer("Yogurt");
    }
    public void setCakeShape(Shape shape){cake.setCakeShape(shape);}
}