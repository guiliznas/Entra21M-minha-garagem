package view;

import dao.CarroDAO;
import dao.CategoriaDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import model.Categoria;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-24-2017)
 */
public class jFrameListaCategoria extends JFrame implements JFrameComportamentosInterface {

    private JTable jTable;
    private DefaultTableModel modelo;
    private JScrollPane scroll;
    private JButton jButtonExcluir;

    public jFrameListaCategoria() {
        criarTela();
        criarComponentes();
        definirLocalizacao();
        adicionarOnClick();
        adicionarComponentes();
    }

    @Override
    public void criarTela() {
        setSize(600, 300);
        setTitle("Listagem de Categorias");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/garage_black.png")).getImage());
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
        modelo.addColumn("Codigo");
        modelo.addColumn("Nome");
        modelo.addColumn("Ativo");
        modelo.addColumn("Descricao");

        popularJTable();

        jTable = new JTable(modelo);
        scroll = new JScrollPane(jTable);

        jButtonExcluir = new JButton("Excluir");
    }

    @Override
    public void definirLocalizacao() {
        scroll.setBounds(10, 10, 500, 200);
        jButtonExcluir.setBounds(515, 10, 75, 25);
    }

    @Override
    public void adicionarComponentes() {
        add(scroll);
        add(jButtonExcluir);
    }

    private void popularJTable() {
        ArrayList<Categoria> categorias = new CategoriaDAO().list();
        for (Categoria c : categorias) {
            modelo.addRow(new Object[]{
                c.getId(), c.getNome(), c.isAtivo(), c.getDescricao()
            });
        }
    }

    @Override
    public void adicionarOnClick() {
        jButtonExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acaoExcluir();
            }
        });
    }

    private void acaoExcluir() {
        int[] linhasSelecionadas = jTable.getSelectedRows();
        if (linhasSelecionadas.length > 0) {
            for (int i = linhasSelecionadas.length - 1; i > -1; i--) {
                int linhaSelecionada = linhasSelecionadas[i];
                new CategoriaDAO().excluir(Integer.parseInt(modelo.getValueAt(linhaSelecionada, 0).toString()));
                modelo.removeRow(linhaSelecionada);
            }
            jTable.clearSelection();
        }
    }

}
