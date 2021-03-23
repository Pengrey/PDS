import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
public class WSSolver {
    public static void main(String[] args){
        try {
            //Read file
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<String>> puzzle = new ArrayList<ArrayList<String>>();
            ArrayList<String> words = new ArrayList<String>();
            while (sc.hasNextLine()) {
              String line = sc.nextLine();
              if(line == line.toUpperCase()){
                String[] chars = line.split("");
                ArrayList<String> l = new ArrayList<String>();
                for (String c : chars) {
                    l.add(c);
                }
                puzzle.add(l);
              }
              else{
                  String[] chars = line.split("[, ;]");
                  for (String string : chars) {
                      words.add(string.toUpperCase());
                  }
              }
            }
            sc.close();
            //Check if number of lines exceeds 40
            if(puzzle.size() > 40){
              System.out.println("Dimensions exceeds 40: " + puzzle.size());
              throw new puzzleWrongDimensions("Dimensions exceeds 40: " + puzzle.size());
            }
            for (ArrayList<String> list : puzzle) {
                if(list.size() != puzzle.size()){ // Check if puzzle is a square
                  System.out.println("Bad size: " + list.size());
                    throw new puzzleNotSquaredException("Bad size: " + list.size());
                }
                if(list.size() > 40){ //Check if number of columns exceeds 40
                  System.out.println("Dimensions exceeds 40: " + puzzle.size());
                    throw new puzzleWrongDimensions("Dimensions exceeds 40: " + puzzle.size());
                }
            }
            ArrayList<word> wordsFound = new ArrayList<word>();

            //Find every word in puzzle
            for(int x = 0; x < puzzle.size(); x++){
              for(int y = 0; y < puzzle.size(); y++){
                wordsFound = findWord(puzzle, words, wordsFound, x, y);
              }
            }

            //Sort words by length from bigger to smaller
            Collections.sort(wordsFound, new wordComparator());

            //Empty solution
            String[][] solution = new String[puzzle.size()][puzzle.size()];
            for (int i = 0; i < puzzle.size(); i++){
              for (int j = 0; j < puzzle.size(); j++){
                solution[i][j] = ".";
              }
            }

            ArrayList<word> toRemove = new ArrayList<word>();
            //Multi dimensional array used for verifications
            int[][][] vrfy = new int[puzzle.size()][puzzle.size()][wordsFound.size()];
            for(int i = 0; i < wordsFound.size();i++){
              for(int k = 0; k < puzzle.size();k++){
                for(int l = 0; l < puzzle.size();l++){
                  vrfy[k][l][i] = 0;
                }
              }
            }
            int z = 0;
            //Fill solution and verification array
            for (word w : wordsFound) {
              int x = w.getStarterX();
              int y = w.getStarterY();
              for(int i = 0; i < w.getLength(); i++){
                solution[x][y] = w.getName().substring(i, i+1);
                vrfy[x][y][z] = 1;
                x = x + w.getDirection().getX();
                y = y + w.getDirection().getY();
              }
              z++;
            }

            
            // Verifications
            int ox, oy, len, count;
            for(int w = 0 ; w < wordsFound.size() ; w++){
              for(int l = 0 ; l < wordsFound.size(); l++){
                if(w == l) continue;  // Case of comparing itself
                ox = wordsFound.get(w).getStarterX();
                oy = wordsFound.get(w).getStarterY();
                len = wordsFound.get(w).getLength();
                count = 0;
                while(len != 0){
                  if(vrfy[ox][oy][l] == 1) count++;
                  ox = ox + wordsFound.get(w).getDirection().getX();
                  oy = oy + wordsFound.get(w).getDirection().getY();
                  len--;
                }
                // Word inside of another
                if(count == wordsFound.get(w).getLength()){
                  ox = wordsFound.get(w).getStarterX();
                  oy = wordsFound.get(w).getStarterY();
                  len = wordsFound.get(w).getLength();
                  // Remove word from the layer mapping
                  while (len != 0){
                    vrfy[ox][oy][w] = 0;
                    ox = ox + wordsFound.get(w).getDirection().getX();
                    oy = oy + wordsFound.get(w).getDirection().getY();
                    len--;
                  }
                  // Blacklist word from solved list
                  toRemove.add(wordsFound.get(w));
                }
              }
            }

            //remove all blacklisted words
            for (word word : toRemove) {
              wordsFound.remove(word);
            }

            //Check for duplicated words
            Set<String> testSet = new HashSet<String>();
            for (word word : wordsFound) {
              if(!testSet.add(word.getName())){
                System.out.println("Duplicate word: " + word.getName());
                throw new duplicateWordException("Duplicate word: " + word.getName());
              }
            }

            //Check for non existing words
            for (String word : words){
              if(!testSet.contains(word)){
                System.out.println("Word " + word + " not in puzzle");
                throw new wordNotInPuzzleException("Word " + word + " not in puzzle");
              }
            }

            //Print words, their coordinates, lengths and directions
            for(int i = 0 ; i < words.size() ; i++) {
              for(int j = 0 ; j < words.size() ; j++) {
                  if(words.get(i).toUpperCase().contentEquals(wordsFound.get(j).getName())) {
                      System.out.printf("%-15s %-5s %-5s %-14s\n", wordsFound.get(j).getName(), wordsFound.get(j).getLength(), wordsFound.get(j).getStarterX() + "," + wordsFound.get(j).getStarterY(), wordsFound.get(j).getDirection());
                  } 
              }
            }

            System.out.println("\n");

            //Print puzzle solution
            for (int i = 0; i < puzzle.size(); i++){
              for (int j = 0; j < puzzle.size(); j++){
                if (j == puzzle.size() - 1){
                  System.out.print(solution[i][j] + " \n");
                }else{
                  System.out.print(solution[i][j] + " ");
                }
              }
            }

            //Save solution to file
            saveData(puzzle, solution, wordsFound, words);

          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
          } catch (puzzleNotSquaredException e){
            System.out.println("An error occurred.");
          } catch (puzzleWrongDimensions e){
            System.out.println("An error occurred.");
          } catch (duplicateWordException e){
            System.out.println("An error occurred.");
          } catch (wordNotInPuzzleException e){
            System.out.println("An error occured.");
          }
    }

