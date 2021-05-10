import java.util.Date;

public class WorkTest{
    public static void main(String args[]){
        WorkerInterface w1 = new Employeer(new Worker("Rui"));
        Employeer e1 = new Employeer(new Worker("Luis"));
        TeamLeader tl1 = new TeamLeader(new Worker("Ana"));
        TeamMember tm1 = new TeamMember(w1);
        Manager m1 = new Manager(e1);
        Manager m2 = new Manager(w1);
        Manager m3 = new Manager(
            new TeamLeader(
                new TeamMember(
                    new Employeer(
                        new Worker("Bruna")
                    )
                )
            )
        );
        WorkerInterface lista[] = {w1, e1, tl1, tm1, m1, m2, m3};
        for (WorkerInterface wi: lista){
            wi.start(new Date());
            wi.work();
            System.out.println();
            wi.terminate(new Date());
        }
        System.out.println();
    }
}