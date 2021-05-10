public class testFile {
    public static void main(String[] args) {
        ReaderInterface main = new TextReader("testTxt.txt");
        TermFilter tFilter = new TermFilter(main);
        NormalizationFilter nFilter= new NormalizationFilter(main);
        CapitalizationFilter cFilter = new CapitalizationFilter(new TermFilter(main));
        VowelFilter vFilter = new VowelFilter(new NormalizationFilter(new CapitalizationFilter(main)));

        ReaderInterface[] allReader = {main,tFilter,nFilter,cFilter,vFilter};
        for (ReaderInterface r : allReader) {
           while(r.hasNext()){
               System.out.println(r.next());
           }

           System.out.println("----------Changed reader------------");
        }

    }
}
