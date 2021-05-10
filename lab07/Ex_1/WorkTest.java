import java.util.Date;

public class WorkTest{
    public static void main(String args[]){
        EmployeeInterface w1 = new Employee("Rui");
        Employee e1 = new Employee("Luis");
        TeamLeader tl1 = new TeamLeader(new Employee("Ana"));
        TeamMember tm1 = new TeamMember(w1);
        Manager m1 = new Manager(e1);
        Manager m2 = new Manager(w1);
        Manager m3 = new Manager(
            new TeamLeader(
                new TeamMember(
                    new Employee("Bruna")
                )
            )
        );
        EmployeeInterface lista[] = {w1, e1, tl1, tm1, m1, m2, m3};
        for (EmployeeInterface wi: lista){
            wi.start(new Date());
            wi.work();
            System.out.println();
            wi.terminate(new Date());
        }
        System.out.println();
    }
}