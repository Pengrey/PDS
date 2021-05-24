import java.util.Iterator;
import java.util.ListIterator;

public class testVector {
    public static void main(String[] args) {
        // Generate vector and add 5 elements
        VectorGeneric<Integer> intVector = new VectorGeneric<>();
        for (int i = 1 ; i < 5 ; i++) intVector.addElem(i);
        
        // Iterator
        System.out.println("Iterator:");
        Iterator<Integer> simpleIterator = intVector.iterator();
        while(simpleIterator.hasNext()) System.out.println(simpleIterator.next());

        // List Iterator
        System.out.println("\nList Iterator:");
        ListIterator<Integer> listIterator = intVector.listIterator();

        System.out.println("\nForward");
        while(listIterator.hasNext()) System.out.printf("Index: %d, Value: %d\n", listIterator.nextIndex() - 1, listIterator.next());

        System.out.println("\nBackward");
        while(listIterator.hasPrevious()) System.out.printf("Index: %d, Value: %d\n", listIterator.previousIndex() + 1, listIterator.previous());

        // List Iterator
        System.out.println("\nList Iterator (with index starting at 2):");
        ListIterator<Integer> listIteratorIndex = intVector.listIterator(2);

        System.out.println("\nForward");
        while(listIteratorIndex.hasNext()) System.out.printf("Index: %d, Value: %d\n", listIteratorIndex.nextIndex() - 1, listIteratorIndex.next());

        listIteratorIndex = intVector.listIterator(2);
        System.out.println("\nBackward");
        while(listIteratorIndex.hasPrevious()) System.out.printf("Index: %d, Value: %d\n", listIteratorIndex.previousIndex() + 1, listIteratorIndex.previous());

        // Use of 2 iterators at the same time
        System.out.println("\nList Iterator (with index starting at 2) and Iterator");
        listIteratorIndex = intVector.listIterator(2);
        simpleIterator = intVector.iterator();

        while(listIterator.hasNext()){
            System.out.printf("List Iterator: %d\n",listIterator.next());
            System.out.printf("Iterator: %d\n", simpleIterator.next());
        }
    }    
}
