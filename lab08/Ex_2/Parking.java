import java.util.ArrayList;

class Parking{
    private static ArrayList<Person> allowed_records = new ArrayList<>();

    public static boolean allow(Person p){
        if(allowed_records.contains(p)){
            return false;
        }else{
            allowed_records.add(p);
            return true;
        }
    }

    public static boolean dontAllow(Person p){
        if(allowed_records.contains(p)){
            allowed_records.remove(p);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Person> getRecords(){
        return allowed_records;
    }
}