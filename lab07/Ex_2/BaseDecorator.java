public class BaseDecorator implements ReaderInterface{

    protected ReaderInterface wrappee;

    public BaseDecorator(ReaderInterface w){
        this.wrappee = w;
    }

    public boolean hasNext() {
        return wrappee.hasNext();
    }

    public String next() {
        return wrappee.next();
    }
    
}
