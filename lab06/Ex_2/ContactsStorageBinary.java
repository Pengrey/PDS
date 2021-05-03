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

    public List<Contact> loadContacts() {
        //Suponho que esta funçao seja para ler o file e devolver a lista de Contactos
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
            Contact contact = new Contact(name, phone);
            list.add(contact);
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

    public boolean saveContacts(List<Contact> list) {
        //Tenho que perguntar se isto grava por cima ou adiciona ao file existente
        try{
            File temp = new File(this.binaryFile.getName());
            OutputStream oStream = new FileOutputStream(temp);
            String tempString = "";
            for (Contact contact : list) {
                tempString += contact.getName() + " " + contact.getPhone() + "\t";
            }
            byte[] allBytes = tempString.getBytes(StandardCharsets.UTF_8);
            oStream.write(allBytes);
            oStream.close();
            this.binaryFile = temp;
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return false;
    }
    
}
