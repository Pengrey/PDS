import java.util.ArrayList;
import java.util.Comparator;

public class BubbleAlgorithm implements Algorithm {

    ArrayList<Phone> arr;
    Comparator<Phone> comparator;

    public BubbleAlgorithm(ArrayList<Phone> arr, Comparator<Phone> c){
        this.arr = arr;
        this.comparator = c;
    }

    @Override
    public void sort() {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++){
            for (int j = 0; j < n - i - 1; j++){
                if (comparator.compare(arr.get(j), arr.get(j+1)) > 0) {
                    Phone temp = arr.get(j);
                    arr.set(j, arr.get(j+1));
                    arr.set(j+1, temp);
                }
            }
        }        
    }
}
