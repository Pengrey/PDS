import java.util.ArrayList;

public class Plane {
    
    private int execRows;
    private int execColumns;
    private int turRows;
    private int turColumns;
    private int[][] execSeats;
    private int[][] turSeats;

    public Plane(String turSeats){
        String[] turConfig = turSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turColumns = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRows][turColumns];
    }

    public Plane(String execSeats, String turSeats){
        String[] turConfig = turSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turColumns = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRows][turColumns];
        String[] execConfig = execSeats.split("x");
        //Temos que ver se a length do array e igual a 2, se for diferente e porque ta mal
        //Temos que ver se os valores do array sao apenas numeros, se nao forem ta mal
        this.turRows = Integer.parseInt(execConfig[0]);
        this.turColumns = Integer.parseInt(execConfig[1]);
        this.turSeats = new int[turRows][turColumns];
    }
}
