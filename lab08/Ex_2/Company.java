import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Company {
    public static User user;
    private List<Employee> emps = new ArrayList<>();

    public void admitEmployee(Person person, double salary) {
        Employee e = new Employee(person, salary);

        // Registers employee in Employees database
        emps.add(e);

        // Registers employee in SocialSecurity registers
        if(SocialSecurity.regist(person)){
            System.out.println(person.getName() + " registered successfully in the SocialSecurity database.");
        }else{
            System.out.println(person.getName() + " wasn't registered in the SocialSecurity database..");
        }

        // Registers employee in Insurance registers
        if(Insurance.regist(person)){
            System.out.println(person.getName() + " registered successfully in the Insurance database.");
        }else{
            System.out.println(person.getName() + " wasn't registered in the Insurance database.");
        }

        // Get average of salaries
        double avg = 0;
        for (Employee emp : emps){
            avg += emp.getSalary();
        }
        avg /= emps.size();

        // Athorizes parking and registers if eligeble
        if(e.getSalary() > avg){
            if(Parking.allow(person)){
                System.out.println(person.getName() + " registered successfully in the allowed parking list.");
            }else{
                System.out.println(person.getName() + " wasn't registered successfully in the allowed parking list.");
            }
        }else{
            System.out.println(person.getName() + " not eligeble for parking.");
        }

        // Creates card for employee
        e.setCard(new Card(person.getName()));
        System.out.println("Atributed card:\n" + e.getCard().toString() + "\n");
    }
    
    public void paySalaries(int month) {
        for(Employee e: emps) {
            BankAccount ba = e.getPerson().getBankAccount();
            ba.deposit(e.getSalary());
        }
    }
    
    public List<Employee> employees() {
        return Collections.unmodifiableList(emps);
    }
}