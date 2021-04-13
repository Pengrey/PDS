import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FlightManager {
    
    public static void main(String[] args){ //Ideia para a option I e para conseguir ler ficheiros com comandos:
        String input = "", opt = "";        //  -Fazer o switch case numa funçao a parte
        String[] inputArgs;                 //  -Chamar no main essa funçao na primeira vez caso nao seja dado nenhum ficheiro de comandos
        ArrayList<String> optionArgs;       //  -No caso do ficheiro de comandos, ler o ficheiro e correr a funçao menu para cada linha do ficheiro
        boolean finish = false;             //  -No caso da opçao I, ler o file e fazer a opçao F na primeira linha e depois option R para reservas
        HashMap<String,Flight> flights = new HashMap<String,Flight>();
        Scanner sc = new Scanner(System.in);
        while(!finish){
            System.out.println("Escolha uma opção (H para ajuda): ");
            input = sc.nextLine();
            inputArgs = input.split(" ");
            optionArgs = new ArrayList<>(Arrays.asList(inputArgs));
            opt = optionArgs.remove(0);
            switch(opt){
                case "H":
                    System.out.println(   "Opções existentes:\n" 
                                        + "H - Menu de Ajuda"
                                        + "I filename - Lê um ficheiro que contém informação sobre um voo.\n"
                                        + "M fligh_code - Exibe o mapa das reservas de um voo.\n"
                                        + "F flight_code num_seats_executive num_setas_tourist - Acrescenta um novo voo com código,"
                                        + "lugares em executiva e lugares em turística. Os lugares em executiva são opcionais.\n"
                                        + "R flight_code class number_seats - Acrescenta uma nova reserva a um voo, com indicação"
                                        + "do código de voo, da classe(T / E), e do número de lugares desejados.\n"
                                        + "C reservation_code - Cancelar uma reserva. O código de reserva tem o formato" 
                                        + "flight_code:sequential_reservation_number. Ex: Tp1930:2\n"
                                        + "Q - Termina o programa");
                    break;
                case "I":
                    break;
                case "M":
                    if(optionArgs.size() != 1){
                        System.out.println("Argumentos não válidos");
                    }else{
                        try {
                            flights.get(optionArgs.get(0)).printFlight();
                        } catch (NullPointerException e) {
                            System.out.println("O voo inserido não está registado");
                        }
                    }
                    break;
                case "F":
                    if(optionArgs.size() > 3 || optionArgs.size() < 2){
                        System.out.println("Argumentos não válidos");
                    }else{
                            addFlight(flights,optionArgs);
                    }
                    break;
                case "R":
                    if(optionArgs.size() != 3){
                        System.out.println("Argumentos não válidos");
                    } else{ // need to do argument validation, only pass if T or E
                        flights.get(optionArgs.get(0)).addReserve(optionArgs.get(1), Integer.parseInt(optionArgs.get(2)));
                    }
                    break;
                case "C":
                case "Q":
                    finish = true;
                    break;
            }
        }
        sc.close();
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
