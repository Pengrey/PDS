class TeamLeader extends WorDecorator{
    TeamLeader(WorkerInterface j) {super(j);}
    @Override public void work(){
        j.work(); System.out.print("TeamLeader ");
    }
    public void plan() {System.out.println("-- Plans");}
}