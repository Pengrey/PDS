class Employeer extends WorDecorator{
    Employeer(WorkerInterface j) {super(j);}
    @Override public void work(){
        j.work(); System.out.print("Employer ");
    }
}