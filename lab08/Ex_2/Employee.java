class Employee {
    private double salary;
    private Person person;
    private Card card;
    
    public Employee(Person person, double s) {
        this.person = person;
        salary = s;
    }
    
    public double getSalary() {
        return salary;
    }

    public Person getPerson() {
        return this.person;
    }

    public void setCard(Card card){
        this.card = card;
    }

    public Card getCard() {
        return this.card;
    }
}