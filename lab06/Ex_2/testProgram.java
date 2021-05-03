import java.io.File;

public class testProgram {
    public static void main(String[] args) {
        ContactsStorageInterface store1, store2;
        ContactsImp cImp, cImp2;
        File txtFile = new File("txtFile.txt");
        File binFile = new File("binFile.bin");
        store1 = new ContactsStorageText(txtFile);
        store2 = new ContactsStorageBinary(binFile);
        cImp = new ContactsImp(store1);                         //Initialize cImp with the store with text file
        cImp.openAndLoad(store1);                               //Load contacts from store1
        cImp.getByName("Diogo Matos");
        Contact c1 = new Contact("Antonio Costa", 966996699);
        Contact c2 = new Contact("Teste Testas", 964587954);
        cImp.add(c1);
        cImp.add(c2);
        cImp.remove(c1);
        cImp.saveAndClose();                                    //Save the contacts onto store1 and clear contacts on cImp
        cImp2 = new ContactsImp(store1);                        //Initialize cImp with store1
        cImp2.openAndLoad(store1);                              //Load contacts from store1
        cImp2.getByName("Teste Testas");
        cImp2.saveAndClose(store2);                             //Save contacts on cImp2 onto store2(binary)
        cImp.openAndLoad(store2);                               //Load contacts from store2
        cImp.getByName("Teste Testas");

    }
}
