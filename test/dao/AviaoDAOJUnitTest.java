package dao;

import database.Conexao;
import model.Aviao;
import model.Categoria;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-25-2017)
 */
public class AviaoDAOJUnitTest {
    
    @Test
    public void inserir(){
        Conexao.truncate();
        new CategoriaDAO().inserir(new Categoria("Baixa", "A", true));
        
        Aviao a = new Aviao();
        a.setNome("A");
        a.setCategoria(new CategoriaDAO().getById(1));
        
        int codigo = new AviaoDAO().add(a);
        a.setId(codigo);
        
        assertEquals(codigo, 1);
        validateEquals(a, new AviaoDAO().getById(codigo));
    }
    
    @Test
    public void alterar(){
        Conexao.truncate();
        new CategoriaDAO().inserir(new Categoria("Baixa", "A", true));
        new CategoriaDAO().inserir(new Categoria("Media", "A", true));
        Aviao a = new Aviao();
        a.setNome("A");
        a.setCategoria(new CategoriaDAO().getById(1));
        
        int codigo = new AviaoDAO().add(a);
        
        a.setId(codigo);
        a.setNome("B");
        a.setCategoria(new CategoriaDAO().getById(2));
        new AviaoDAO().edit(a);
        
        Aviao dps = new AviaoDAO().getById(codigo);
        
        validateEquals(a, dps);
    }
    
    @Test
    public void excluir(){
        
    }
    
    public void validateEquals(Aviao a1, Aviao a2){
        assertEquals(a1.getId(), a2.getId());
        assertEquals(a1.getNome(), a2.getNome());
        assertEquals(a1.getCategoria().getId(), a2.getCategoria().getId());
        assertEquals(a1.getCategoria().getNome(), a2.getCategoria().getNome());
        assertEquals(a1.getCategoria().getDescricao(), a2.getCategoria().getDescricao());
        assertEquals(a1.getCategoria().isAtivo(), a2.getCategoria().isAtivo());
    }
    
}
