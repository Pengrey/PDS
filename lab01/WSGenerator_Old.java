import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;


public class WSGenerator_Old {
    // Generate a random position in the wordsoup
    public static int[] generateCords(int size){
        int[] cords = new int[2];
        Random r = new Random();
        cords[0] = r.nextInt(size);
        Random s = new Random();
        cords[1] = s.nextInt(size);
        return cords;
    }

    // Generate a random orientation in the wordsoup
    public static String generateOri(){
        String Orientation[] = {"DownLeft","Down","DownRight","UpLeft","Up","UpRight","Left","Right"};
        Random r = new Random();
        int n = r.nextInt(8);
        return Orientation[n];
    } 

    // Generate word soup
    public static char[][] wsGen(ArrayList<String> wordList, int size){
        char[][] wordSoup = new char[size][size];
        int[][][] vrfy = new int[size][size][wordList.size()];
        for(int i = 0 ; i < wordList.size() ; i++){
            boolean fits = false;
            String word = wordList.get(i).toUpperCase();
            //System.out.println(word);
            while(!fits){
                int[] cords = generateCords(size);
                int x = cords[0];
                int y = cords[1];
                String ori = generateOri();
                int starterLength =  word.length();
                int len = starterLength;
                int ox = x;
                int oy = y; 
                switch(ori) {
                    case "UpRight":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x++;
                                            y--;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x--;
                                                y++;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x++;
                                                y--;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x++;
                                                    y--;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x++;
                                                y--;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break;
                    case "Up":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x++;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x--;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x++;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x++;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x++;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break;
                    case "UpLeft":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x++;
                                            y++;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x--;
                                                y--;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x++;
                                                y++;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x++;
                                                    y++;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x++;
                                                y++;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break;
                    case "DownRight":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x--;
                                            y--;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x++;
                                                y++;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x--;
                                                y--;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x--;
                                                    y--;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x--;
                                                y--;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break;
                    case "Down":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x--;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x++;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x--;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x--;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x--;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break;   
                    case "DownLeft":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            x--;
                                            y++;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                x++;
                                                y--;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                x--;
                                                y++;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    x--;
                                                    y++;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                x--;
                                                y++;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break; 
                    case "Right":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            y--;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                y++;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                y--;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    y--;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                y--;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break; 
                    case "Left":
                                    while(starterLength != 0) {
                                        if(x >= 0 && x < size && y >= 0 && y < size && (wordSoup[x][y] == 0 || wordSoup[x][y] == word.charAt(starterLength - 1))){
                                            vrfy[x][y][i] = 1;
                                            y++;
                                            starterLength--;
                                        }else{
                                            while(starterLength != len){
                                                y--;
                                                starterLength++;
                                                vrfy[x][y][i] = 0;
                                            }
                                            fits = false;
                                            break;
                                        }
                                        fits = true;
                                    }

                                    // Check if any word nests the new one in the same position
                                    if(fits){
                                        for (int w = 0; w < i; w++) {
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            int count = 0;
                                            while (starterLength != 0) {
                                                if (vrfy[ox][oy][w] == 1) count++;
                                                y++;
                                                starterLength--;
                                            }

                                            if (count == len) {
                                                //System.out.println("NESTED");
                                                x = ox;
                                                y = oy;
                                                starterLength = len;
                                                while (starterLength != 0) {
                                                    vrfy[x][y][i] = 0;
                                                    y++;
                                                    starterLength--;
                                                }
                                                fits = false;
                                                break;
                                            }
                                        }
                                        if(fits){
                                            x = ox;
                                            y = oy;
                                            starterLength = len;
                                            while (starterLength != 0) {
                                                wordSoup[x][y] = word.charAt(starterLength - 1);
                                                y++;
                                                starterLength--;
                                            }
                                        }
                                    }
                                    break; 
                }
            }
        }
        /*
        // Print the word soup, debug
	    System.out.println();
	    for(int i = 0 ; i < size ; i++) {
	    	for(int j = 0 ; j < size ; j++) {
	    		if(wordSoup[i][j] != 0) {
	    			System.out.print(wordSoup[i][j] + " ");
	    		}else {
	    			System.out.print(". ");
	    		}
	    	}
	    	System.out.println();
	    }

        System.out.println();
        */
        // Fill blank spaces with random letters
        System.out.println();
	    for(int i = 0 ; i < size ; i++) {
	    	for(int j = 0 ; j < size ; j++) {
	    		if(wordSoup[i][j] == 0) {
	    			// Generate random char in uppercase
                    Random r = new Random();
                    char c = (char)(r.nextInt(26) + 'A');
                    wordSoup[i][j] = c;
                }
	    	}
	    }

        /*
        // Print the word soup, debug
        System.out.println();
	    System.out.println();
	    for(int i = 0 ; i < size ; i++) {
	    	for(int j = 0 ; j < size ; j++) {
	    		if(wordSoup[i][j] != 0) {
	    			System.out.print(wordSoup[i][j] + " ");
	    		}else {
	    			System.out.print(". ");
	    		}
	    	}
	    	System.out.println();
	    }
        */

        return wordSoup;
    }

