import java.util.Collection;

public class AddCommand<T> extends Command{

    public AddCommand(Collection<T> col) {
        super(col);
    }

    public boolean execute(T elementT) {
        boolean executed = this.col.add(elementT);

        if (executed){
            this.history.push(elementT);
        }
        return executed;
    }

    @Override
    public boolean undo() {
        boolean executed = this.col.remove(this.history.peek()); //Tries to remove the element from the collection
        if(executed)
            this.history.pop(); //If it managed to do so, it pops the element out of the elements stack

        return executed;
    }
    
}
