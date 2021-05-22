import java.util.Stack;
import java.util.Collection;

public abstract class Command<T> {
    protected Collection<T> col;
    protected Stack<T> history;

    public Command(Collection<T> col){
        this.col = col;
        this.history = new Stack<T>();
    }

    abstract public boolean undo();
    
}
