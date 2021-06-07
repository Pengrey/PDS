import java.util.ArrayList;
import java.util.Comparator;

public class InsertionAlgorithm implements Algorithm{

    ArrayList<Phone> arr;
    Comparator<Phone> comparator;

    public InsertionAlgorithm(ArrayList<Phone> arr, Comparator<Phone> c){
        this.arr = arr;
        this.comparator = c;
    }

    @Override
    public void sort() {
        int n = arr.size();
        for (int i = 1; i < n; ++i) {
            Phone phone = arr.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(arr.get(j), phone) > 0) {
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1, phone);
        }
    }
}
