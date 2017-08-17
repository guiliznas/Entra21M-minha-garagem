package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-14-2017)
 */
public class JFrameInicial extends JFrame implements JFrameComportamentosInterface{
    private JButton jButtonListaCarros;
    private JButton jButtonCadastroCarro;

    public JFrameInicial() {
        criarTela();
        criarComponentes();
        definirLocalizacao();
        adicionarOnClick();
        adicionarComponentes();
    }

    @Override
    public void criarTela() {
        setSize(600, 300);
        setTitle("Garagem");
        setIconImage(new ImageIcon(getClass().getResource("/imagens/garage_black.png")).getImage());
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void criarComponentes() {
        jButtonListaCarros = new JButton("Lista Carros");
        jButtonCadastroCarro = new JButton("Add Carro");
    }

    @Override
    public void definirLocalizacao() {
        jButtonListaCarros.setBounds(10, 10, 200, 35);
        jButtonCadastroCarro.setBounds(10, 55, 200, 35);
    }

    @Override
    public void adicionarComponentes() {
        add(jButtonListaCarros);
        add(jButtonCadastroCarro);
    }

    @Override
    public void adicionarOnClick() {
        jButtonListaCarros.addActionListener((ActionEvent e) -> {
            JFrameListaCarros jflc = new JFrameListaCarros();
        });
        
        jButtonCadastroCarro.addActionListener((ActionEvent e) -> {
            new jFrameCadastroCarro().setVisible(true);
        });
    }
}
