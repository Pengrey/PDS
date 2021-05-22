import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

public class Demo {
    private static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        AddCommand addCommand = new AddCommand(list);
        RemoveCommand removeCommand = new RemoveCommand(list);
        
        addCommand.execute("Element1");
        addCommand.execute("Element2");
        addCommand.execute("Element3");
        System.out.println("\nInitial list");
        printList();

        addCommand.undo();
        System.out.println("After add undo");
        printList();

        removeCommand.execute("Element2");
        System.out.println("After remove command");

        printList();

        removeCommand.undo();
        System.out.println("After remove undo");

        printList();
    }

    public static void printList(){
        System.out.println("Size of list: " + list.size());
        System.out.print("List elements: ");
        for (String string : list) {
            System.out.print(string + " ");
        }
        System.out.println("\n");
    }
}
