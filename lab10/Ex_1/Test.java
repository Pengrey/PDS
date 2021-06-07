import java.util.Scanner;
//import java.util.concurrent.*;

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
        Cliente andre = new Cliente("Andre");
        Cliente ana = new Cliente("Ana");

        // Adicionar leiloes
        Lei_Lao.addLeilao(espada, 30);
        Lei_Lao.addLeilao(pintura, 40);

        int opt = 3;
        Cliente user = rodrigo;
        Scanner sc = new Scanner(System.in);
        boolean badName = true;
        String name;
        while(badName){
            System.out.println("Qual a pessoa que esta a usar este menu?");
            name = sc.next();
            if(name.toLowerCase().equals("rodrigo")){
                user = rodrigo;
                badName = false;
            }else if(name.toLowerCase().equals("andre")){
                user = andre;
                badName = false;
            }else if(name.toLowerCase().equals("ana")){
                user = ana;
                badName = false;
            }
        }
        while(opt != 4){
            System.out.println("Escolha a opçao:\n    1 - Lista de objetos do leilao\n    2 - Licitar num objeto\n    3 - Trocar de User\n    4 - Terminar");
            opt = sc.nextInt();
            switch(opt){
                case 1:
                    Lei_Lao.listLeilao();
                    break;
                case 2:
                    System.out.println("Escolha o objeto a licitar:\n    1 - Espada\n    2 - Pintura\n");
                    int objeto = sc.nextInt();
                    System.out.println("Quanto quer licitar?");
                    double licitacao = sc.nextDouble();
                    if(objeto == 1){
                        Lei_Lao.licita(espada, user, licitacao);
                    }else if(objeto == 2){
                        Lei_Lao.licita(pintura, user, licitacao);
                    }
                    break;
                case 3:
                    System.out.println("Qual a pessoa que esta a usar este menu?");
                    sc = new Scanner(System.in);
                    name = sc.next();
                    if(name.toLowerCase().equals("rodrigo")){
                        user = rodrigo;
                    }else if(name.toLowerCase().equals("andre")){
                        user = andre;
                    }else if(name.toLowerCase().equals("ana")){
                        user = ana;
                    }
                case 4:
                    break;
            }
        }
        sc.close();

        // Client pede listagem
        /*Lei_Lao.listLeilao();
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
        Lei_Lao.licita(pintura, andre, 2001.00);*/
    }
}