import java.util.ArrayList;
import java.util.Scanner;

public class FlightManager {
    
    public static void main(String[] args){
        String opt = "";
        boolean finish = false; 
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Scanner sc = new Scanner(System.in);
        while(!finish){
            System.out.println("Escolha uma opção (H para ajuda): ");
            opt = sc.next();
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
                case "F":
                case "R":
                case "C":
                case "Q":
                    finish = true;
                    break;
            }
        }
        sc.close();
    }
}
