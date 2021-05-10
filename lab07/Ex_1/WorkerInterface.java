import java.util.Date;

interface WorkerInterface{
    void start(Date d);
    void terminate(Date d);
    void work();
}