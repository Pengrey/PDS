/**
 * @author Raquel Ferreira
 * @author Sophie Pousinho
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Voos {
    public static void main(String[] args) {
        Scanner sc = null;
        if(args.length == 0){
            sc = new Scanner(System.in);
            menu(sc);
        } else if(args.length == 1){
            try {
                sc = new Scanner(new File(args[0]));
                while(sc.hasNextLine()){
                    menu(sc);
                }
            } catch (FileNotFoundException e) {
                System.err.println("ERROR: ficheiro inválido");
                System.exit(-1);
            }
        } else {
            System.err.println("ERROR: argumentos inválidos");
            System.exit(-1);
        }
        sc.close();
    }

    public static void menu(Scanner sc){
        String[] option;
        // Saves key=flight_code, value=Airplane
        Map<String, Aviao> flights = new HashMap();

        do {
            System.out.println("--------------------Voos--------------------");
            System.out.println("Escolha uma opção: (H para ajuda)");
            option = sc.nextLine().split(" ");

            switch(option[0]){
                case "H":
                    printOptions();
                    break;
                case "I":
                    addFlightByFile(option, flights);
                    break;
                case "M":
                    printFlight(option, flights);
                    break;
                case "F":
                    createFlight(option, flights);
                    break;
                case "R":
                    createReservation(option, flights);
                    break;
                case "C":
                    cancelReservation(option, flights);
                    break;
                case "Q":
                    break;
                default:
                    System.err.println("ERROR: Opção inválida");
                    printOptions();
                    break;
            }

        } while(!option[0].equals("Q"));
    }

    public static void printOptions(){
        System.out.println("I <ficheiro.txt> :  Lê um ficheiro de texto contendo informação sobre um voo.");
        System.out.println("M <codigo do voo> :  exibe o mapa das reservas de um voo.");
        System.out.println("F <codigo do voo> [lugares-executiva] <lugares-turistica> : acrescenta um voo.");
        System.out.println("R <codigo do voo> <classe> <numero de lugares> : acrescenta uma nova reserva a um voo");
        System.out.println("C <codigo de reserva> : cancela uma reserva");
        System.out.println("Q :  Termina programa");
    }

    public static void addFlightByFile(String[] option, Map<String, Aviao> flights) {
        if(option.length != 2) {
            System.err.println("ERROR: número de argumentos inválido");
            return;
        }

        try {
            /* option[1] = filename.txt */
            Scanner sc2 = new Scanner(new File(option[1]));
            String[] airplaneInfo = sc2.nextLine().split(" ");

            if(airplaneInfo[0].charAt(0) != '>') {
                System.err.println("ERROR: Não tem '>' no início do ficheiro");
                return;
            } else {
                Aviao plane;
                String planeCode = airplaneInfo[0].substring(1);

                if(airplaneInfo.length == 2) {
                    plane =  new AviaoTuristico(airplaneInfo[1]);
                    flights.put(planeCode, plane);
                    System.out.printf("Código de voo %s.\n" +
                            "Lugares disponíveis: %d lugares em classe Turística.\n" +
                            "Classe executiva não disponível neste voo.\n", planeCode, plane.getAvailableTuristicSeats());
                } else if(airplaneInfo.length == 3) {
                    plane =  new AviaoComExecutiva(airplaneInfo[1], airplaneInfo[2]);
                    flights.put(planeCode, plane);
                    System.out.printf("Código de voo %s.\n" +
                            "Lugares disponíveis: %d lugares em classe Turística.\n" +
                            "Lugares disponíveis: %d lugares em classe Executiva.\n", planeCode, plane.getAvailableTuristicSeats(), plane.getAvailableExecutiveSeats());
                } else {
                    System.err.println("ERROR: número inválido de argumentos no ficheiro");
                    return;
                }
                while(sc2.hasNextLine()) {
                    String[] reservation = sc2.nextLine().split(" ");
                    plane.checkReservation(reservation);
                }
            }
            sc2.close();

        } catch (FileNotFoundException e) {
            System.err.println("ERROR: Ficheiro Inválido");
            return;
        }
    }

    public static void createFlight(String[] option, Map<String, Aviao> flights){
        Aviao plane;
        if(option.length == 3) {
            /* option[1] = flight_code
            option[2] = num_seats_tourist */
            plane =  new AviaoTuristico(option[2]);
            flights.put(option[1], plane);
        } else if(option.length == 4) {
            /* option[1] = flight_code
            option[2] = num_seats_executive
            option[3] = num_seats_tourist */
            plane =  new AviaoComExecutiva(option[2], option[3]);
            flights.put(option[1], plane);
        } else {
            System.err.println("ERROR: Tem mais do que 3 argumentos");
            return;
        }
        System.out.println(plane);
    }

    public static void printFlight(String[] option, Map<String, Aviao> flights){

        if(option.length == 2) {
            Aviao plane = flights.get(option[1]);

            if(!flights.containsKey(option[1])){
                System.err.println("ERROR: Voo não existe");
                return;
            }
            // Formatted print of flight according to its type (AviaoTuristico or AviaoComExecutiva)
            if(plane instanceof AviaoComExecutiva){
                System.out.print("   ");
                for(int i = 0; i < ((AviaoComExecutiva) plane).getTuristicSeats(); i++){
                    SeatLetter s = SeatLetter.values()[i];
                    System.out.printf("%2s ", s.toString());
                }
                System.out.println();
                int row;
                for(row = 0; row < ((AviaoComExecutiva) plane).getExecutiveRows(); row++){
                    System.out.printf("%2d ", row+1);
                    for(int seat = 0; seat < ((AviaoComExecutiva) plane).getExecutiveSeats(); seat++){
                        System.out.printf("%2s ", ((AviaoComExecutiva) plane).airplaneClsExec[row][seat]);
                    }
                    System.out.println();
                }
                for(int rowT = 0; rowT < plane.getTuristicRows(); rowT++){
                    System.out.printf("%2d ", rowT+row+1);
                    for(int seat = 0; seat < plane.getTuristicSeats(); seat++){
                        System.out.printf("%2s ", ((AviaoComExecutiva) plane).airplaneClsTur[rowT][seat]);
                    }
                    System.out.println();
                }
            } else {
                System.out.print("   ");
                for(int i = 0; i < ((AviaoTuristico) plane).getTuristicSeats(); i++){
                    SeatLetter s = SeatLetter.values()[i];
                    System.out.printf("%2s ", s.toString());
                }
                System.out.println();
                for(int rowT = 0; rowT < plane.getTuristicRows(); rowT++){
                    System.out.printf("%2d ", rowT+1);
                    for(int seat = 0; seat < plane.getTuristicSeats(); seat++){
                        System.out.printf("%2s ", ((AviaoTuristico) plane).airplaneClsTur[rowT][seat]);
                    }
                    System.out.println();
                }
            }

        } else {
            System.err.println("ERROR: Tem mais do que 1 argumentos");
            return;
        }
    }

    public static void createReservation(String[] option, Map<String, Aviao> flights) {
       /* option[1] = flight_code
       option[2] = class
       option[3] = num_seats */
        String[] reservation = {option[2], option[3]};

        if(option.length != 4){
            System.err.println("ERROR: número de argumentos inválido");
            return;
        }
        if(!flights.containsKey(option[1])){
            System.err.println("ERROR: Voo não existe");
            return;
        }
        Aviao plane = flights.get(option[1]);
        System.out.printf("%s: ", option[1]);
        plane.checkReservation(reservation);

    }

    public static void cancelReservation(String[] option, Map<String, Aviao> flights) {
        if(option.length != 2) {
            System.err.println("ERROR: número de argumentos inválido");
            return;
        }
        /* option[1] = flight_code:reservation_number */
        String[] reservation = option[1].split(":");
        /* reservation[0] = flight_code
        reservation[1] = reservation_number */
        if(!flights.containsKey(reservation[0])){
            System.err.println("ERROR: Voo não existe");
            return;
        }
        Aviao plane = flights.get(reservation[0]);
        // Verify if the turistic or executive reservations contain this plane
        if(plane instanceof AviaoComExecutiva){
            if(((AviaoComExecutiva) plane).reservations.containsKey(Integer.parseInt(reservation[1]))){
                ((AviaoComExecutiva) plane).removeReservation(Integer.parseInt(reservation[1]), "T");
            } else if(((AviaoComExecutiva) plane).execReservations.containsKey(Integer.parseInt(reservation[1]))) {
                ((AviaoComExecutiva) plane).removeReservation(Integer.parseInt(reservation[1]), "E");
            } else{
                System.err.println("ERROR: A reserva não existe");
            }
        } else{
            if(((AviaoTuristico) plane).reservations.containsKey(Integer.parseInt(reservation[1]))) {
                ((AviaoTuristico) plane).removeReservation(Integer.parseInt(reservation[1]));
            } else {
                System.err.println("ERROR: A reserva não existe");
            }
        }
    }
}
