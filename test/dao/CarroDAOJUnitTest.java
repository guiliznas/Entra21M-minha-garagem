package dao;

import java.sql.Date;
import model.Carro;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-14-2017)
 */
public class CarroDAOJUnitTest {

    @Test
    public void inserir() {
        Carro c = new Carro();
        c.setAnoFabricacao((short) 2017);
        c.setAnoLancamento((short) 2017);
        c.setChassi("51516516");
        c.setCor("Vermelho");
        c.setDataCompra(new Date(2017, 8, 14));
        c.setDescricao("sadasdasdaerwefwrevarv rebaerba");
        c.setEstaFuncional(false);
        c.setFabricante("VW");
        c.setNome("Gol");
        c.setPlaca("FLA-2017");
        c.setPotencia(100);
        c.setPermitidaCirculacao(true);
        c.setQtdBatidas((byte) 0);
        c.setQtdPortas((byte) 4);
        c.setQuilometragem(11651.6f);
        c.setTipoPneu((short) 17);
        c.setRenavam(198949);

        CarroDAO dao = new CarroDAO();
        dao.truncate();
        int codigoInserir = dao.inserir(c);
        c.setId(codigoInserir);
        assertEquals(codigoInserir, 1);

        Carro c2 = dao.buscarPorId(codigoInserir);
        validarIgualCarro(c, c2);
    }

    @Test
    public void excluir() {
        Carro c = new Carro();
        c.setAnoFabricacao((short) 2017);
        c.setAnoLancamento((short) 2017);
        c.setChassi("51516516");
        c.setCor("Vermelho");
        c.setDataCompra(new Date(2017, 8, 14));
        c.setDescricao("sadasdasdaerwefwrevarv rebaerba");
        c.setEstaFuncional(false);
        c.setFabricante("VW");
        c.setNome("Gol");
        c.setPlaca("FLA-2017");
        c.setPotencia(100);
        c.setPermitidaCirculacao(true);
        c.setQtdBatidas((byte) 0);
        c.setQtdPortas((byte) 4);
        c.setQuilometragem(11651.6f);
        c.setTipoPneu((short) 17);
        c.setRenavam(198949);

        CarroDAO dao = new CarroDAO();
        dao.truncate();
        int codigo = dao.inserir(c);
        dao.excluir(codigo);

        Carro carro = dao.buscarPorId(codigo);
        assertNull(carro);
    }

    @Test
    public void alterar() {
        Carro c = new Carro();
        c.setAnoFabricacao((short) 2017);
        c.setAnoLancamento((short) 2017);
        c.setChassi("51516516");
        c.setCor("Vermelho");
        c.setDataCompra(new Date(2017, 8, 14));
        c.setDescricao("sadasdasdaerwefwrevarv rebaerba");
        c.setEstaFuncional(false);
        c.setFabricante("VW");
        c.setNome("Gol");
        c.setPlaca("FLA-2017");
        c.setPotencia(100);
        c.setPermitidaCirculacao(true);
        c.setQtdBatidas((byte) 0);
        c.setQtdPortas((byte) 4);
        c.setQuilometragem(11651.6f);
        c.setTipoPneu((short) 17);
        c.setRenavam(198949);

        CarroDAO dao = new CarroDAO();
        dao.truncate();
        int codigo = dao.inserir(c);
        c.setId(codigo);
        c.setAnoFabricacao((short) 2015);
        c.setAnoLancamento((short) 2015);
        c.setChassi("5151231211");
        c.setCor("Vbbbbb");
        c.setDataCompra(new Date(2017, 8, 20));
        c.setDescricao("rebaerba");
        c.setEstaFuncional(true);
        c.setFabricante("FG");
        c.setNome("aasasasad");
        c.setPlaca("DOI-DMSL");
        c.setPotencia(200);
        c.setPermitidaCirculacao(false);
        c.setQtdBatidas((byte) 2);
        c.setQtdPortas((byte) 2);
        c.setQuilometragem(1.6f);
        c.setTipoPneu((short) 10);
        c.setRenavam(832);

        dao.alterar(c);
        
        Carro dps = dao.buscarPorId(codigo);
        
        validarIgualCarro(c, dps);
    }

    private void validarIgualCarro(Carro c1, Carro c2) {
        assertEquals(c1.getId(), c2.getId());
        assertEquals(c1.getAnoFabricacao(), c2.getAnoFabricacao());
        assertEquals(c1.getAnoLancamento(), c2.getAnoLancamento());
        assertEquals(c1.getChassi(), c2.getChassi());
        assertEquals(c1.getCor(), c2.getCor());
        assertEquals(c1.getDataCompra(), c2.getDataCompra());
        assertEquals(c1.getDescricao(), c2.getDescricao());
        assertEquals(c1.getFabricante(), c2.getFabricante());
        assertEquals(c1.getNome(), c2.getNome());
        assertEquals(c1.getPlaca(), c2.getPlaca());
        assertEquals(c1.getPotencia(), c2.getPotencia(), 0.0f);
        assertEquals(c1.getQtdBatidas(), c2.getQtdBatidas());
        assertEquals(c1.getQtdPortas(), c2.getQtdPortas());
        assertEquals(c1.getQuilometragem(), c2.getQuilometragem(), 0.0f);
        assertEquals(c1.getRenavam(), c2.getRenavam());
        assertEquals(c1.getTipoPneu(), c2.getTipoPneu());
        assertEquals(c1.isEstaFuncional(), c2.isEstaFuncional());
        assertEquals(c1.isPermitidaCirculacao(), c2.isPermitidaCirculacao());
    }

}
