public class VowelFilter extends BaseDecorator {

    public VowelFilter(ReaderInterface w) {
        super(w);
    }

    public boolean hasNext(){
        return super.hasNext();
    }

    public String next(){
        String notChanged =  super.next();
        String changed = "";
        changed = notChanged.replaceAll("[aeiouAEIOU]", "");
        return changed;
    }
    
}
