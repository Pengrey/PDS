public class PizzaChef extends Chef {
    
    public void cook(String food){
        if(canCook(food, "pizza")){
            System.out.println("PizzaChef: Starting to cook" + food + ". Out in 7 minutes!");
        }else{
            System.out.println("PizzaChef: I can't cook that.");
            super.cook(food);
        }
    }
}
