class Alinea1{
    public static void testemployees(){
        // Create Employees
        System.out.println("Creating employees");
        Employee Jose = new Employee("Jose Andre",  98374, 1600);
        Employee Ana  = new Employee("Ana Banana",  98375, 1700);
        Employee Joao = new Employee("Joao Beirao", 98376, 1800);
        Employee Maria = new Employee("Maria Sofia",98377, 1900);
        System.out.println("DONE\n");

        // Create Database
        System.out.println("Creating database");
        Database database = new Database();
        System.out.println("DONE\n");

        // Show Database
        System.out.println("Printing database");
        for (Employee emp : database.getAllEmployees()){
            System.out.printf("(%s, %d, %.2f)\n",emp.getName(),emp.getEmpNum(),emp.getSalary());
        }
        System.out.println("DONE\n");

        // Add Employees
        System.out.println("Adding employees");
        database.addEmployee(Jose);
        database.addEmployee(Ana);
        database.addEmployee(Joao);
        database.addEmployee(Maria);
        System.out.println("DONE\n");

        // Show Database
        System.out.println("Printing database");
        for (Employee emp : database.getAllEmployees()){
            System.out.printf("(%s, %d, %.2f)\n",emp.getName(),emp.getEmpNum(),emp.getSalary());
        }
        System.out.println("DONE\n");

        // Delete Employee
        System.out.println("Deleting some employees");
        database.deleteEmployee(98375);
        database.deleteEmployee(98376);
        System.out.println("DONE\n");
        
        // Show Database
        System.out.println("Printing database");
        for (Employee emp : database.getAllEmployees()){
            System.out.printf("(%s, %d, %.2f)\n",emp.getName(),emp.getEmpNum(),emp.getSalary());
        }
        System.out.println("DONE\n");
    }

    public static void testempregados(){
        // Create Empregados
        System.out.println("Creating empregados");
        Empregado Jose = new Empregado("Jose","Andre",  98374, 1600);
        Empregado Ana  = new Empregado("Ana","Banana",  98375, 1700);
        Empregado Joao = new Empregado("Joao","Beirao", 98376, 1800);
        Empregado Maria = new Empregado("Maria","Sofia",98377, 1900);
        System.out.println("DONE\n");
        
        // Create Registos
        System.out.println("Creating registos");
        Registos registos = new Registos();
        System.out.println("DONE\n");

        // Show Registos
        System.out.println("Printing registos");
        for (Empregado emp : registos.listaDeEmpregados()){
            System.out.printf("(%s %s, %d, %.2f)\n",emp.nome(),emp.apelido(),emp.codigo(), emp.salario());
        }
        System.out.println("DONE\n");

        // Add Empregados
        System.out.println("Adding employees");
        registos.insere(Jose);
        registos.insere(Ana);
        registos.insere(Joao);
        registos.insere(Maria);
        System.out.println("DONE\n");

        // Show Empregados
        System.out.println("Printing registos");
        for (Empregado emp : registos.listaDeEmpregados()){
            System.out.printf("(%s %s, %d, %.2f)\n",emp.nome(),emp.apelido(),emp.codigo(), emp.salario());
        }
        System.out.println("DONE\n");

        // Delete Empregado
        System.out.println("Deleting some employees");
        registos.remove(98375);
        registos.remove(98376);
        System.out.println("DONE\n");

        // Show Empregados
        System.out.println("Printing registos");
        for (Empregado emp : registos.listaDeEmpregados()){
            System.out.printf("(%s %s, %d, %.2f)\n",emp.nome(),emp.apelido(),emp.codigo(), emp.salario());
        }
        System.out.println("DONE\n");
    }

    public static void main(String args[]){
        System.out.println("TEST EMPLOYEES\n");
        testemployees();
        System.out.println("TEST EMPREGADOS\n");
        testempregados();
    }
}