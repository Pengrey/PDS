import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Flight {
    
    private String code;
    private int reserveCounter;
    private int execRows;
    private int execColumns;
    private int turRows;
    private int turColumns;
    private int[][] execSeats;
    private int[][] turSeats;
    private Map<Integer,ArrayList<String>> reserves;
    

    public Flight(String code, String turSeats){
        this.code = code;
        this.reserveCounter = 0;
        this.reserves = new HashMap<Integer, ArrayList<String>>();
        String[] turConfig = turSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turColumns = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRows][turColumns];
    }

    public Flight(String code, String execSeats, String turSeats){
        this.code = code;
        this.reserveCounter = 0;
        this.reserves = new HashMap<Integer, ArrayList<String>>();
        String[] turConfig = turSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turColumns = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRows][turColumns];
        String[] execConfig = execSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.execRows = Integer.parseInt(execConfig[0]);
        this.execColumns = Integer.parseInt(execConfig[1]);
        this.execSeats = new int[execRows][execColumns];
    }

    public String getCode(){
        return this.code;
    }

    public int getExecRows(){
        return execRows;
    }

    public int getExecColumns(){
        return execColumns;
    }

    public int getTurRows(){
        return turRows;
    }

    public int getTurColumns(){
        return turColumns;
    }

    public void addReserve(){

    }

    public void printFlight(){
        System.out.println("size: " + (this.execRows + this.turRows -1));
        System.out.print("   ");
        for(int i = 1; i < (this.execRows + this.turRows + 1); i++){
            System.out.printf("%3d",i);
        }
        System.out.println("");
        if(this.execColumns < this.turColumns){
            for(int j = 0; j < this.execColumns; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[i][j]);
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[i][j]);
                }
                System.out.println("");
            }
            for(int j = this.execColumns; j < this.turColumns; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for (int i = 0; i < execRows; i++){
                    System.out.print("   ");
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[i][j]);
                }
                System.out.println("");
            }
        }else{
            for(int j = 0; j < this.turColumns; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[i][j]);
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[i][j]);
                }
                System.out.println("");
            }
            for(int j = this.turColumns; j < this.execRows; j++ ){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[i][j]);
                }
                System.out.println("");
            }
        }
    }
}
