class TeamLeader extends EmpDecorator{
    TeamLeader(EmployeeInterface e) {super(e);}
    @Override public void work(){
        e.work(); System.out.print("TeamLeader ");
    }
    public void plan() {System.out.println("-- Plans");}
}