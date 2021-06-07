import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    ArrayList<Book> inventory;
    
    public Library (ArrayList<Book> i){
        this.inventory = i;
    }

    public ArrayList<Book> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<Book> inventory) {
        this.inventory = inventory;
    }

    public void printInventory(){
        System.out.println("***Biblioteca***");
        for(int i = 0; i < inventory.size(); i++){
            System.out.println((i+1) + "   " + inventory.get(i).toString());
        }
    }

    public void registerBook(int pos){
        inventory.get(pos).registerBook();
    }

    public void requestBook(int pos){
        inventory.get(pos).requestBook();
    }

    public void returnBook(int pos){
        inventory.get(pos).returnBook();
    }

    public void reserveBook(int pos){
        inventory.get(pos).reserveBook();
    }

    public void cancelReservedBook(int pos){
        inventory.get(pos).cancelReservedBook();
    }

    public void menu(){
        String inputNotParsed;
        String[] inputParsed;
        int opt = 0;
        int book = 0;
        Scanner sc = new Scanner(System.in);
        while(true){
            printInventory();
            System.out.println(">> <livro>,<operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela;");
            System.out.print(">> ");
            inputNotParsed = sc.next();
            inputParsed = inputNotParsed.split(",");
            book = Integer.valueOf(inputParsed[0]) - 1;
            opt = Integer.valueOf(inputParsed[1]);
            switch(opt){
                case 1:
                    registerBook(book);
                    break;
                case 2:
                    requestBook(book);
                    break;
                case 3:
                    returnBook(book);
                    break;
                case 4:
                    reserveBook(book);
                    break;
                case 5:
                    cancelReservedBook(book);
                    break;
                default:
                    System.out.println("A opção escolhida não é valida.");
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<Book> inventory = new ArrayList<>();
        inventory.add(new Book("Java Anti-Stress", "ISBN1", "2010", "Omodionah"));
        inventory.add(new Book("A Guerra dos Padrões", "ISBN2", "2011", "Jorge Omel"));
        inventory.add(new Book("A Procura da Luz", "ISBN3", "2012", "Khumatkli"));
        Library library = new Library(inventory);
        library.menu();
    }
}
