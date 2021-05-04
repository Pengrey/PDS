import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ContactsStorageBinary implements ContactsStorageInterface{

    private File binaryFile;

    public ContactsStorageBinary(File binaryFile){
        this.binaryFile = binaryFile;
    }

    //Reads binary file and returns a list with the contacts read
    public List<Contact> loadContacts() {
        List<Contact> list = new ArrayList<Contact>();
        String strRead = "";
        try {
            Scanner sc = new Scanner(this.binaryFile);
            while(sc.hasNextLine()){
                strRead += sc.nextLine();
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        strRead = binaryToString(strRead);
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
        }
        return list;
    }

    //Reads the list and saves the contacts onto a binary file
    public boolean saveContacts(List<Contact> list) {
        try{
            FileWriter fWriter = new FileWriter(this.binaryFile, false);
            PrintWriter pWriter = new PrintWriter(fWriter);
            String tempString = "";
            for (Contact contact : list) {
                tempString += contact.getName() + " " + contact.getPhone() + "\t";
            }
            byte[] allBytes = tempString.getBytes(StandardCharsets.UTF_8);
            String binary = convertByteArraysToBinary(allBytes);
            binary = prettyBinary(binary, 8, " ");
            pWriter.print(binary);
            pWriter.close();
            fWriter.close();
        }catch(FileNotFoundException e){
            System.out.println("File Not Found");
            return false;
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return false;
    }

    //Converts byte array to binary string
    public static String convertByteArraysToBinary(byte[] input) {

        StringBuilder result = new StringBuilder();
        for (byte b : input) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                result.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return result.toString();

    }

    //formats binary string
    public static String prettyBinary(String binary, int blockSize, String separator) {

        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < binary.length()) {
            result.add(binary.substring(index, Math.min(index + blockSize, binary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }

    //Converts binary string to actual text string
    public static String binaryToString(String bin) {

        String raw = Arrays.stream(bin.split(" "))
                .map(binary -> Integer.parseInt(binary, 2))
                .map(Character::toString)
                .collect(Collectors.joining()); // cut the space


        return raw;
    }
    
}
