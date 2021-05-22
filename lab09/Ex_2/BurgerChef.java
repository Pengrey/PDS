public class BurgerChef extends Chef{

    public void cook(String food){
        if(canCook(food, "burger")){
            System.out.println("BurgerChef: Starting to cook" + food + ". Out in 19 minutes!");
        }else{
            System.out.println("BurgerChef: I can't cook that.");
            super.cook(food);
        }
    }
}
