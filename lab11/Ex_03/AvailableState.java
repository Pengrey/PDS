public class AvailableState extends State {

    public AvailableState(Book bo) {
        super(bo);
        this.name = "Disponivel";
    }

    @Override
    public void registerBook() {
        System.out.println("Operação não disponivel");
    }

    @Override
    public void requestBook() {
        book.setState(new BorrowedState(book));
    }

    @Override
    public void returnBook() {
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void reserveBook() {
        book.setState(new ReservedState(book));     
    }

    @Override
    public void cancelReservedBook() {
        System.out.println("Operação não disponivel");
    }    
}
