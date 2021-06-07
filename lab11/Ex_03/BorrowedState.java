public class BorrowedState extends State{

    public BorrowedState(Book bo) {
        super(bo);
        this.name = "Emprestado";
    }

    @Override
    public void registerBook() {
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void requestBook() {
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void returnBook() {
        book.setState(new AvailableState(book));
    }

    @Override
    public void reserveBook() {
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void cancelReservedBook() {
        System.out.println("Operação não disponivel");        
    }
    
}
