import java.util.ArrayList;
import java.util.Comparator;

public class SelectionAlgorithm implements Algorithm{

    ArrayList<Phone> arr;
    Comparator<Phone> comparator;

    public SelectionAlgorithm(ArrayList<Phone> arr, Comparator<Phone> c){
        this.arr = arr;
        this.comparator = c;
    }

    @Override
    public void sort() {
        int n = arr.size();
        for (int i = 0; i < n-1; i++) { 
            int min_idx = i; 
            for (int j = i+1; j < n; j++){
                if (comparator.compare(arr.get(j),arr.get(min_idx)) < 0){
                    min_idx = j;
                } 
                Phone temp = arr.get(min_idx);
                arr.set(min_idx, arr.get(i));
                arr.set(i, temp);
            }
                 
        } 
    }
    
}
