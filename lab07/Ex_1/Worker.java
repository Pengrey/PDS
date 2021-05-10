import java.util.Date;

class Worker implements WorkerInterface{
    private String name;

    Worker(String n) {name = n;}

    @Override public void start(Date d){
        System.out.println("Worker " + this.name + " started working at: " + d.toString());
    }

    @Override public void terminate(Date d){
        System.out.println("Worker " + this.name + " terminated working at: " + d.toString() + "\n");
    }

    @Override public void work(){
        System.out.print(name + " working as ");
    }

}