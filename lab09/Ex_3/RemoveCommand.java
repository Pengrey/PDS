import java.util.Collection;

public class RemoveCommand<T> extends Command {

    public RemoveCommand(Collection col) {
        super(col);
    }

    public boolean execute(T element){
        boolean executed = this.col.remove(element); //Tries to add the element to the collection
        
        if(executed) //If it managed to do so, it pushes the element to the top of the elements stack
            this.history.push(element);

        return executed;
    }

    @Override
    public boolean undo() {
        boolean executed = this.col.add(this.history.peek()); //Tries to remove the element from the collection
        if(executed)
            this.history.pop(); //If it managed to do so, it pops the element out of the elements stack

        return executed;
    }
    
}