import java.util.ArrayList;
import java.util.List;

public class ContactsImp implements ContactsInterface {
    List<Contact> contacts;
    ContactsStorageInterface cSI;

    public ContactsImp(){
        this.contacts = new ArrayList<Contact>();
    }

    public ContactsImp(ContactsStorageInterface store){
        this.cSI = store;
        this.contacts = new ArrayList<Contact>();
    }

    //Load all the contacts on the ContactsStorageInterface and add them to contacts
    public void openAndLoad(ContactsStorageInterface store) {
        this.contacts.addAll(store.loadContacts());
    }

    //Save all contacts to the ContactsStorageInterface and clear all the contacts
    public void saveAndClose() {
        this.cSI.saveContacts(contacts);
        this.contacts.clear();
    }

    //Save all contacts to the ContactsStorageInterface and clear all the contacts
    public void saveAndClose(ContactsStorageInterface store) {
        store.saveContacts(contacts);
        this.contacts.clear();
    }

    //Checks if a contact exists in contacts
    public boolean exist(Contact contact) {
        for (Contact con : contacts) {
            if(contact.equalsContact(con)){
                return true;
            }
        }
        return false;
    }

    //Search contacts for the contact with a specific name
    public Contact getByName(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equals(name)){
                System.out.println("Found " + contact.toString());
                return contact;
            }
        }
        System.out.println("No contact with name: " + name + " has been found");
        return null;
    }

    //Add contact to contacts if it is not already there
    public boolean add(Contact contact) {
        System.out.println("Contact: " + contact + " exists: " + exist(contact));
        if (!exist(contact) && contact != null){
            contacts.add(contact);
            System.out.println("Contact " + contact.toString() + " added");
            return true;
        }
        System.out.println("Contact could not be added");
        return false;
    }

    //Remove contact if it exists in contacts
    public boolean remove(Contact contact) {
        if(!exist(contact)){
            System.out.println("Contact could not be removed");
            return false;
        }
        contacts.remove(contact);
        System.out.println("Contact " + contact.toString() + " removed");
        return true;
    }
    
}
