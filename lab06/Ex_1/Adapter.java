// Adapter
class Adapter {
    private Database database;
    private Registos registos;

    // Adapter constructor
    public Adapter(Database database, Registos registos){
        this.database = database;
        this.registos = registos;
    }

    // Saves employee on the databases
    public void save(Employee person){
        database.addEmployee(person);
    }

    // Removes employee from databases
    public void remove(Object emp_num){
        if (emp_num instanceof Integer){
            registos.remove((int) emp_num);
        }
        if (emp_num instanceof Long){
            database.deleteEmployee((long) emp_num);
        }
    }

    // Checks if the employee is in our database's
    public boolean belongs(Object emp_num){
        if (emp_num instanceof Integer){
            return registos.isEmpregado((int) emp_num);
        }
        if (emp_num instanceof Long){
            Employee[] employees = database.getAllEmployees(); 

            for (int i = 0; i < employees.length; i++)
                if((long) emp_num == employees[i].getEmpNum()) return true;
            return false;
        }
        return false;
    }

    // Prints our databases 
    public void show(){
        // Print Employees
        for (Employee emp : database.getAllEmployees()){
            System.out.printf("(%s, %d, %.2f)\n",emp.getName(),emp.getEmpNum(),emp.getSalary());
        }

        // Print registos
        for (Empregado emp : registos.listaDeEmpregados()){
            System.out.printf("(%s %s, %d, %.2f)\n",emp.nome(),emp.apelido(),emp.codigo(), emp.salario());
        }
    }
}