import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

//In the same folder as this java file, there are 4 txt files:
//  -flight1.txt and flight2.txt are example of files that are supposed to be used with the option I inside the program
//  -commandFile.txt is a file that you pass as an argument when starting the program
//  -commandFileExpectedOutput.txt is the expected output when you pass commandFile.txt as an argument when starting the program

public class FlightManager {
    public static void main(String[] args){
        String input = "", opt = "";
        String[] inputArgs;
        ArrayList<String> optionArgs;
        boolean finish = false;
        HashMap<String,Flight> flights = new HashMap<String,Flight>();                  //HashMap to save the flights, the key being the flight code
        if(args.length > 1){                                                            //Arguments verification
            System.out.println("O flightManager pode aceitar no máximo 1 argumento");
            System.exit(0);
        }
        if(args.length == 1){                                                           //Case where a command file is passed as an argument
            try {
                Scanner fileScanner = new Scanner(new File(args[0]));                   //Scanner that reads the command file
                String line;
                while(fileScanner.hasNextLine()){                                       //While there is content to read
                    line = fileScanner.nextLine();                                      //Read line
                    inputArgs = line.split(" ");                                        //Split line in order to have the arguments of the command separated
                    optionArgs = new ArrayList<>(Arrays.asList(inputArgs));             //Turn the String[] into an ArrayList<String> to be easier to use
                    opt = optionArgs.remove(0);                                         //Get first argument, which is the indicator of the command that will be executed
                    finish = menu(flights, opt, optionArgs);                            //Call menu() where the command will be executed, and return true if the program should end
                    if (finish){                                                        //Case where the program needs to end
                        System.exit(0);                                                 //End program
                    }
                }
            } catch (FileNotFoundException e) {                                         //Catch exception where the file is not found
                System.out.println("Ficheiro não encontrado");
                System.exit(0);
            }
        }
        Scanner sc = new Scanner(System.in);                                            //Scanner reads user input
        while(!finish){                                                                 //While the user does not want to end the program
            System.out.println("Escolha uma opção (H para ajuda): ");
            input = sc.nextLine();                                                      //Read user input
            inputArgs = input.split(" ");                                               //Put the arguments in a String[]
            optionArgs = new ArrayList<>(Arrays.asList(inputArgs));                     //Turn the String[] into a ArrayList<String>
            opt = optionArgs.remove(0);                                                 //Get first argument, which is the indicator of the command that will be executed
            finish = menu(flights, opt, optionArgs);                                    //Call menu() where the command will be executed, and return true if the program should end
        }
        sc.close();
    }

