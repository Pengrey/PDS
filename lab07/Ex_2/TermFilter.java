import java.util.ArrayList;

public class TermFilter extends BaseDecorator {

    protected ArrayList<String> paragraph;

    public TermFilter(ReaderInterface w) {
        super(w);
        this.paragraph = new ArrayList<String>();
    }

    public boolean hasNext(){
        if(paragraph.size() == 0){
            return super.hasNext();
        }
        return true;
    }

    public String next(){
        if(paragraph.size() == 0){
            if(hasNext()){
                String para = super.next();
                String[] splittedParagraph = para.split("\\s+");
                for (int i = 0; i < splittedParagraph.length; i++){
                    paragraph.add(splittedParagraph[i]);
                }
            }
        }
        return paragraph.remove(0);
    }
    
}
