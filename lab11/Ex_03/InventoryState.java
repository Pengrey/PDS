public class InventoryState extends State{

    public InventoryState(Book bo) {
        super(bo);
        this.name = "Inventário";
    }

    @Override
    public void registerBook() {
        book.setState( new AvailableState(book));
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
        System.out.println("Operação não disponivel");
    }
    
}
