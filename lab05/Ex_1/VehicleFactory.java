import java.util.ArrayList;

/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class VehicleFactory {
    
    public static Vehicle getVehicle(int passangers){
        return getVehicle(passangers, new int[]{0}, false);
    }

    public static Vehicle getVehicle(int passangers, int[] luggage){
        return getVehicle(passangers, luggage, false);
    }

    public static Vehicle getVehicle(int passangers, boolean cadeiraDeRodas){
        return getVehicle(passangers, new int[]{0}, cadeiraDeRodas);
    }

    public static Vehicle getVehicle(int passangers, int[] luggage, boolean cadeiraDeRodas){
        int totalVolume = 0;
        Vehicle appropriateVehicle = null;
        String outputString;
        //Calculate total volume
        for (int i : luggage) {
            if (i < 0){
                System.out.println("Luggage volume can't be negative: " + i);
                return null;
            }
            totalVolume += i;
        }

        //List with all different types of Vehicle
        ArrayList<Vehicle> possibleVehicles = new ArrayList<Vehicle>();
        possibleVehicles.add(new Scooter());
        possibleVehicles.add(new Micro());
        possibleVehicles.add(new City());
        possibleVehicles.add(new Family());
        possibleVehicles.add(new Van());

        //Special case, since only Van can have wheelchair
        if (cadeiraDeRodas && totalVolume <= possibleVehicles.get(4).getMaxVolume()){
            appropriateVehicle = possibleVehicles.get(4);
        }else{
            //for each vehicle
            for (Vehicle vehicle : possibleVehicles) {
                //check if its the appropriate one
                if(vehicle.getMaxPassangers() >= passangers && vehicle.getMaxVolume() >= totalVolume){
                    appropriateVehicle = vehicle;
                    break;
                }
            }
        }

        outputString = "Vehicle for " + passangers + " passangers";
        if (totalVolume > 0){
            outputString += " with " + luggage.length + " items of luggage";
        }
        if (cadeiraDeRodas){
            outputString += " and wheelchair";
        }
        if(appropriateVehicle != null){
            outputString += ": Use a " + appropriateVehicle;
        } else{
            outputString += ": Appropriate vehicle not found";
        }

        System.out.println(outputString);

        return appropriateVehicle;
    }
}
