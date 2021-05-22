public class DessertChef extends Chef{
    
    public void cook(String food){
        if(canCook(food, "dessert")){
            System.out.println("DessertChef: Starting to cook" + food + ". Out in 17 minutes!");
        }else{
            System.out.println("DessertChef: I can't cook that.");
            super.cook(food);
        }
    }
}
