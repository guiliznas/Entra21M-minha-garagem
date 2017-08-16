
import dao.CarroDAO;
import database.Utilitarios;
import java.awt.EventQueue;
import java.sql.Date;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Carro;
import view.JFrameInicial;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-10-2017)
 */
public class Principal {

    public static void main(String[] args) {
//        test();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrameInicial jfi = new JFrameInicial();
            }
        });

    }

    private void test() {
        System.out.println("a");
        CarroDAO dao = new CarroDAO();
        Carro eoq = new Carro();
        eoq.setNome("308");
        eoq.setFabricante("Peugeot");
        eoq.setCor("Preto");
        eoq.setAnoFabricacao((short) 2013);
        eoq.setAnoLancamento((short) 2014);
        eoq.setEstaFuncional(true);
        eoq.setPermitidaCirculacao(false);
        eoq.setQtdBatidas((byte) 13);
        eoq.setPlaca("AAA 1489");
        eoq.setPotencia(150);
        eoq.setDataCompra(new Date(2013, 05, 03));
        eoq.setTipoPneu((short) 17);
        eoq.setRenavam(56165165);
        eoq.setQuilometragem(61516.5f);
        eoq.setQtdPortas((byte) 4);
        eoq.setChassi("A889DASHD8ADS98DSA9");
        eoq.setDescricao("Legal");
        int codigoInserir = dao.inserir(eoq);
        if (codigoInserir != Utilitarios.NAO_FOI_POSSIVEL_INSERIR) {
            eoq.setId(codigoInserir);
        } else {
            JOptionPane.showMessageDialog(null, "nao deu");
        }

        eoq.setNome("ata");
        int codigoAlterar = dao.alterar(eoq);
        if (codigoAlterar != Utilitarios.NAO_FOI_POSSIVEL_ALTERAR) {
            JOptionPane.showMessageDialog(null, "Alterado!");
        } else {
            JOptionPane.showMessageDialog(null, "Hj n");
        }

        for (int i = 1; i < 20; i++) {
            dao.excluir(i);
        }

        ArrayList<Carro> carros = dao.retornarListagem();
        for (Carro c : carros) {
            System.out.println(c.getId() + " - " + c.getNome());
        }
    }

}
