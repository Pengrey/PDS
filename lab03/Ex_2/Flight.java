//=================Done by=========================
//          Rodrigo Lima nMec 98475
//          Gonçalo Machado nMec 98359
//=================================================

public class Flight {
    
    private String code;                                                            //Flight code
    private int reservationCounter;                                                 //Counter to know the number of the next reservation
    private int execRowSeats;                                                       //Number of seats in an executive row
    private int execRows;                                                           //Number of executive rows
    private int turRowSeats;                                                        //Number of seats in a turistic row
    private int turRows;                                                            //Number of turistic rows
    private int[][] execSeats;                                                      //Bidimensional array that represents the executive seats on the flight«
    private int[][] turSeats;                                                       //Bidimensional array that represents the turistic seats on the flight
    

    //Flight construtor when the flight only has turistic seats
    public Flight(String code, String turSeats){
        this.code = code;                                                           //Flight code
        this.reservationCounter = 1;                                                //Start on 1 cause there is no reservation with number 0
        String[] turConfig = turSeats.split("x");                                   //Since the var turSeats comes in a format like 3x2, split it to get the 3 and 2
        this.turRows = Integer.parseInt(turConfig[0]);                              //In the example given, turRows is the 3, which means there are 3 rows of turistic seats
        this.turRowSeats = Integer.parseInt(turConfig[1]);                          //In the example given, turRowSeats is the 2, which means there are 2 seats in each turistic row
        this.turSeats = new int[turRowSeats][turRows];                              //Bidimensional array that represents the turistic seats on the flight
    }

