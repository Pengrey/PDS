class TeamMember extends EmpDecorator{
    TeamMember(EmployeeInterface e) {super(e);}
    @Override public void work(){
        e.work(); System.out.print("TeamMember ");
    }
    public void colaborate() {System.out.println("-- Colaborates");}
}