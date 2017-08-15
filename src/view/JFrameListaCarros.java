package view;

import dao.CarroDAO;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Carro;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-14-2017)
 */
public class JFrameListaCarros extends JFrame implements JFrameComportamentosInterface {

    private JTable jTable;
    private DefaultTableModel modelo;
    private JScrollPane scroll;

    public JFrameListaCarros() {
        criarTela();
        criarComponentes();
        definirLocalizacao();
        adicionarOnClick();
        adicionarComponentes();
    }

    @Override
    public void criarTela() {
        setSize(600, 300);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void criarComponentes() {
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modelo.addColumn("Fabricante");
        modelo.addColumn("Nome");
        modelo.addColumn("Placa");
        modelo.addColumn("Cor");

        popularJTable();

        jTable = new JTable(modelo);
        scroll = new JScrollPane(jTable);

    }

    @Override
    public void definirLocalizacao() {
        scroll.setBounds(10, 10, 500, 200);
    }

    @Override
    public void adicionarComponentes() {
        add(scroll);
    }

    private void popularJTable() {
        ArrayList<Carro> carros = new CarroDAO().retornarListagem();
        for (Carro c : carros) {
            modelo.addRow(new Object[]{
                c.getFabricante(), c.getNome(), c.getPlaca(), c.getCor()
            });
        }
    }

    @Override
    public void adicionarOnClick() {

    }

}
