public class Book {
    State state;
    String title;
    String isbn;
    String year;
    String author;

    public Book(String t, String i, String y, String a){
        this.title = t;
        this.isbn = i;
        this.year = y;
        this.author = a;
        this.state = new InventoryState(this);
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }
    
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    @Override
    public String toString() {
        return String.format("%-20s %15s [%s]", this.title, this.author, this.state.getName());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void registerBook(){
        state.registerBook();
    }

    public void requestBook(){
        state.requestBook();
    }

    public void returnBook(){
        state.returnBook();
    }

    public void reserveBook(){
        state.reserveBook();
    }

    public void cancelReservedBook(){
        state.cancelReservedBook();
    }

}
