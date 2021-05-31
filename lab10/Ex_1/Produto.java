public class Produto {
    private int ID;
    private String Descricao;
    private double Preco;
    private int tempo;

    public Produto(String Descricao, Double preco) {
        this.Descricao = Descricao;
        this.Preco = preco;
    }

    public String getDescricao(){
        return this.Descricao;
    }

    public Double getPreco(){
        return this.Preco;
    }

    public void setPreco(Double Preco){
        this.Preco = Preco;
    }

    public void setID(Integer ID){
        this.ID = ID;
    }

    public Integer getID(){
        return this.ID;
    }

    public void setTempo(Integer tempo){
        this.tempo = tempo;
    }

    public Integer getTempo(){
        return this.tempo;
    }
}