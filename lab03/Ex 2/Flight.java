import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flight {
    
    private String code;
    private int reserveCounter;
    private int execRowSeats;
    private int execRows;
    private int turRowSeats;
    private int turRows;
    private int[][] execSeats;
    private int[][] turSeats;
    private Map<Integer,ArrayList<String>> reserves;
    

    public Flight(String code, String turSeats){
        this.code = code;
        this.reserveCounter = 0;
        this.reserves = new HashMap<Integer, ArrayList<String>>();
        String[] turConfig = turSeats.split("x");
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turRowSeats = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRowSeats][turRows];
    }

    public Flight(String code, String execSeats, String turSeats){
        this.code = code;
        this.reserveCounter = 0;
        this.reserves = new HashMap<Integer, ArrayList<String>>();
        String[] turConfig = turSeats.split("x");
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turRowSeats = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRowSeats][turRows];
        String[] execConfig = execSeats.split("x");
        this.execRows = Integer.parseInt(execConfig[0]);
        this.execRowSeats = Integer.parseInt(execConfig[1]);
        this.execSeats = new int[execRowSeats][execRows];
    }

    public String getCode(){
        return this.code;
    }

    public int getExecRowSeats(){
        return this.execRowSeats;
    }

    public int getExecRows(){
        return this.execRows;
    }

    public int getTurRowSeats(){
        return this.turRowSeats;
    }

    public int getTurRows(){
        return this.turRows;
    }

    public void addReserve(String type,int numSeats){   //Não está completa, tenho que reorganizar pois tenho que tratar do caso em que sobre 1 row vazia e
        if(type.equals("T")){                           //tratar de casos em que o numSeats é maior que RowSeats
            int j, emptySeats, rowsNeeded;
            rowsNeeded = (int) Math.ceil(numSeats/3.0);
            boolean foundEmptyRow = false;
            System.out.println("turRows: " + this.turRows + " turRowSeats: " + this.turRowSeats);
            for(j = 0; j < this.turRows; j++){
                emptySeats = 0;
                for(int i = 0; i < this.turRowSeats;i++){
                    System.out.println("Row: " + j + " Seat: " + i + " Value: " + this.turSeats[i][j]);
                    if(this.turSeats[i][j] == 0){
                        emptySeats++;
                        System.out.println("Empty: " + emptySeats);
                    }
                }
                if(emptySeats == this.turRowSeats){
                    foundEmptyRow = true;
                    break;
                }
            }

            System.out.println("j: " + j);
            if(foundEmptyRow){
                for(int x = j; x < this.turRows; x++){
                    for(int y = 0; y < this.turRowSeats; y++){
                        turSeats[y][x] = this.reserveCounter + 1;
                        System.out.println("numSeats antes: " + numSeats + " y: " + y);
                        numSeats--;
                        System.out.println("numSeats depois: " + numSeats);
                        if(numSeats == 0){
                            break;
                        }
                    }
                    if(numSeats == 0){
                        break;
                    }
                }
            }
        }else{
            System.out.println("Not functional yet");
        }
        //reserveCounter++; // fazer quando vir que é possivel
    }

    public void printFlight(){
        System.out.println("turRows: " + this.turRows + " turRowSeats: " + this.turRowSeats);
        System.out.print("   ");
        for(int i = 1; i < (this.execRows + this.turRows + 1); i++){
            System.out.printf("%3d",i);
        }
        System.out.println("");
        if(this.execRowSeats < this.turRowSeats){
            for(int j = 0; j < this.execRowSeats; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println("");
            }
            for(int j = this.execRowSeats; j < this.turRowSeats; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for (int i = 0; i < execRows; i++){
                    System.out.print("   ");
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println("");
            }
        }else{
            for(int j = 0; j < this.turRowSeats; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println("");
            }
            for(int j = this.turRowSeats; j < this.execRows; j++ ){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRowSeats; i++){
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                System.out.println("");
            }
        }
    }
}
