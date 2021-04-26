/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class ChocolateCakeBuilder extends CakeBuilderMaster{
    public void addCreamLayer() {if(cake.getCakeLayers() > 1) cake.setmidLayerCream(Cream.Whipped_Cream);}  // If there is more than one layer of cake we have cream between them
    public void addTopLayer() {cake.settopLayerCream(Cream.Whipped_Cream);}                                 // Sets cream for this type of cake                                
    public void addTopping() {cake.setTopping(Topping.Fruit);}                                              // Sets Topping for this type of cake
    public void addMessage(String string){cake.setMessage(string);}                                         // Adds Message to the cake
    public void addCakeLayer(){
        cake.setCakeLayers(cake.getCakeLayers() + 1);                                                       // Adds one more layer to the cake
        if(cake.getCakeLayers() == 1) cake.setCakeLayer("Soft chocolate");                                  // If its the first layer of the cake we define the type of cake
    }
    public void setCakeShape(Shape shape){cake.setCakeShape(shape);}                                        // Sets shape of cake
}