import java.util.Date;

abstract class EmpDecorator implements EmployeeInterface{
    protected EmployeeInterface e;
    EmpDecorator(EmployeeInterface j) {this.e = j;}
    public void work() {e.work();}
    public void start(Date d){ e.start(d);};
    public void terminate(Date d){e.terminate(d);};
}