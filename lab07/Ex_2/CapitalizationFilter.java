public class CapitalizationFilter extends BaseDecorator {

    public CapitalizationFilter(ReaderInterface w) {
        super(w);
    }

    public boolean hasNext(){
        return super.hasNext();
    }

    public String next(){
        String notChanged = super.next();
        String changed = "";
        if(notChanged.length() > 2){
            changed = notChanged.substring(0, 1).toUpperCase() + notChanged.substring(1, notChanged.length()-1) + notChanged.substring(notChanged.length()-1).toUpperCase();
        }else{
            changed = notChanged.toUpperCase();
        }
        return changed;
    }
    
}