    //Flight construtor when the flight has turistic seats and executive seats
    public Flight(String code, String execSeats, String turSeats){
        this.code = code;                                                           //Flight code
        this.reservationCounter = 1;                                                //Start on 1 cause there is no reservation with number 0
        String[] turConfig = turSeats.split("x");                                   //Since the var turSeats comes in a format like 3x2, split it to get the 3 and 2
        this.turRows = Integer.parseInt(turConfig[0]);                              //In the example given, turRows is the 3, which means there are 3 rows of turistic seats
        this.turRowSeats = Integer.parseInt(turConfig[1]);                          //In the example given, turRowSeats is the 2, which means there are 2 seats in each turistic row
        this.turSeats = new int[turRowSeats][turRows];                              //Bidimensional array that represents the turistic seats on the flight
        String[] execConfig = execSeats.split("x");                                 //Since the var execSeats comes in a format like 3x2, split it to get the 3 and 2
        this.execRows = Integer.parseInt(execConfig[0]);                            //In the example given, execRows is the 3, which means there are 3 rows of executive seats
        this.execRowSeats = Integer.parseInt(execConfig[1]);                        //In the example given, execRowSeats is the 2, which means there are 2 seats in each executive row
        this.execSeats = new int[execRowSeats][execRows];                           //Bidimensional array that represents the executive seats on the flight
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

    //Function to cancel a reservation
    public void cancelReservation(int numReservation){
        //This nested for checks if the reservation that we want to cancel is for executive seats, and if so, puts the seat value equal to 0, which means no reservation
        for(int j = 0; j < this.execRows; j++){
            for(int i = 0; i < this.execRowSeats;i++){
                if(this.execSeats[i][j] == numReservation){
                    this.execSeats[i][j] = 0;
                }
            }
        }
        //This nested for checks if the reservation that we want to cancel is for turistic seats, and if so, puts the seat value equal to 0, which means no reservation
        for(int j = 0; j < this.turRows; j++){
            for(int i = 0; i < this.turRowSeats;i++){
                if(this.turSeats[i][j] == numReservation){
                    this.turSeats[i][j] = 0;
                }
            }
        }
    }

    //Function to add a reservation
    public void addReservation(String type,int numSeats){
        String[] seatsUsed;                                                                                     //String[] is used to save the seats that were reservated
        if(type.equals("T")){                                                                                   //Reservation in turistic seats
            seatsUsed = addReservationSupport(this.turSeats, numSeats, true);                                   //Call addReservationSupport() to reserve the turistic seats
        }else{                                                                                                  //Reservation for executive seats
            seatsUsed = addReservationSupport(this.execSeats, numSeats, false);                                 //Call addReservationSupport() to reserve the executive seats
        }
        if(seatsUsed[numSeats-1] == null){                                                                      //If the reservation was not made
            System.out.println("Não foi possivel obter lugares para a reserva: " + type + " " + numSeats);
        }else{                                                                                                  //If the reservation was made
            System.out.print(this.code + ":" + (this.reservationCounter-1) + " = ");
            for (String string : seatsUsed) {
                System.out.print(" " + string + " |");
            }
            System.out.println("");
        }
    }

    //In order to not have an if and an else in the addReservation() function that would only differ on the array used, this function was created
    //It will check if the reservation can be made and if so, does it
    private String[] addReservationSupport(int[][] xSeats,int numSeats, boolean isTur){
        int startRow = 1;                                                   //Variable used on line 127 and 146, important only for printing purposes
        if(isTur){                                                          //If the reservation is for turistic seats
            startRow = this.execRows + 1;                                   //Turistic seats start after executive, so we need to sum the executive rows + 1 to get the start of the turistic rows
        }
        String[] seatsUsed = new String[numSeats];                          //String[] used to save the seats reserved
        int seatsUsedCounter = 0;                                           //Counter for number of seats reserved
        int xRowSeats = xSeats.length;                                      //Number of seats in a row
        int xRows = xSeats[0].length;                                       //Number of rows
        int j, emptySeats;
            for(j = 0; j < xRows; j++){                                     //For each row
                emptySeats = 0;                                             //Counter for empty seats
                for(int i = 0; i < xRowSeats;i++){                          //For each seat in the row
                    if(xSeats[i][j] == 0){                                  //If a reservation was not made
                        emptySeats++;                                       //Increase the empty seats by 1
                    }
                }
                if(emptySeats == xRowSeats){                                //If the row is empty
                    for(int x = j; x < xRows; x++){                         //For each row starting on the empty row
                        for(int y = 0; y < xRowSeats; y++){                 //For each seat
                            xSeats[y][x] = reservationCounter;              //Reserve the seat
                            seatsUsed[seatsUsedCounter] = String.valueOf(x + startRow) + String.valueOf((char)(y + 65)); // add the reserved seat to the String[]
                            seatsUsedCounter++;                             //Increase the counter for number of seats reserved by 1
                            numSeats--;                                     //Decrease the counter for the number of seats that still need to be reserved by 1
                            if(numSeats == 0){                              //If all seats requested to be reserved are reserved, end the function
                                this.reservationCounter++;
                                return seatsUsed;
                            }
                        }                        
                    }
                }
            }

            if(numSeats != 0){                                              //If there are no empty rows left and not all seats requested are reserved
                for(j = 0; j < xRows; j++){                                 //For each row
                    for(int i = 0; i < xRowSeats;i++){                      //For each seat
                        if(xSeats[i][j] == 0){                              //If the seat is not reserved
                            xSeats[i][j] = reservationCounter;              //Reserve the seat
                            seatsUsed[seatsUsedCounter] = String.valueOf(j + startRow) + String.valueOf((char)(i + 65)); // add the reserved seat to the String[]
                            seatsUsedCounter++;                             //Increase the counter for number of seats reserved by 1
                            numSeats--;                                     //Decrease the counter for the number of seats that still need to be reserved by 1
                            if(numSeats == 0){                              //If all seats requested to be reserved are reserved, end the function
                                this.reservationCounter++;
                                return seatsUsed;
                            }
                        }
                    }
                }
            }
            if(numSeats !=0){                                               //If there are no empty seats and there is still seats that need to be reserved, then the reservation cant be made
                for(j = 0; j < xRows; j++){                                 //For each row
                    for(int i = 0; i < xRowSeats;i++){                      //For each seat in the row
                        if(xSeats[i][j] == reservationCounter){             //If it was reserved
                            xSeats[i][j] = 0;                               //Remove the reservation, since it was not completed
                        }
                    }
                }
                return seatsUsed;
            }
            return seatsUsed;
    }

    //Function that prints the flight configuration, showing which seats have been reserved or not
    public void printFlight(){
        System.out.println();
        System.out.println(this.code);                                      //Prints flight code
        System.out.print("   ");
        for(int i = 1; i < (this.execRows + this.turRows + 1); i++){        //Prints the first row, where the row numbers are displayed
            System.out.printf("%3d",i);
        }
        System.out.println();
        //If the number of seats per row is lower in executive than in turistic,
        //we need to treat the blank space on the lines where there is turistic seats, but not executive seats
        if(this.execRowSeats < this.turRowSeats){
            for(int j = 0; j < this.execRowSeats; j++){                     //For each line of seats
                System.out.printf("%3s", String.valueOf((char)(j + 65)));   //First print the letter that represents that line
                for(int i = 0; i < this.execRows; i++){                     //Then print the exec seats
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                for(int i = 0; i < this.turRows; i++){                      //Then print the turistic seats
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println();
            }
            for(int j = this.execRowSeats; j < this.turRowSeats; j++){      //For each line of seats that has turistic seats, but does not have executive seats
                System.out.printf("%3s", String.valueOf((char)(j + 65)));   //First print the letter that represents that line
                for (int i = 0; i < execRows; i++){                         //Then print blank spaces in the executive rows
                    System.out.print("   ");
                }
                for(int i = 0; i < this.turRows; i++){                      //Then print the turistic seats
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println();
            }
        }else{
            for(int j = 0; j < this.turRowSeats; j++){                      //For each line of seats
                System.out.printf("%3s", String.valueOf((char)(j + 65)));   //First print the letter that represents that line
                for(int i = 0; i < this.execRows; i++){                     //Then print the executive seats
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                for(int i = 0; i < this.turRows; i++){                      //Then print the turistic seats
                    System.out.printf("%3d", this.turSeats[j][i]);
                }
                System.out.println();
            }
            for(int j = this.turRowSeats; j < this.execRows; j++ ){         //For each line of seats that has executive seats, but does not have turistic seats
                System.out.printf("%3s", String.valueOf((char)(j + 65)));   //First print the letter that represents that line
                for(int i = 0; i < this.execRowSeats; i++){                 //Then print the executive seats
                    System.out.printf("%3d", this.execSeats[j][i]);
                }
                System.out.println();
            }
        }
        System.out.println();
    }
}
