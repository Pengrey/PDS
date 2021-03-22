import java.util.Random;

public class test {
    // Generate a random position in the wordsoup
    public static int[] generateCords(int size){
        int[] cords = new int[2];
        Random r = new Random();
        cords[0] = r.nextInt(size + 1);
        Random s = new Random();
        cords[1] = s.nextInt(size + 1);
        return cords;
    }
    public static void main(String[] args){
        int[] cords = generateCords(6);
        System.out.print("x: " + cords[0] + " y: " + cords[1]);
    }
}