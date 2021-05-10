import java.util.Date;

class Employee implements EmployeeInterface{
    private String name;

    Employee(String n) {name = n;}

    @Override public void start(Date d){
        System.out.println("Employee " + this.name + " started working at: " + d.toString());
    }

    @Override public void terminate(Date d){
        System.out.println("EMployee " + this.name + " terminated working at: " + d.toString() + "\n");
    }

    @Override public void work(){
        System.out.print(name + " working as Employee ");
    }

}