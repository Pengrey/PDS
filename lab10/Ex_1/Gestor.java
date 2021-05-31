import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gestor {
    private String Nome;
    private int ID;
    Map<Produto, List<Cliente>> leiloes = new HashMap<>();
    ArrayList<Produto> stock = new ArrayList<>();
    ArrayList<Produto> vendas = new ArrayList<>();

    public Gestor(String Nome) {
        this.Nome = Nome;
        this.ID = 0;
    }

    public void addStock(Produto produto){
        produto.setID(this.getID());
        this.stock.add(produto);
    }

    public void backToStock(Produto produto){
        this.stock.add(produto);
    }

    public void sold(Produto produto){
        this.vendas.add(produto);
    }

    public void addLeilao(Produto produto, Integer tempo){
        produto.setTempo((int) ((System.currentTimeMillis()/1000) + tempo));
        this.leiloes.put(produto, new ArrayList<>());
        this.stock.remove(produto);
    }

    public void listLeilao(){
        System.out.println("Items:");
        for (Produto item : this.leiloes.keySet())
            System.out.println(item.getDescricao());
    }

    public void licita(Produto produto, Cliente cliente, Double preco) {
        List<Cliente> licitadores = leiloes.get(produto);
        if(!leilaoEnded(produto)){
            if(!licitadores.contains(cliente))
                licitadores.add(cliente);

            if(produto.getPreco() < preco){
                produto.setPreco(preco);
                notify(licitadores, produto, preco, false);
            }else{
                System.out.println("\nCaro licitador " + cliente.getNome() + ",\no preco de licitação tem que ser maior que o preco atual.");
            }
        }else{
            if(leiloes.containsKey(produto)){
                notify(licitadores, produto, preco, true);
                leiloes.remove(produto);
                if(licitadores.isEmpty()){
                    sold(produto);
                }else{
                    backToStock(produto);
                }
                System.out.println("\nCaro licitador " + cliente.getNome() + ",\no leilao acabou.");
            }else{
                System.out.println("\nCaro licitador " + cliente.getNome() + ",\no leilao acabou.");
            }
        }
    }

    public void notify(List<Cliente> licitadores, Produto produto, Double preco, Boolean ended) {
        for (Cliente licitador : licitadores)
            if(!ended){
                System.out.println("\nCaro licitador " + licitador.getNome() + ",\no preco atual do produto " + produto.getDescricao() + " é " + preco);
            }else{
                System.out.println("\nCaro licitador " + licitador.getNome() + ",\no item " + produto.getDescricao() + " foi vendido por " + preco);
            }   
    }

    public boolean leilaoEnded(Produto produto){
        return (int) (System.currentTimeMillis()/1000) >= produto.getTempo() ? true : false;
    }

    public String getNome(){
        return this.Nome;
    }

    public Integer getID(){
        this.ID++;
        return this.ID - 1;
    }
}