public class SushiChef extends Chef {
    
    public void cook(String food){
        if(canCook(food, "sushi")){
            System.out.println("SushiChef: Starting to cook" + food + ". Out in 14 minutes!");
        }else{
            System.out.println("SushiChef: I can't cook that.");
            super.cook(food);
        }
    }
}
