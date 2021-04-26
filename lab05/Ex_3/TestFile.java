import java.util.ArrayList;
import java.util.Arrays;

/** 
 * @author Rodrigo Lima
 * @author Goncalo Machado
*/

public class TestFile {
    public static void main  (String args[]){
        Movie myMovie =
          new Movie.Builder("Basic Horror Movie", 1904, new Person("Tino de Ras")).cast(new ArrayList<Person>(Arrays.asList(new Person("Zezoca"), new Person("Filomena")))).genres(Arrays.asList("Horror", "Thriller")).build();
    
        Movie myMovie2 =
          new Movie.Builder("Shrek", 1969, new Person("Best Human Ever")).cast(new ArrayList<Person>(Arrays.asList(new Person("Pessoa1"), new Person("Pessoa3")))).series("Shrek vai a Cidade").build();
    
        Movie myMovie3 =
          new Movie.Builder("Tugas e Fagulhas", 2000, new Person("Bodas")).genres(new ArrayList<String>(Arrays.asList("Comedia", "Familiar"))).locations(new ArrayList<Place>(Arrays.asList(new Place("Ilha da Pascoa"), new Place("Estados Unidos de Portugal")))).build();
    
        Movie myMovie4 =
          new Movie.Builder("E agora?", 2021, new Person("Nilton")).writer(new Person(
            "Ricardo Araujo Pereira")).series("E ____?").cast(new ArrayList<Person>(Arrays.asList(new Person("Herman"), new Person("Fernando Rocha")))).locations(new ArrayList<Place>(Arrays.asList(new Place("Lisboa"), new Place("Porto")))).languages(new ArrayList<String>(Arrays.asList("Portugues", "Portunhol"))).genres(new ArrayList<String>(Arrays.asList("Humor", "Adulto"))).isTelevision(true).isNetflix(true).isIndependent(false).build();
    
    
        System.out.println(myMovie);
        System.out.println(myMovie2);
        System.out.println(myMovie3);
        System.out.println(myMovie4);
    
    
      }
}
