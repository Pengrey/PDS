/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class Test {
    public static void main(String[] args) {
        int[] luggage;
        Vehicle v;
        // Get vehicle for 1 passenger without luggage
        v = VehicleFactory.getVehicle(1);
        // Get vehicle for 1 passenger with two items of luggage
        luggage = new int[]{100, 140}; // two bags with a total volume of 240 
        v = VehicleFactory.getVehicle(1, luggage);
        // Get vehicle for 3 passengers with two items of luggage
        luggage = new int[]{50, 200, 240}; // three bags with a total volume of 490 
        v = VehicleFactory.getVehicle(3, luggage);
        // Get vehicle for 2 passengers and wheelchair
        v = VehicleFactory.getVehicle(2, true);
        //Get vehicle for 4 passengers with 3 items of luggage
        luggage = new int[]{50, 200, 440}; //three bags witha  total volume of 690
        v = VehicleFactory.getVehicle(4, luggage, false);
        //Get vehicle for 5 passengers
        v = VehicleFactory.getVehicle(5);
        }
}
