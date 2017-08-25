package dao;

import database.Conexao;
import model.Categoria;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-25-2017)
 */
public class CategoriaDAOJUnitTest {

    @Test
    public void inserir() {
        Conexao.truncate();

        Categoria c = new Categoria();
        c.setNome("Media");
        c.setDescricao("Fon");
        c.setAtivo(false);

        int codigo = new CategoriaDAO().inserir(c);
        assertEquals(1, codigo);
    }

    @Test
    public void buscarPorId() {
        Conexao.truncate();

        Categoria c = new Categoria();
        c.setNome("Media");
        c.setDescricao("Fon");
        c.setAtivo(false);

        int codigo = new CategoriaDAO().inserir(c);
        c.setId(codigo);

        Categoria achado = new CategoriaDAO().getById(codigo);

        assertNotNull(achado);
        assertEquals(achado.getId(), c.getId());
        assertEquals(achado.getNome(), c.getNome());
        assertEquals(achado.getDescricao(), c.getDescricao());
        assertEquals(achado.isAtivo(), c.isAtivo());
    }

    @Test
    public void alterar() {
        Conexao.truncate();

        Categoria c = new Categoria();
        c.setNome("Media");
        c.setDescricao("Fon");
        c.setAtivo(false);

        int codigo = new CategoriaDAO().inserir(c);
        c.setId(codigo);

        c.setNome("Alta");
        c.setDescricao("Meu txt");
        c.setAtivo(true);
        new CategoriaDAO().alterar(c);

        Categoria dps = new CategoriaDAO().getById(codigo);
        validarEquals(c, dps);
    }

    @Test
    public void excluir() {
        Conexao.truncate();

        Categoria c = new Categoria();
        c.setNome("Media");
        c.setDescricao("Fon");
        c.setAtivo(false);

        int codigo = new CategoriaDAO().inserir(c);

        new CategoriaDAO().excluir(codigo);
        assertNull(new CategoriaDAO().getById(codigo));
    }

    public void validarEquals(Categoria c1, Categoria c2) {
        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getNome(), c2.getNome());
        assertEquals(c1.getDescricao(), c2.getDescricao());
        assertEquals(c1.isAtivo(), c2.isAtivo());
    }

}
