import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FlightManager {
    
    public static void main(String[] args){
        String input = "", opt = "";
        String[] inputArgs;
        ArrayList<String> optionArgs;
        boolean finish = false;
        HashMap<String,Flight> flights = new HashMap<String,Flight>();
        if(args.length > 1){
            System.out.println("O flightManager pode aceitar no máximo 1 argumento");
            System.exit(0);
        }
        if(args.length == 1){
            try {
                Scanner fileScanner = new Scanner(new File(args[0]));
                String line;
                while(fileScanner.hasNextLine()){
                    line = fileScanner.nextLine();
                    inputArgs = line.split(" ");
                    optionArgs = new ArrayList<>(Arrays.asList(inputArgs));
                    opt = optionArgs.remove(0);
                    finish = menu(flights, opt, optionArgs);
                    if (finish){
                        System.exit(0);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Ficheiro não encontrado");
                System.exit(0);
            }
        }
        Scanner sc = new Scanner(System.in);
        while(!finish){
            System.out.println("Escolha uma opção (H para ajuda): ");
            input = sc.nextLine();
            inputArgs = input.split(" ");
            optionArgs = new ArrayList<>(Arrays.asList(inputArgs));
            opt = optionArgs.remove(0);
            finish = menu(flights, opt, optionArgs);
        }
        sc.close();
    }

    public static boolean menu(HashMap<String,Flight> flights, String opt, ArrayList<String> optionArgs){
        switch(opt){
            case "H":
                System.out.println(   "Opções existentes:\n" 
                                    + "H - Menu de Ajuda\n"
                                    + "I filename - Lê um ficheiro que contém informação sobre um voo.\n"
                                    + "M fligh_code - Exibe o mapa das reservas de um voo.\n"
                                    + "F flight_code num_seats_executive num_setas_tourist - Acrescenta um novo voo com código,"
                                    + "lugares em executiva e lugares em turística. Os lugares em executiva são opcionais.\n"
                                    + "R flight_code class number_seats - Acrescenta uma nova reserva a um voo, com indicação"
                                    + "do código de voo, da classe(T / E), e do número de lugares desejados.\n"
                                    + "C reservation_code - Cancelar uma reserva. O código de reserva tem o formato" 
                                    + "flight_code:sequential_reservation_number. Ex: Tp1930:2\n"
                                    + "Q - Termina o programa");
                return false;
            case "I":
                if(optionArgs.size() != 1){
                    System.out.println("Argumentos inválidos");
                }else{
                    try{
                        Scanner fileSC = new Scanner(new File(optionArgs.get(0)));
                        String line, code;
                        ArrayList<String> fileArgs;
                        String[] input;
                        line = fileSC.nextLine();
                        if(line.startsWith(">")){
                            line = line.substring(1);
                            input = line.split(" ");
                            fileArgs = new ArrayList<>(Arrays.asList(input));
                            code = fileArgs.get(0);
                            menu(flights, "F", fileArgs);
                            while(fileSC.hasNextLine()){
                                line = fileSC.nextLine();
                                input = line.split(" ");
                                fileArgs = new ArrayList<>(Arrays.asList(input));
                                fileArgs.add(0, code);
                                menu(flights, "R", fileArgs);
                            }
                            fileSC.close();
                        }else{
                            System.out.println("O ficheiro tem que começar com o carater >");
                        }
                    }catch (FileNotFoundException e){
                        System.out.println("Ficheiro não encontrado");
                    }
                    
                }
                return false;
            case "M":
                if(optionArgs.size() != 1){
                    System.out.println("Argumentos inválidos");
                }else{
                    try {
                        flights.get(optionArgs.get(0)).printFlight();
                    } catch (NullPointerException e) {
                        System.out.println("O voo inserido não está registado");
                    }
                }
                return false;
            case "F":
                if(optionArgs.size() > 3 || optionArgs.size() < 2){
                    System.out.println("Argumentos inválidos");
                }else{
                        addFlight(flights,optionArgs);
                }
                return false;
            case "R":
                if(optionArgs.size() != 3){
                    System.out.println("Argumentos inválidos");
                } else{
                    int numSeats;
                    try {
                        numSeats = Integer.parseInt(optionArgs.get(2));
                        if(optionArgs.get(1).matches("T") || optionArgs.get(1).matches("E")){
                            flights.get(optionArgs.get(0)).addReserve(optionArgs.get(1), numSeats);
                        }else{
                            System.out.println("Argumetos inválidos");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Argumentos inválidos");
                    } catch (NullPointerException e){
                        System.out.println("O voo inserido não está registado");
                    }               
                }
                return false;
            case "C":
                if(optionArgs.size() != 1){
                    System.out.println("Argumentos inválidos");
                }else{
                    if(optionArgs.get(0).matches("^(?=.*?[0-9a-zA-Z:])[0-9a-zA-Z]*[:][0-9]*$")){
                        String[] cancelArgs = optionArgs.get(0).split(":");
                        try{
                            flights.get(cancelArgs[0]).cancelReserve(Integer.parseInt(cancelArgs[1]));
                        } catch (NullPointerException e){
                            System.out.println("O voo inserido não está registado");
                        } catch (NumberFormatException e){
                            System.out.println("Argumentos inválidos");
                        }
                    }
                }
                return false;
            case "Q":
                return true;
            default:
                System.out.println("A opção inserida não é válida");
                return false;
        }
    }

    public static void addFlight(HashMap<String,Flight> flights,ArrayList<String> args){
        String code = args.get(0);
        Flight flight;
        if(args.get(0).matches("[A-Za-z0-9]+")){
            if(args.size() == 2){
                if(args.get(1).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){
                    flight = new Flight(code, args.get(1));
                    flights.put(code, flight);
                }else{
                    System.out.println("A configuraçao do avião não está corretamente formatada (Ex: 3x3)");
                }                
            }else{
                if(args.get(1).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$") && args.get(2).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){
                    flight = new Flight(code, args.get(1), args.get(2));
                    flights.put(code, flight);
                }else{
                    System.out.println("A configuraçao do avião não está corretamente formatada (Ex: 3x3)");
                }
            }
        }else{
            System.out.println("O código do voo tem que ser alfanúmerico");
        }
    }
}
