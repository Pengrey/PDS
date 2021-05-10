import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextReader implements ReaderInterface {

    private File file;
    private Scanner sc;
    private boolean reset;

    public TextReader(String f){
        try {
            this.file = new File(f);
        } catch (Exception e) {
            System.out.println("File could not be found");
        }
        try {
            this.sc = new Scanner(this.file);
        } catch (Exception e) {
            System.out.println("Unexpected Error");
        }
        this.reset = false;
    }

    public boolean hasNext() {
        if(sc.hasNext()){
            return sc.hasNext();
        }else{
                try {
                    sc = new Scanner(this.file);
                } catch (FileNotFoundException e) {
                    System.out.println("file not found");
                }
            return false;
        }
    }

    public String next() {
        if(hasNext()){
            return this.sc.nextLine();
        }
        return null;
    }
    
}
