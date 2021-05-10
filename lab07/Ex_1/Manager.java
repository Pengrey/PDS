class Manager extends WorDecorator{
    Manager(WorkerInterface j) {super(j);}
    @Override public void work(){
        j.work(); System.out.print("Manager ");
    }
    public void manage() {System.out.println("-- Manages");}
}