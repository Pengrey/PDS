import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ContactsStorageBinary implements ContactsStorageInterface{

    private File binaryFile;

    public ContactsStorageBinary(File binaryFile){
        this.binaryFile = binaryFile;
    }

    //Reads binary file and returns a list with the contacts read
    public List<Contact> loadContacts() {
        List<Contact> list = new ArrayList<Contact>();
        String strRead = "";
        try (InputStream iStream = new FileInputStream(this.binaryFile);){
            long fileSize = this.binaryFile.length();
            byte[] allBytes = new byte[(int) fileSize];
            iStream.read(allBytes);
            strRead = new String(allBytes, StandardCharsets.UTF_8);
            String[] contactsRead = strRead.split("\t");
        for (String string : contactsRead) {
            String[] tempString = string.split(" ");
            int phone = Integer.parseInt(tempString[tempString.length-1]);
            String name = "";
            for(int i = 0; i < tempString.length-1; i++){
                name += tempString[i] + " ";
            }
            name = name.strip();
            Contact contact = new Contact(name, phone);
            System.out.println(contact);
            list.add(contact);
            iStream.close();
        }
        return list;

        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            return null;
        } catch (IOException e) {
            System.out.println("IO Exception");
            return null;
        }
    }

    //Reads the list and saves the contacts onto a binary file
    public boolean saveContacts(List<Contact> list) {
        try{
            OutputStream oStream = new FileOutputStream(this.binaryFile, false);
            String tempString = "";
            for (Contact contact : list) {
                tempString += contact.getName() + " " + contact.getPhone() + "\t";
            }
            byte[] allBytes = tempString.getBytes(StandardCharsets.UTF_8);
            oStream.write(allBytes);
            oStream.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return false;
    }
    
}
