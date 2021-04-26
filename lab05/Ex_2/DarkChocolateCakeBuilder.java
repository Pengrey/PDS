public class DarkChocolateCakeBuilder extends CakeBuilderMaster{

    public void addCreamLayer() {if(cake.getCakeLayers() > 1) cake.setmidLayerCream(Cream.Whipped_Cream);}
    public void addTopLayer() {cake.settopLayerCream(Cream.Vanilla) ;}
    public void addTopping() {cake.setTopping(Topping.Chocolate);}

    public void addMessage(String string){cake.setMessage(string);}
    public void addCakeLayer(){
        cake.setCakeLayers(cake.getCakeLayers() + 1);
        if(cake.getCakeLayers() == 1) cake.setCakeLayer("Dark Chocolate");
    }
    public void setCakeShape(Shape shape){cake.setCakeShape(shape);}
}