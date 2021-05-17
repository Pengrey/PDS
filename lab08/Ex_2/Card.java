class Card{
    private String emp_Name;
    private int emp_ID;
    private static int IDgen = 0;

    Card(String name){
        this.emp_Name = name;
        this.emp_ID = IDgen;
        IDgen++;
    }

    public String toString(){
        return "Name: " + this.emp_Name + " ID: " + this.emp_ID;
    }
}