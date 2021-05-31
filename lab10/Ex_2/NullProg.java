class NullProg extends Employee {
    public NullProg(String name) {
        this.name = name;
    }
    
    @Override
    public String getName() {
        return "The programmer " + this.name + " doesn't exist.";
    }
}