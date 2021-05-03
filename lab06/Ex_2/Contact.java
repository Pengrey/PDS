public class Contact {
    private String name;
    private int phone;

    public Contact(String name, int phone){
        this.name = name;
        this.phone = phone;
    }

    public String getName(){
        return this.name;
    }

    public int getPhone(){
        return this.phone;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhone(int phone){
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact of " + this.name + " with number " + this.phone + ".";
    }

    public boolean equalsContact(Contact contact) {
        return getPhone() == contact.getPhone() && java.util.Objects.equals(getName(), contact.getName());
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), getName(), getPhone());
    }
}