    //menu() function is used to execute commands the user inputs, return true if the user wants to terminate the program, otherwise returns false
    public static boolean menu(HashMap<String,Flight> flights, String opt, ArrayList<String> optionArgs){
        boolean end = false;
        switch(opt){
            case "H":  //Help menu - Prints all the existing commands                                                                                                           
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
                break;
            case "I":                                                                                   //Read file with a flight code, configuration and reservations
                if(optionArgs.size() != 1){                                                             //Verify number of arguments
                    System.out.println("Argumentos inválidos");
                }else{
                    try{
                        Scanner fileSC = new Scanner(new File(optionArgs.get(0)));                      //Scanner that read the file
                        String line, code;
                        ArrayList<String> fileArgs;
                        String[] input;
                        line = fileSC.nextLine();                                                       //First line of the file
                        if(line.startsWith(">")){                                                       //Check if line starts with the char ">", which is needs for it to be valid
                            line = line.substring(1);                                                   //Take out the char ">", since it will not be used
                            input = line.split(" ");                                                    //Separate the args and put them in a String[]
                            fileArgs = new ArrayList<>(Arrays.asList(input));                           //Turn the String[] into a ArrayList<String>, for easier use
                            code = fileArgs.get(0);                                                     //Get flight code
                            menu(flights, "F", fileArgs);                                               //Call menu() to register the flight
                            while(fileSC.hasNextLine()){                                                //Read rest of the file
                                line = fileSC.nextLine();                                               //Read line
                                input = line.split(" ");                                                //Separate the args and put them in a String[]
                                fileArgs = new ArrayList<>(Arrays.asList(input));                       //Turn the String[] into a ArrayList<String>, for easier use
                                fileArgs.add(0, code);                                                  //Since the lines in the file do not contain the flight code, and it is needed for the menu(), insert in the args arrayList
                                menu(flights, "R", fileArgs);                                           //Call menu() to make the reservation
                            }
                            fileSC.close();
                        }else{                                                                          //First line does not start with ">"
                            System.out.println("O ficheiro tem que começar com o carater >");
                        }
                    }catch (FileNotFoundException e){                                                   //Catch exception where the file is not found
                        System.out.println("Ficheiro não encontrado");
                    }
                    
                }
                break;
            case "M":                                                                                   //Print flight
                if(optionArgs.size() != 1){                                                             //Verify number of args
                    System.out.println("Argumentos inválidos");
                }else{
                    try {
                        flights.get(optionArgs.get(0)).printFlight();                                   //Print flight
                    } catch (NullPointerException e) {                                                  //Catch exception where the flight does not exist
                        System.out.println("O voo inserido não está registado");
                    }
                }
                break;
            case "F":                                                                                   //Register flight
                if(optionArgs.size() > 3 || optionArgs.size() < 2){                                     //Verify number of args
                    System.out.println("Argumentos inválidos");
                }else{
                        addFlight(flights,optionArgs);                                                  //Call addFlight(), that adds the flight to the HashMap
                }
               break;
            case "R":                                                                                   //Make a reservation
                if(optionArgs.size() != 3){                                                             //verify number of args
                    System.out.println("Argumentos inválidos");
                } else{
                    int numSeats;
                    try {
                        numSeats = Integer.parseInt(optionArgs.get(2));                                 //Turn the string that indicates the number of seats wanted into int
                        if(optionArgs.get(1).matches("T") || optionArgs.get(1).matches("E")){           //Check if the type given is T or E, otherwise it is not valid
                            flights.get(optionArgs.get(0)).addReservation(optionArgs.get(1), numSeats);     //Make reservation
                        }else{
                            System.out.println("Argumetos inválidos");
                        }
                    } catch (NumberFormatException e) {                                                 //Catch exception when the numSeats given by the user has letters
                        System.out.println("Argumentos inválidos");
                    } catch (NullPointerException e){                                                   //Catch exception when the fligh is not registered
                        System.out.println("O voo inserido não está registado");
                    }               
                }
                break;
            case "C":                                                                                   //Cancel a reservation
                if(optionArgs.size() != 1){                                                             //verify number of args
                    System.out.println("Argumentos inválidos");
                }else{
                    if(optionArgs.get(0).matches("^(?=.*?[0-9a-zA-Z:])[0-9a-zA-Z]*[:][0-9]*$")){        //Check if argument has the valid pattern
                        String[] cancelArgs = optionArgs.get(0).split(":");                             //Split the argument into 2 arguments, the flight code and the reservation to be cancelled
                        try{
                            flights.get(cancelArgs[0]).cancelReservation(Integer.parseInt(cancelArgs[1]));  //Cancel the reservation
                        } catch (NullPointerException e){                                               //Catch exception when the fligh is not registered
                            System.out.println("O voo inserido não está registado");
                        } catch (NumberFormatException e){                                              //Catch exception when the reservation given by the user has letters
                            System.out.println("Argumentos inválidos");
                        }
                    }else{
                        System.out.println("Argumentos inválidos");
                    }
                }
                break;
            case "Q":                                                                                   //End program
                end = true;
                break;
            default:                                                                                    //Command does not exist
                System.out.println("A opção inserida não é válida");
                break;
        }
        return end;
    }

    //Function to add a flight to the HashMap
    public static void addFlight(HashMap<String,Flight> flights,ArrayList<String> args){
        String code = args.get(0);                                                                                                                  //Flight code
        Flight flight;
        if(code.matches("[A-Za-z0-9]+")){                                                                                                           //Check if flight code is valid
            if (flights.containsKey(args.get(0))){                                                                                                  //Check if exists flight with same code as the user inputted
                System.out.println("Já existe um voo registado com esse código");
                return;
            }
            if(args.size() == 2){                                                                                                                   //Flight only has turistic seats
                if(args.get(1).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){                                                                     //Check if configuration is valid
                    flight = new Flight(code, args.get(1));                                                                                         //Create flight
                    flights.put(code, flight);                                                                                                      //Register flight
                }else{
                    System.out.println("A configuraçao do avião não está corretamente formatada (Ex: 3x3)");
                }                
            }else{                                                                                                                                  //Flight has executive and turistic seats
                if(args.get(1).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$") && args.get(2).matches("^(?=.*?[0-9a-zA-Z])[0-9]*[x][0-9]*$")){       //Check if configurations are valid
                    flight = new Flight(code, args.get(1), args.get(2));                                                                            //Create flight
                    flights.put(code, flight);                                                                                                      //Register flight
                }else{
                    System.out.println("A configuraçao do avião não está corretamente formatada (Ex: 3x3)");
                }
            }
        }else{
            System.out.println("O código do voo tem que ser alfanúmerico");
        }
    }
}
