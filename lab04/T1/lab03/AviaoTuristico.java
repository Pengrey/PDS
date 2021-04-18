/**
 * @author Raquel Ferreira
 * @author Sophie Pousinho
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AviaoTuristico implements Aviao {

    protected Map<String, int[]> classes = new HashMap();
    protected int[][] airplaneClsTur;
    protected int availableTuristicSeats;
    protected int reservationCounter = 1;
    public Map<Integer, ArrayList<String>> reservations = new HashMap<>();

    // Constructor
    public AviaoTuristico(String turisticInfo){
        int index = turisticInfo.indexOf('x');
        int turisticRows = Integer.parseInt(turisticInfo.substring(0, index));
        int turisticSeats = Integer.parseInt(turisticInfo.substring(index+1));
        classes.put("T", new int[]{turisticRows, turisticSeats});

        availableTuristicSeats = turisticSeats*turisticRows;

        airplaneClsTur = new int[turisticRows][turisticSeats];
        for(int i = 0; i < turisticRows; i++){
            for(int j = 0; j < turisticSeats; j++){
                airplaneClsTur[i][j] = 0;
            }
        }
    }

    public boolean checkReservation(String[] reservation){
        String planeClass = reservation[0];
        int numberSeats = Integer.parseInt(reservation[1]);
        int peopleLeft = numberSeats;

        // Check if class Executiva exists
        if(planeClass.equals("E")){
            System.err.println("ERROR: classe executiva indisponível neste voo");
            return false;
        }
        // Check if there are enough seats available for the given class
        if((planeClass.equals("T") && numberSeats > availableTuristicSeats)) {
            System.err.println("ERROR: não foi possivel obter lugares para a reserva :" + planeClass + " " + numberSeats);
            return false;
        }
        // Find seats in empty rows first, if non-existent, put in an available seat
        if(!putInEmptyRow(peopleLeft)){
            putInDifferentRows(peopleLeft);
        }

        // Print of reservation
        for (Map.Entry<Integer, ArrayList<String>> entry : reservations.entrySet()) {
            if(entry.getKey() == reservationCounter-1) {
                System.out.printf("%s = ", entry.getKey());
                for (String s : entry.getValue()) {
                    System.out.printf("%s ", s);
                }
                System.out.println();
            }
        }
        return true;
    }

    protected boolean putInEmptyRow(int peopleLeft){
        // Goes through rows until it finds one empty
        for (int row = 0; row < getTuristicRows(); row++) {
            // Check if row only has empty seats
            if (Arrays.toString(airplaneClsTur[row]).replaceAll("[\\[\\], ]", "").matches("[0]+")) {
                // Check if peopleLeft fit in one row
                if (getTuristicSeats() >= peopleLeft) {
                    // Put people in row
                    ArrayList<String> res = new ArrayList<>();
                    reserveSeats(peopleLeft, row, res);
                    reservations.put(reservationCounter, res);
                    reservationCounter++;
                    return true;
                // If peopleLeft don't fit a row
                } else {
                    ArrayList<String> res = new ArrayList<>();
                    while(peopleLeft > 0){
                        reserveSeats(peopleLeft, row, res);
                        peopleLeft -= getTuristicSeats();
                        // Goes to next row when the previous one is full until peopleLeft is less or equal to 0
                        if(row+1 >= getTuristicRows() && peopleLeft > 0) {
                            return false;
                        }
                        row++;
                    }
                    reservations.put(reservationCounter, res);
                    reservationCounter++;
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String> reserveSeats(int peopleLeft, int row, ArrayList<String> res){
        for(int seat = 0; seat < getTuristicSeats(); seat++) {
            if(seat < peopleLeft) {
                airplaneClsTur[row][seat] = reservationCounter;
                availableTuristicSeats--;
                String resSeat = String.valueOf(row+1) + SeatLetter.values()[seat];
                res.add(resSeat);
            } else {
                break;
            }
        }
        return res;
    }

    protected ArrayList<String> putInDifferentRows(int peopleLeft) {
        ArrayList<String> res = new ArrayList<>();
        for(int row = 0; row < getTuristicRows(); row++){
            for(int seat = 0; seat < getTuristicSeats(); seat++){
                if (peopleLeft > 0) {
                    if(airplaneClsTur[row][seat] == 0){
                        airplaneClsTur[row][seat] = reservationCounter;
                        String resSeat = String.valueOf(row+1) + SeatLetter.values()[seat];
                        res.add(resSeat);
                        peopleLeft--;
                        availableTuristicSeats--;
                    }
                } else {
                    return null;
                }
            }
        }
        return res;
    }

    public void removeReservation(int reservationCounter){
        ArrayList<String> seatsToRemove = reservations.get(reservationCounter);
        for (String s : seatsToRemove) {
            int row;
            int seat;
            if (s.length() == 2) {
                row = Integer.parseInt(String.valueOf(s.charAt(0)));
                seat = SeatLetter.valueOf(String.valueOf(s.charAt(1))).ordinal();
            } else {
                row = Integer.parseInt(s.substring(0, 2));
                seat = SeatLetter.valueOf(String.valueOf(s.charAt(2))).ordinal();
            }
            airplaneClsTur[row][seat] = 0;
        }
        reservations.remove(reservationCounter);
    }

    public int getAvailableTuristicSeats(){
        return availableTuristicSeats;
    }

    public int getAvailableExecutiveSeats() {
        return -1;
    }

    @Override
    public String toString(){
        return "Avião com " + getTuristicRows()*getTuristicSeats() + " lugares na Classe Turística";
    }

    public int getTuristicSeats(){
        return classes.get("T")[1];
    }
    public int getTuristicRows(){
        return classes.get("T")[0];
    }
}

