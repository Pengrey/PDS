class CakeMaster{
    private CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cb){
        cakeBuilder = cb;
    }

    public Cake getCake() {
        return cakeBuilder.getCake();
    }

    public void constructCake(){
        cakeBuilder.createCake();
        cakeBuilder.setCakeShape(Shape shape);
        cakeBuilder.addCakeLayer();
        cakeBuilder.addCreamLayer();
        cakeBuilder.addTopLayer();
        cakeBuilder.addTopping();
        cakeBuilder.addMessage(String m);
    }

    public void createCake(int i, String string) {
    }

}