    // Save data in file
    public static void saveData(ArrayList<String> dataList, char[][]wordSoup){
      // Create File
      try {
        File myObj = new File("sdl_01.txt");
        myObj.createNewFile();
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
      
      // Write data to file
      try {
        FileWriter myWriter = new FileWriter("sdl_01.txt");
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


    public static void main(String[] args) {
        /*  options:
                    -i: file to be used
                    -s: size of the wod soup to be made
        */
        int argc = args.length;

        // Given only the words file
        if(argc == 2 && args[0].charAt(0) == '-' && args[0].charAt(1) == 'i'){
            String fileName = args[1];
            int size = 1;
            ArrayList<String> wordList = new ArrayList<String>();

            // Get data from file
            ArrayList<String> dataList = new ArrayList<String>();	
            try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine(); 
                  dataList.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // Get words from data 
            for(int s = 0 ; s < dataList.size() ; s++) {
                String[] arrOfStr = dataList.get(s).split("[,\\;\\ ]");	// check same line words
                for(int i = 0 ; i < arrOfStr.length ; i++) {
                    if(arrOfStr[i].equals(arrOfStr[i].toUpperCase())){
                        System.out.println("Words not only in lowercase or mixed");
                        //termina o programa
                        return;
                    }
                    if(!(arrOfStr[i].matches("[a-zA-Z]+"))){
                        System.out.println("Words have non alpha values");
                        //termina o programa
                        return;
                    }
                    if(arrOfStr[i].length() > size) size = arrOfStr[i].length();
                    wordList.add(arrOfStr[i].toUpperCase());	// add words discovered and turn upper case
                }
            }
            if(wordList.size() > size) size = wordList.size();
            char[][] wordSoup = wsGen( wordList, size + 2);
            saveData(dataList,wordSoup);
            return;
        }

        // Given all args
        if(argc == 4 && args[0].charAt(0) == '-' && args[0].charAt(1) == 'i' && args[2].charAt(0) == '-' && args[2].charAt(1) == 's'){
            String fileName = args[1];
            int size = Integer.parseInt(args[3]);
            // Check max size
            if(size > 40){
                System.out.print("Max size 40");
                return;
            }

            ArrayList<String> wordList = new ArrayList<String>();

            // Get data from file
            ArrayList<String> dataList = new ArrayList<String>();	
            try {
                File myObj = new File(fileName);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                  String data = myReader.nextLine(); 
                  dataList.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // Get words from data 
            for(int s = 0 ; s < dataList.size() ; s++) {
                String[] arrOfStr = dataList.get(s).split("[,\\;\\ ]");	// check same line words
                for(int i = 0 ; i < arrOfStr.length ; i++) {
                    if(arrOfStr[i].equals(arrOfStr[i].toUpperCase())){
                        System.out.println("Words not only in lowercase or mixed");
                        //termina o programa
                        return;
                    }
                    if(!(arrOfStr[i].matches("[a-zA-Z]+"))){
                        System.out.println("Words have non alpha values");
                        //termina o programa
                        return;
                    }
                    if(arrOfStr[i].length() > size){
                        System.out.println("At least one word given doesn't fit in the size provided.");
                        return;
                    }
                    wordList.add(arrOfStr[i].toUpperCase());	// add words discovered and turn upper case
                }
            }
            char[][] wordSoup = wsGen( wordList, size);
            saveData(dataList,wordSoup);
            return;
        }
        // Help message
        System.out.println("usage: -i file  # gives file with word soup words");
        System.out.println("       -s size  # gives size for the word soup");
        return;
    }
}