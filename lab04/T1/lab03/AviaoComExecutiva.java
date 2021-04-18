/**
 * @author Raquel Ferreira
 * @author Sophie Pousinho
 **/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AviaoComExecutiva extends AviaoTuristico {

    private int availableExecutiveSeats;
    protected int[][] airplaneClsExec;
    public Map<Integer, ArrayList<String>> execReservations = new HashMap<>();


    public AviaoComExecutiva(String executiveInfo, String turisticInfo){
        super(turisticInfo);

        // Executive class
        int index = executiveInfo.indexOf('x');
        int executiveRows = Integer.parseInt(executiveInfo.substring(0, index));
        int executiveSeats = Integer.parseInt(executiveInfo.substring(index+1));

        classes.put("E", new int[]{executiveRows, executiveSeats});
        availableExecutiveSeats = executiveSeats*executiveRows;

        airplaneClsExec = new int[executiveRows][executiveSeats];
        for(int i = 0; i < executiveRows; i++){
            for(int j = 0; j < executiveSeats; j++){
                airplaneClsExec[i][j] = 0;
            }
        }
    }

    public boolean checkReservation(String[] reservation){
        String planeClass = reservation[0];
        int seats = Integer.parseInt(reservation[1]);
        int peopleLeft = seats;

        // Check if there are enough seats available for the given class
        if((planeClass.equals("T") && seats > availableTuristicSeats)) {
            System.err.println("ERROR: não foi possivel obter lugares para a reserva :" + planeClass + " " + seats);
            return false;
        }
        if((planeClass.equals("E") && seats > availableExecutiveSeats)) {
            System.err.println("ERROR: não foi possivel obter lugares para a reserva :" + planeClass + " " + seats);
            return false;
        }
        if(planeClass.equals("T")){
            // Find seats in empty rows first, if non-existent, put in an available seat
            if(!putInEmptyRow(peopleLeft)) {
                putInDifferentRows(peopleLeft);
            }
        } else{
            // Find seats in empty rows first, if non-existent, put in an available seat
            if(!putInEmptyRowExec(peopleLeft)) {
                putInDifferentRowsExec(peopleLeft);
            }
        }

        for (Map.Entry<Integer, ArrayList<String>> entry : execReservations.entrySet()) {
            if(entry.getKey() == reservationCounter-1) {
                System.out.printf("%s = ", entry.getKey());
                for (String s : entry.getValue()) {
                    System.out.printf("%s ", s);
                }
                System.out.println();
            }
        }
        for (Map.Entry<Integer, ArrayList<String>> entry : reservations.entrySet()) {
            if(entry.getKey() == reservationCounter-1) {
                System.out.printf("%s = " , entry.getKey());
                for (String s : entry.getValue()) {
                    int row;
                    if(s.length() == 2) {
                        row = Integer.parseInt(String.valueOf(s.charAt(0)))+getExecutiveRows();
                    } else {
                        row = Integer.parseInt(s.substring(0,2))+getExecutiveRows();
                    }
                    System.out.printf("%d%s ", row, s.charAt(1));
                }
                System.out.println();
            }
        }
        return true;
    }

    private boolean putInEmptyRowExec(int peopleLeft){
        // Goes through rows until it finds one empty
        for (int row = 0; row < getExecutiveRows(); row++) {
            // Check if row only has empty seats
            if (Arrays.toString(airplaneClsExec[row]).replaceAll("[\\[\\], ]", "").matches("[0]+")) {
                // Check if peopleLeft fit in one row
                if (getExecutiveSeats() >= peopleLeft) {
                    // Put people in row
                    ArrayList<String> res = new ArrayList<>();
                    reserveSeatsExec(peopleLeft, row, res);
                    execReservations.put(reservationCounter, res);
                    reservationCounter++;
                    return true;
                // If peopleLeft don't fit a row
                } else {
                    ArrayList<String> res = new ArrayList<>();
                    while(peopleLeft > 0){
                        reserveSeatsExec(peopleLeft, row, res);
                        peopleLeft -= getExecutiveSeats();
                        // Goes to next row when the previous one is full until peopleLeft is less or equal to 0
                        if(row+1 >= getExecutiveRows() && peopleLeft > 0) {
                            return false;
                        }
                        row++;
                    }
                    execReservations.put(reservationCounter, res);
                    reservationCounter++;
                    return true;
                }
            }
        }
        return false;
    }

    private ArrayList<String>  reserveSeatsExec(int peopleLeft, int row, ArrayList<String> res){
        for(int seat = 0; seat < getExecutiveSeats(); seat++) {
            if(seat < peopleLeft) {
                airplaneClsExec[row][seat] = reservationCounter;
                availableExecutiveSeats--;
                String resSeat = String.valueOf(row+1) + SeatLetter.values()[seat];
                res.add(resSeat);
            } else {
                break;
            }
        }
        return res;
    }

    private ArrayList<String> putInDifferentRowsExec(int peopleLeft) {
        ArrayList<String> res = new ArrayList<>();
        for(int row = 0; row < getExecutiveRows(); row++){
            for(int seat = 0; seat < getExecutiveSeats(); seat++){
                if (peopleLeft > 0) {
                    if(airplaneClsExec[row][seat] == 0){
                        airplaneClsExec[row][seat] = reservationCounter;
                        String resSeat = String.valueOf(row+1) + SeatLetter.values()[seat];
                        res.add(resSeat);
                        peopleLeft--;
                        availableExecutiveSeats--;
                    }
                } else {
                    return null;
                }
            }
        }
        return res;
    }

    public void removeReservation(int reservationCounter, String cls){
        if(cls.equals("T")){
            ArrayList<String> seatsToRemove = reservations.get(reservationCounter);
            for(String s: seatsToRemove){
                int row;
                int seat;
                if(s.length() == 2){
                    row = Integer.parseInt(String.valueOf(s.charAt(0)));
                    seat = SeatLetter.valueOf(String.valueOf(s.charAt(1))).ordinal();
                } else {
                    row = Integer.parseInt(s.substring(0,2));
                    seat = SeatLetter.valueOf(String.valueOf(s.charAt(2))).ordinal();
                }
                airplaneClsTur[row][seat] = 0;
            }
            reservations.remove(reservationCounter);
        } else{
            ArrayList<String> seatsToRemove = execReservations.get(reservationCounter);
            for(String s: seatsToRemove){
                int row;
                int seat;
                if(s.length() == 2){
                    row = Integer.parseInt(String.valueOf(s.charAt(0)));
                    seat = SeatLetter.valueOf(String.valueOf(s.charAt(1))).ordinal();
                } else {
                    row = Integer.parseInt(s.substring(0,2));
                    seat = SeatLetter.valueOf(String.valueOf(s.charAt(2))).ordinal();
                }
                airplaneClsExec[row][seat] = 0;
            }
            execReservations.remove(reservationCounter);
        }
    }

    @Override
    public int getAvailableExecutiveSeats(){
        return availableExecutiveSeats;
    }

    @Override
    public String toString(){
        return super.toString() + "  e " + getExecutiveRows()*getExecutiveSeats() + " lugares na Classe Executiva";
    }

    public int getExecutiveSeats(){
        return classes.get("E")[1];
    }
    public int getExecutiveRows(){
        return classes.get("E")[0];
    }

}
