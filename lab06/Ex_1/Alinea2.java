class Alinea2{
    public static void main(String args[]){
        // Create Database
        System.out.println("Creating database");
        Database database = new Database();
        System.out.println("DONE\n");
        
        // Create Registos
        System.out.println("Creating registos");
        Registos registos = new Registos();
        System.out.println("DONE\n");

        // Create Adapter
        System.out.println("Creating adapter");
        Adapter adapter = new Adapter(database, registos);
        System.out.println("DONE\n");

        // Create Employees
        System.out.println("Creating employees");
        Employee Jose = new Employee("Jose Andre",  98374, 1600);
        Employee Ana  = new Employee("Ana Banana",  98375, 1700);
        System.out.println("DONE\n");

        // Create Empregados
        System.out.println("Creating empregados");
        Empregado Joao = new Empregado("Joao","Beirao", 98376, 1800);
        Empregado Maria = new Empregado("Maria","Sofia",98377, 1900);
        System.out.println("DONE\n");
        
        // Add persons to databases
        System.out.println("Adding persons");
        adapter.save(Jose);
        adapter.save(Ana);
        registos.insere(Joao);
        registos.insere(Maria);
        System.out.println("DONE\n");

        // Check if employes belong to company
        System.out.println("Checking belonging persons");
        System.out.print("The person with the number 98372");
        if(adapter.belongs(98372)){
            System.out.print(" belongs to the company.\n");
        }else{
            System.out.print(" doenst belong to the company.\n");
        }

        System.out.print("The person with the number 98376");
        if(adapter.belongs(98376)){
            System.out.print(" belongs to the company.\n");
        }else{
            System.out.print(" doenst belong to the company.\n");
        }
        System.out.println("DONE\n");

        // Print Databases
        System.out.println("Printing Databases");
        adapter.show();
        System.out.println("DONE\n");

        // Remove two persons
        System.out.println("Removing persons");
        adapter.remove((long)98375);
        adapter.remove(98376);
        System.out.println("DONE\n");
        
        // Print Databases
        System.out.println("Printing Databases");
        adapter.show();
        System.out.println("DONE\n");
    }
}