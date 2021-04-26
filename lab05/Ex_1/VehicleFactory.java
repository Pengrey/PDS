import java.util.ArrayList;

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
        int totalWeight = 0;
        Vehicle appropriateVehicle = null;
        String outputString;
        for (int i : luggage) {
            if (i < 0){
                System.out.println("Luggage volume can't be negative: " + i);
                return null;
            }
            totalWeight += i;
        }

        ArrayList<Vehicle> possibleVehicles = new ArrayList<Vehicle>();
        possibleVehicles.add(new Scooter());
        possibleVehicles.add(new Micro());
        possibleVehicles.add(new City());
        possibleVehicles.add(new Family());
        possibleVehicles.add(new Van());

        if (cadeiraDeRodas && totalWeight <= 1000){
            appropriateVehicle = possibleVehicles.get(4);
        }else{
            for (Vehicle vehicle : possibleVehicles) {
                if(vehicle.getMaxPassangers() >= passangers && vehicle.getMaxVolume() >= totalWeight){
                    appropriateVehicle = vehicle;
                    break;
                }
            }
        }

        outputString = "Vehicle for " + passangers + " passangers";
        if (totalWeight > 0){
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
