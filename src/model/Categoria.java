package model;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-24-2017)
 */
public class Categoria {

    private int id;
    private String nome, descricao;
    private boolean ativo;

    public Categoria(){
        
    }

    public Categoria(String nome, String descricao, boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return nome;
    }

}
