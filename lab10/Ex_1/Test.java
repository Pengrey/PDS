import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        // Produtos
        Produto espada = new Produto("Uma espada", 1000.00);
        Produto vaso = new Produto("Um vaso", 100.00);
        Produto livro = new Produto("Um livro", 30.00);
        Produto carro = new Produto("Um carro", 50000.00);
        Produto pintura = new Produto("Uma pintura", 2000.00);

        // Gestor
        Gestor Lei_Lao = new Gestor("Lei Lao");
        Lei_Lao.addStock(espada);
        Lei_Lao.addStock(vaso);
        Lei_Lao.addStock(livro);
        Lei_Lao.addStock(carro);
        Lei_Lao.addStock(pintura);

        // Clientes
        Cliente rodrigo = new Cliente("Rodrigo");
        Cliente andre = new Cliente("andre");
        Cliente ana = new Cliente("ana");

        // Adicionar leiloes
        Lei_Lao.addLeilao(espada, 10);
        Lei_Lao.addLeilao(pintura, 5);

        // Client pede listagem
        Lei_Lao.listLeilao();
        Lei_Lao.licita(espada, rodrigo, 100.00);
        Lei_Lao.licita(espada, rodrigo, 1001.00);
        Lei_Lao.licita(espada, andre, 2001.00);
        Lei_Lao.licita(pintura, ana, 3000.00);
        TimeUnit time = TimeUnit.SECONDS;
        try {
            time.sleep(5L);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while Sleeping");
        }
        Lei_Lao.licita(pintura, rodrigo, 4000.00);
        Lei_Lao.licita(pintura, andre, 2001.00);
    }
}