public class Flight {
    
    private String code;
    private int reserveCounter;
    private int execRowSeats;
    private int execRows;
    private int turRowSeats;
    private int turRows;
    private int[][] execSeats;
    private int[][] turSeats;
    

    public Flight(String code, String turSeats){
        this.code = code;
        this.reserveCounter = 1;
        String[] turConfig = turSeats.split("x");
        this.turRows = Integer.parseInt(turConfig[0]);
        this.turRowSeats = Integer.parseInt(turConfig[1]);
        this.turSeats = new int[turRowSeats][turRows];
    }

    public Flight(String code, String execSeats, String turSeats){
        this.code = code;
        this.reserveCounter = 1;
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

    public void addReserve(String type,int numSeats){
        String[] seatsUsed;
        if(type.equals("T")){
            seatsUsed = addedReserveSupport(this.turSeats, numSeats, true);
            if(seatsUsed[numSeats-1] == null){
                System.out.println("Não foi possivel obter lugares para a reserva: " + type + " " + numSeats);
            }else{
                System.out.print(this.code + ":" + (this.reserveCounter-1) + " = ");
                for (String string : seatsUsed) {
                    System.out.print(" " + string + " |");
                }
                System.out.println("");
            }
        }else{
            seatsUsed = addedReserveSupport(this.execSeats, numSeats, false);
            if(seatsUsed[numSeats-1] == null){
                System.out.println("Não foi possivel obter lugares para a reserva: " + type + " " + numSeats);
            }else{
                System.out.print(this.code + ":" + (this.reserveCounter-1) + " = ");
                for (String string : seatsUsed) {
                    System.out.print(" " + string + " |");
                }
                System.out.println("");
            }
        }
    }

    private String[] addedReserveSupport(int[][] xSeats,int numSeats, boolean isTur){
        int startRow = 1;
        if(isTur){
            startRow = this.execRows + 1;
        }
        String[] seatsUsed = new String[numSeats];
        int seatsUsedCounter = 0;
        int xRowSeats = xSeats.length;
        int xRows = xSeats[0].length;
        int j, emptySeats;
            for(j = 0; j < xRows; j++){
                emptySeats = 0;
                if(numSeats == 0){
                    break;
                }
                for(int i = 0; i < xRowSeats;i++){
                    if(xSeats[i][j] == 0){
                        emptySeats++;
                    }
                }
                if(emptySeats == xRowSeats){
                    for(int x = j; x < xRows; x++){
                        for(int y = 0; y < xRowSeats; y++){
                            xSeats[y][x] = reserveCounter;
                            seatsUsed[seatsUsedCounter] = String.valueOf((char)(y + 65)) + String.valueOf(x + startRow);
                            seatsUsedCounter++;
                            numSeats--;
                            if(numSeats == 0){
                                break;
                            }
                        }
                        if(numSeats == 0){
                            break;
                        }
                    }
                }
            }

            if(numSeats != 0){
                for(j = 0; j < xRows; j++){
                    for(int i = 0; i < xRowSeats;i++){
                        if(xSeats[i][j] == 0){
                            xSeats[i][j] = reserveCounter;
                            seatsUsed[seatsUsedCounter] = String.valueOf((char)(i + 65)) + String.valueOf(j + startRow);
                            seatsUsedCounter++;
                            numSeats--;
                            if(numSeats == 0){
                                break;
                            }
                        }
                    }
                    if(numSeats == 0){
                        break;
                    }
                }
            }
            if(numSeats !=0){
                for(j = 0; j < xRows; j++){
                    for(int i = 0; i < xRowSeats;i++){
                        if(xSeats[i][j] == reserveCounter){
                            xSeats[i][j] = 0;
                        }
                    }
                }
                return seatsUsed;
            }
            this.reserveCounter++;
            return seatsUsed;
    }

    public void printFlight(){
        System.out.print("   ");
        for(int i = 1; i < (this.execRows + this.turRows + 1); i++){
            System.out.printf("%3d",i);
        }
        System.out.println();
        if(this.execRowSeats < this.turRowSeats){
            for(int j = 0; j < this.execRowSeats; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRows; i++){
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println();
            }
            for(int j = this.execRowSeats; j < this.turRowSeats; j++){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for (int i = 0; i < execRows; i++){
                    System.out.print("   ");
                }
                for(int i = 0; i < this.turRows; i++){
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println();
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
                System.out.println();
            }
            for(int j = this.turRowSeats; j < this.execRows; j++ ){
                System.out.printf("%3s", String.valueOf((char)(j + 65)));
                for(int i = 0; i < this.execRowSeats; i++){
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                System.out.println();
            }
        }
    }
}
