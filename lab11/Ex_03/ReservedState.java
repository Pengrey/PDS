public class ReservedState extends State{

    public ReservedState(Book bo) {
        super(bo);
        this.name = "Reservado";
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
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void reserveBook() {
        System.out.println("Operação não disponivel");        
    }

    @Override
    public void cancelReservedBook() {
        book.setState(new AvailableState(book));
    }
    
}
