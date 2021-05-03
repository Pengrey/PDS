import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.PrintWriter;

public class ContactsStorageText implements ContactsStorageInterface {

    private File txtFile;

    public ContactsStorageText(File txtFile){
        this.txtFile = txtFile;
    }

    public List<Contact> loadContacts() {
        //Suponho que esta funçao seja para ler o file e devolver a lista de Contactos
        String strRead = "";
        String name = "";
        int phone;
        Contact contact;
        String[] contactsRead, tempString;
        List<Contact> list = new ArrayList<Contact>();
        try {
            Scanner sc = new Scanner(this.txtFile);
            while(sc.hasNextLine()){
                strRead += sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        contactsRead = strRead.split("\t");
        for (String string : contactsRead) {
            tempString = string.split(" ");
            phone = Integer.parseInt(tempString[tempString.length-1]);
            for(int i = 0; i < tempString.length-1; i++){
                name += tempString[i] + " ";
            }
            name = name.strip();
            contact = new Contact(name, phone);
            list.add(contact);
            name = "";
        }
        return list;
    }

    @Override
    public boolean saveContacts(List<Contact> list) {
        try {
            FileWriter fileWriter = new FileWriter(this.txtFile, false);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            for (Contact contact : list) {
                printWriter.print(contact.getName() + " " + contact.getPhone() + "\t");
            }
            printWriter.close();
            fileWriter.close();
            return true;
        } catch (IOException e) {
           return false;
        }
    }
    
}
