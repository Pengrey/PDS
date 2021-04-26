class CakeMaster{
    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cb){
        cakeBuilder = cb;
    }

    public Cake getCake() {
        return cakeBuilder.getCake();
    }


    public void createCake(Shape shape, int layers, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(shape);
        for (int i = 0 ; i < layers ; i++)
            cakeBuilder.addCakeLayer();
        cakeBuilder.addMessage(message);
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
    }

    public void createCake(int layers, String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.Circle);
        for (int i = 0 ; i < layers ; i++)
            cakeBuilder.addCakeLayer();
        cakeBuilder.addMessage(message);
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
    }

    public void createCake(String message) {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.Circle);
        cakeBuilder.addCakeLayer();
        cakeBuilder.addMessage(message);
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
    }

    public void createCake() {
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape.Circle);
        cakeBuilder.addCakeLayer();
        cakeBuilder.addMessage("Congrats");
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
    }
}