import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<Phone> array = new ArrayList<>();
        array.add(new Phone(1000, 16, 18, "A14 Bionic"));
        array.add(new Phone(500, 8, 13, "Snapdragon 888"));
        array.add(new Phone(1200, 4, 13, "Exynos 2100"));
        array.add(new Phone(200, 2, 10, "Kirin 950"));
        array.add(new Phone(800, 8, 12, "Dimensity 1100"));

        Comparator<Phone> comp = null;
        Algorithm algorithm = null;

        Scanner sc = new Scanner(System.in);
        
        System.out.println("---Order by---");
        System.out.println("1) Price");
        System.out.println("2) Memory");
        System.out.println("3) Camera");
        System.out.println("4) Processor");
        System.out.print(">>");
        int parameter = sc.nextInt();
        
        System.out.println("---Order---");
        System.out.println("1) Ascending");
        System.out.println("2) Descending");
        System.out.print(">>");
        int order = sc.nextInt();
        
        System.out.println("---Algorithm---");
        System.out.println("1) Bubble Sort");
        System.out.println("2) Insertion Sort");
        System.out.println("3) Selection Sort");
        System.out.print(">>");
        int algOpt = sc.nextInt();

        sc.close();
        
        switch(parameter)
        {
            case 1 :
                if(order == 1){
                    comp = new priceCompAscending();
                }else{
                    comp = new priceCompDescending();
                }
                break;
                
            case 2 :
                if(order == 1){
                    comp = new memCompAscending();
                }else{
                    comp = new memCompDescending();
                }
                break;
                
            case 3:
                if(order == 1){
                    comp = new cameraCompAscending();
                }else{
                    comp = new cameraCompDescending();
                }
                break;
            
            case 4:
                if(order == 1){
                    comp = new brandCompAscending();

                }else{
                    comp = new brandCompDescending();
                }
                break;

        }
        switch(algOpt)
        {
            case 1 :
                algorithm = new BubbleAlgorithm(array, comp);
                algorithm.sort();
                break;
                
            case 2 :
                algorithm = new InsertionAlgorithm(array, comp);
                algorithm.sort();
                break;
            case 3:
                algorithm = new SelectionAlgorithm(array, comp);
                algorithm.sort();
                break;
        }
        System.out.println("Result:");
        for (Phone p : array) {
            System.out.println(p);
            
        }
    }

    static class cameraCompAscending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getCamera() > p2.getCamera() ? 1 : p1.getCamera() == p2.getCamera() ? 0 : -1;
        }
    }
    
    static class cameraCompDescending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getCamera() < p2.getCamera() ? -1 : p1.getCamera() == p2.getCamera() ? 0 : 1;
        }
    } 

    static class priceCompAscending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getPrice() > p2.getPrice() ? 1 : p1.getPrice() == p2.getPrice() ? 0 : -1;
        }
    }
    
    static class priceCompDescending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getPrice() < p2.getPrice() ? -1 : p1.getPrice() == p2.getPrice() ? 0 : 1;
        }
    }
    
    static class memCompAscending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getMemory() > p2.getMemory() ? 1 : p1.getMemory() == p2.getMemory() ? 0 : -1;
        }
    }
    
    static class memCompDescending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getMemory()< p2.getMemory() ? -1 : p1.getMemory() == p2.getMemory() ? 0 : 1;
        }
    }
    
    static class brandCompAscending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return p1.getProcessor().compareToIgnoreCase(p2.getProcessor());
        }
    }
    
    static class brandCompDescending implements Comparator<Phone> {
        @Override
        public int compare(Phone p1, Phone p2) {
            return - p1.getProcessor().compareToIgnoreCase(p2.getProcessor());
        }
    } 
}
