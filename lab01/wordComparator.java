import java.util.Comparator;

public class wordComparator implements Comparator<word> {
    public int compare(word o1, word o2) {
        if(o1.getLength() < o2.getLength()){
            return -1;
        } else if (o1.getLength() > o2.getLength()){
            return 1;
        } else{
            return 0;
        }
    }
}
