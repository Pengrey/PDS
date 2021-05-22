import java.util.ArrayList;
import java.util.List;

public class Orders {

    public static void main(String[] args) {
        List<String> orders = new ArrayList<String>();
        orders.add("veggie burger");
        orders.add("Pasta Carbonara");
        orders.add("PLAIN pizza, no toppings!");
        orders.add("sushi nigiri and sashimi");
        orders.add("salad with tuna");
        orders.add("strawberry ice cream and waffles dessert");
        Chef chefs = new SushiChef().setSucChef(new PastaChef().setSucChef(new BurgerChef().setSucChef(new PizzaChef().setSucChef(new DessertChef()))));
        for (String food : orders) {
            System.out.println("Can i please get a " + food + "?");
            chefs.cook(food);
            System.out.println();
        }
    }
}
