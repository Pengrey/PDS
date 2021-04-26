public class SpongeCakeBuilder extends CakeBuilderMaster{

    public void addCreamLayer() {if(cake.getCakeLayers() > 1) cake.setmidLayerCream(Cream.Red_Berris);}
    public void addTopLayer() {cake.settopLayerCream(Cream.Whipped_Cream) ;}
    public void addTopping() {cake.setTopping(Topping.Fruit);}

    public void addMessage(String string){cake.setMessage(string);}
    public void addCakeLayer(){
        cake.setCakeLayers(cake.getCakeLayers() + 1);
        if(cake.getCakeLayers() == 1) cake.setCakeLayer("Sponge");
    }
    public void setCakeShape(Shape shape){cake.setCakeShape(shape);}
}