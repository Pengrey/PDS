public abstract class Chef{

    private Chef sucChef = null;

    public void cook(String food){
        if(sucChef != null){
            sucChef.cook(food);
        }else{
            System.out.println("We're sorry but that request can't be satisfied by our service!");
        }
    }

    protected boolean canCook(String food, String specialty){
        return (food == null) || (food.toLowerCase().contains(specialty.toLowerCase()));
    }

    public Chef setSucChef(Chef sucChef) {
        this.sucChef = sucChef;
        return this;
    }
}