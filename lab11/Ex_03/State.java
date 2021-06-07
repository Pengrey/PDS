public abstract class State {

    protected Book book;
    protected String name;

    public State(Book bo){
        this.book = bo;
    }

    public String getName(){
        return this.name;
    }
    public abstract void registerBook();
    public abstract void requestBook();
    public abstract void returnBook();
    public abstract void reserveBook();
    public abstract void cancelReservedBook();
}
