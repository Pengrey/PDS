/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

class CakeMaster{
    private CakeBuilder cakeBuilder;

    // Selects right builder to use
    public void setCakeBuilder(CakeBuilder cb){
        cakeBuilder = cb;
    }

    // Return cake built
    public Cake getCake() {
        return cakeBuilder.getCake();
    }

    // Construction with full selection
    public void createCake(Shape shape, int layers, String message) {
        cakeBuilder.createCake();                   // Creation of cake
        cakeBuilder.setCakeShape(shape);            // Sets cake shape
        for (int i = 0 ; i < layers ; i++)          // For loop on layers number
            cakeBuilder.addCakeLayer();             // Sets number of layers 
        cakeBuilder.addMessage(message);            // Sets message to be shown on cake
        cakeBuilder.addCreamLayer();                // Adds cream layers
        cakeBuilder.addTopLayer();                  // Adds top layers
        cakeBuilder.addTopping();                   // Adds topping
    }

    // Construction with 2 options selected
    public void createCake(int layers, String message) {
        cakeBuilder.createCake();                   // Creation of cake
        cakeBuilder.setCakeShape(Shape.Circle);     // Sets cake shape
        for (int i = 0 ; i < layers ; i++)          // For loop on layers number
            cakeBuilder.addCakeLayer();             // Sets number of layers 
        cakeBuilder.addMessage(message);            // Sets message to be shown on cake
        cakeBuilder.addCreamLayer();                // Adds cream layers
        cakeBuilder.addTopLayer();                  // Adds top layers
        cakeBuilder.addTopping();                   // Adds topping
    }

    // Construction with 1 option selected
    public void createCake(String message) {
        cakeBuilder.createCake();                   // Creation of cake
        cakeBuilder.setCakeShape(Shape.Circle);     // Sets cake shape
        cakeBuilder.addCakeLayer();                 // Sets number of layers 
        cakeBuilder.addMessage(message);            // Sets message to be shown on cake
        cakeBuilder.addCreamLayer();                // Adds cream layers
        cakeBuilder.addTopLayer();                  // Adds top layers
        cakeBuilder.addTopping();                   // Adds topping
    }

    // Construction with no options selected
    public void createCake() {
        cakeBuilder.createCake();                   // Creation of cake
        cakeBuilder.setCakeShape(Shape.Circle);     // Sets cake shape
        cakeBuilder.addCakeLayer();                 // Sets number of layers 
        cakeBuilder.addMessage("Congrats");         // Sets message to be shown on cake
        cakeBuilder.addCreamLayer();                // Adds cream layers
        cakeBuilder.addTopLayer();                  // Adds top layers
        cakeBuilder.addTopping();                   // Adds topping
    }
}