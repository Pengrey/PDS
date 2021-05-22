public class PastaChef extends Chef {
    
    public void cook(String food){
        if(canCook(food, "pasta")){
            System.out.println("PastaChef: Starting to cook" + food + ". Out in 14 minutes!");
        }else{
            System.out.println("PastaChef: I can't cook that.");
            super.cook(food);
        }
    }
}
