import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class WSGenerator {
    public static void main(String[] args){
        /*  options:
                    -i: file to be used
                    -s: size of the wod soup to be made
        */
        
        int argc = args.length;

        //Caso seja introduzido todos os argumentos
        if(argc == 6 && args[0].charAt(0) == '-' && args[0].charAt(1) == 'i' && args[2].charAt(0) == '-' && args[2].charAt(1) == 's' && args[4].charAt(0) == '-' && args[4].charAt(1) == 'o'){	
            try {

                String fileName = args[1];
                int size = Integer.parseInt(args[3]);
                String fileOutput = args[5];

                // Check max size
                if(size > 40){
                    System.out.println("Dimensions exceeds 40");
                    throw new puzzleWrongDimensions("Dimensions exceeds 40");
                }

                ArrayList<String> wordList = new ArrayList<String>();

                ArrayList<String> dataList = new ArrayList<String>();	

                //Read wordlist
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine(); 
                    dataList.add(data);
                }
                myReader.close();

                //Input verifications
                for(int s = 0 ; s < dataList.size() ; s++) {
                    String[] arrOfStr = dataList.get(s).split("[,\\;\\ ]");	// check same line words
                    for(int i = 0 ; i < arrOfStr.length ; i++) {
                        if(arrOfStr[i].equals(arrOfStr[i].toUpperCase())){ //Check if word is only in upper case
                            System.out.println("Word " + arrOfStr[i] + " is only in upper case");
                            throw new wordUpperCaseOnly("Word " + arrOfStr[i] + " is only in upper case");
                        }
                        if(!(arrOfStr[i].matches("[a-zA-Z]+"))){ //Check if word has non alpha values
                            System.out.println("Word " + arrOfStr[i] + " has non alpha values");
                            throw new wordNonAlpha("Word " + arrOfStr[i] + " has non alpha values");
                        }
                        if(arrOfStr[i].length() > size){ // Check if word is bigger than size of puzzle
                            System.out.println("Word " + arrOfStr[i] + " is bigger than puzzle size");
                            throw new puzzleWrongDimensions("Word " + arrOfStr[i] + " is bigger than puzzle size");
                        }
                        wordList.add(arrOfStr[i].toUpperCase());	// add words discovered and turn upper case
                    }
                }

                if(wordList.size() > (size - 2)){ // Check if size given is too small for number of words
                    System.out.println("Size too small for number of words given.");
                    throw new puzzleWrongDimensions("Size too small for number of words given.");
                }

                String[][] wordSoup = wsGen(wordList, size); //Generate word soup
                saveData(dataList,wordSoup, fileOutput); // Save word soup in file
                System.exit(0);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            } catch (puzzleWrongDimensions e) {
                System.out.println("An error occurred.");
            } catch (wordUpperCaseOnly e){
                System.out.println("An error occurred.");
            } catch (wordNonAlpha e){
                System.out.println("An error occurred.");
            }
        }

        //Caso em que não introduzam size nem nome de ficheiro
        if(argc == 2 && args[0].charAt(0) == '-' && args[0].charAt(1) == 'i'){
            try {

                String fileName = args[1];
                int size = 1;

                ArrayList<String> wordList = new ArrayList<String>();

                ArrayList<String> dataList = new ArrayList<String>();	

                //Read wordlist
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);

                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine(); 
                    dataList.add(data);
                }
                myReader.close();

                //Input verifications
                for(int s = 0 ; s < dataList.size() ; s++) {
                    String[] arrOfStr = dataList.get(s).split("[,\\;\\ ]");	// check same line words
                    for(int i = 0 ; i < arrOfStr.length ; i++) {
                        if(arrOfStr[i].equals(arrOfStr[i].toUpperCase())){ //Check if word is only in upper case
                            System.out.println("Word " + arrOfStr[i] + " is only in upper case");
                            throw new wordUpperCaseOnly("Word " + arrOfStr[i] + " is only in upper case");
                        }
                        if(!(arrOfStr[i].matches("[a-zA-Z]+"))){ //Check if word has non alpha values
                            System.out.println("Word" + arrOfStr[i] + " has non alpha values");
                            throw new wordNonAlpha("Word" + arrOfStr[i] + " has non alpha values");
                        }
                        if(arrOfStr[i].length() > size){ // Check if word is bigger than size of puzzle
                            size = arrOfStr[i].length();
                        }
                        wordList.add(arrOfStr[i].toUpperCase());	// add words discovered and turn upper case
                    }
                }

                if(wordList.size() > size) size = wordList.size(); // Check if size given is too small for number of words
                String[][] wordSoup = wsGen(wordList, size + 2); //Generate word soup
                saveData(dataList,wordSoup, "sopa1.txt"); //Save word soup in file
                System.exit(0);
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
            } catch (wordUpperCaseOnly e){
                System.out.println("An error occurred.");
            } catch (wordNonAlpha e){
                System.out.println("An error occurred.");
            }
        }
        // Help message
        System.out.println("usage: -i file  # gives file with word soup words");
        System.out.println("       -s size  # gives size for the word soup (max size: 40)");
        System.exit(0);
    }

    public static String[][] wsGen(ArrayList<String> wordList, int size){
        
        //Empty word soup
        String[][] wordSoup = new String[size][size];
        for(int i = 0; i < size;i++){
            for(int j = 0; j < size; j++){
                wordSoup[i][j] = "";
            }
        }

        //Multi dimensional array used for verifications
        int[][][] vrfy = new int[size][size][wordList.size()];
        for(int i = 0; i < wordList.size(); i++){
            boolean fits = false;
            String word = wordList.get(i).toUpperCase();
            while(!fits){
                int[] cords = generateCords(size); //Generate random coordinates for start of word
                int starterX = cords[0];
                int starterY = cords[1];
                int starterLength = word.length();
                direction dir = direction.randomDirection(); //Generate random direction
                int len = starterLength;
                int x = starterX;
                int y = starterY;
                int count;
                //Check if word fits
                while(len != 0){
                    if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == "" || wordSoup[x][y] == word.substring(len - 1, len))){
                        vrfy[x][y][i] = 1;
                        x = x + dir.getX();
                        y = y + dir.getY();
                        len--;
                    } else{
                        while (len != starterLength){
                            x = x - dir.getX();
                            y = y - dir.getY();
                            len++;
                            vrfy[x][y][i] = 0;
                        }
                        fits =false;
                        break;
                    }
                    fits = true;

                }

                //If the word fits, check if it is not inside another word
                if(fits){
                    for (int w = 0; w < i; w++){
                        x = starterX;
                        y = starterY;
                        len = starterLength;
                        count = 0;
                        while(len != 0){
                            if(vrfy[x][y][w] == 1){
                                count++;
                            }
                            x = x + dir.getX();
                            y = y + dir.getY();
                            len--;
                        }

                        if(count == starterLength){
                            x = starterX;
                            y = starterY;
                            len = starterLength;
                            while(len != 0){
                                vrfy[x][y][i] = 0;
                                x = x + dir.getX();
                                y = y + dir.getY();
                                len--;
                            }
                            fits = false;
                            break;
                        }

                    }

                    //If all verifications are done and word still fits, put it in word soup
                    if(fits){
                        x = starterX;
                        y = starterY;
                        len = starterLength;
                        while(len != 0){
                            wordSoup[x][y] = word.substring(len - 1, len);
                            x = x + dir.getX();
                            y = y + dir.getY();
                            len--;
                        }
                    }
                }
            }
        }

        //Generate random letters for empty spaces
        System.out.println();
	    for(int i = 0 ; i < size ; i++) {
	    	for(int j = 0 ; j < size ; j++) {
	    		if(wordSoup[i][j] == "") {
	    			// Generate random char in uppercase
                    Random r = new Random();
                    char c = (char)(r.nextInt(26) + 'A');
                    String s = String.valueOf(c);
                    wordSoup[i][j] = s;
                }
	    	}
	    }

        return wordSoup;
        
    }

    public static void saveData(ArrayList<String> dataList, String[][] wordSoup, String FileOutput){
        // Create File
      try {
        File myObj = new File(FileOutput);
        myObj.createNewFile();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      
      // Write data to file
      try {
        FileWriter myWriter = new FileWriter(FileOutput);
        // Data writing
        int size = wordSoup.length;
        for(int i = 0 ; i < size ; i++) {
	    	for(int j = 0 ; j < size ; j++) {
	    		myWriter.write(wordSoup[i][j]);
                System.out.print(wordSoup[i][j]);
	    	}
	    	myWriter.write("\n");
            System.out.print("\n");
	    }
        size = dataList.size();
        for(int i = 0 ; i < size ; i++) {
            myWriter.write(dataList.get(i) + "\n");
            System.out.print(dataList.get(i) + "\n");
        }
        myWriter.close();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    
    public static int[] generateCords(int size){
        int[] cords = new int[2];
        Random r = new Random();
        cords[0] = r.nextInt(size);
        Random s = new Random();
        cords[1] = s.nextInt(size);
        return cords;
    }
}