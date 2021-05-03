import java.util.List;

public class ContactsImp implements ContactsInterface {
    List<Contact> contacts;

    public void openAndLoad(ContactsStorageInterface store) {
        this.contacts.addAll(store.loadContacts());
    }

    public void saveAndClose() {
        //maybe tenho que fazer saveContacts, depois ir buscar o file
    }

    public void saveAndClose(ContactsStorageInterface store) {

    }

    public boolean exist(Contact contact) {
        for (Contact con : contacts) {
            if(contact == con){
                return true;
            }
        }
        return false;
    }

    public Contact getByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)){
                return contact;
            }
        }
        System.out.println("No contact with name: " + name + " has been found");
        return null;
    }

    public boolean add(Contact contact) {
        if (exist(contact) && contact != null){
            contacts.add(contact);
            System.out.println("Contact added");
            return true;
        }
        System.out.println("Contact could not be added");
        return false;
    }

    public boolean remove(Contact contact) {
        if(exist(contact)){
            System.out.println("Contact could not be removed");
            return false;
        }
        contacts.remove(contact);
        System.out.println("Contact removed");
        return true;
    }
    
}
