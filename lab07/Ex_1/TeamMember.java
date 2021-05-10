class TeamMember extends WorDecorator{
    TeamMember(WorkerInterface j) {super(j);}
    @Override public void work(){
        j.work(); System.out.print("TeamMember ");
    }
    public void colaborate() {System.out.println("-- Colaborates");}
}