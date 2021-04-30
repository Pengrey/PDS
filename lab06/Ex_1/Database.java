import java.util.Vector;

class Database {    // Data elements
    private Vector<Employee> employees;     // Stores the employees

    public Database() {
        employees = new Vector<>();
    }

    public boolean addEmployee(Employee employee) {
        // Code to add employee
        for (int i = 0; i < employees.size(); i++)
            if(employee == employees.get(i)) return false;
        employees.add(employee);
        return true;
    }

    public void deleteEmployee(long emp_num) {
        // Code to delete employee
        for (int i = 0; i < employees.size(); i++)
            if( employees.get(i).getEmpNum() ==emp_num) employees.remove(employees.get(i));
    }
    
    public Employee[]getAllEmployees() {
        // Code to retrieve collection
        Employee allEmployees[] = new Employee[employees.size()];
        for (int i = 0; i < employees.size(); i++)
            allEmployees[i] = employees.get(i);
        return allEmployees;
    }
}