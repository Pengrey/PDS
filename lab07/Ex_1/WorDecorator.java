import java.util.Date;

abstract class WorDecorator implements WorkerInterface{
    protected WorkerInterface j;
    WorDecorator(WorkerInterface j) {this.j = j;}
    public void work() {j.work();}
    public void start(Date d){ j.start(d);};
    public void terminate(Date d){j.terminate(d);};
}