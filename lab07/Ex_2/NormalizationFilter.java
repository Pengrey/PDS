import java.text.Normalizer;

public class NormalizationFilter extends BaseDecorator {

    public NormalizationFilter(ReaderInterface w) {
        super(w);
    }
    
    public boolean hasNext(){
        return super.hasNext();
    }

    public String next(){
        String notChanged = super.next();
        String changed = "";
        notChanged = Normalizer.normalize(notChanged, Normalizer.Form.NFD);
        changed = notChanged.replaceAll("[^\\p{ASCII}]", "");
        return changed;
    }
}