    public static ArrayList<word> findWord(ArrayList<ArrayList<String>> puzzle, ArrayList<String> words, ArrayList<word> wordsFound, int x, int y){
      String firstLetter = puzzle.get(x).get(y);
      word w;
      for (String word : words) {
        if(word.substring(0, 1).equalsIgnoreCase(firstLetter)){ //Check if puzzle leter equals the first letter from word
          int x2, y2, i;
          boolean wordFound;
          for (direction dir : direction.values()) { //Check every direction for the word
            x2 = x;
            y2 = y;
            wordFound = true;
            for(i = 1; i < word.length();i++){
              x2 = x2 + dir.getX();
              y2 = y2 + dir.getY();
              //If word does not exist in this direction
              if(x2 < 0 || y2 < 0 || x2 > puzzle.size() - 1 || y2 > puzzle.size() - 1 || !(word.substring(i, i + 1).equals(puzzle.get(x2).get(y2)))){
                wordFound = false;
                break;
              }
            }
            //If word exists in this direction
            if(wordFound){
              w = new word(word,word.length(),dir, x,y);
              wordsFound.add(w);
              wordFound = false;
            }
          }
        }
      }
      return wordsFound;
    }

    public static void saveData(ArrayList<ArrayList<String>> puzzle, String[][] solution, ArrayList<word> wordsFound, ArrayList<String> words){
      // Create File
    try {
      File myObj = new File("out1.txt");
      myObj.createNewFile();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    
    // Write data to file
    try {
      FileWriter myWriter = new FileWriter("out1.txt");
      PrintWriter pr = new PrintWriter(myWriter);
      // Data writing
      for(int i = 0 ; i < words.size() ; i++) {
        for(int j = 0 ; j < words.size() ; j++) {
            if(words.get(i).toUpperCase().contentEquals(wordsFound.get(j).getName())) {
              String name, cords, direction; 
              int len;
              name = wordsFound.get(j).getName();
              len = wordsFound.get(j).getLength();
              cords = (wordsFound.get(j).getStarterX() + "," + wordsFound.get(j).getStarterY());
              direction = wordsFound.get(j).getDirection().toString();
              String result = String.format("%-15s %-5s %-5s %-14s\n", name, len, cords, direction);
              myWriter.write(result);
            } 
        }
      }

      myWriter.write("\n\n");

      for (int i = 0; i < puzzle.size(); i++){
        for (int j = 0; j < puzzle.size(); j++){
          if (j == puzzle.size() - 1){
           pr.print(solution[i][j] + " \n");
          }else{
            pr.print(solution[i][j] + " ");
          }
        }
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

