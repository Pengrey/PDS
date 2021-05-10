class Manager extends EmpDecorator{
    Manager(EmployeeInterface e) {super(e);}
    @Override public void work(){
        e.work(); System.out.print("Manager ");
    }
    public void manage() {System.out.println("-- Manages");}
}