package dao;

import database.Conexao;
import database.Utilitarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Carro;

/**
 *
 * @author(name= Guilherme de Liz do Nascimento, date= 08-07-2017)
 */
public class CarroDAO {

    public ArrayList<Carro> retornarListagem() {
        ArrayList<Carro> carros = new ArrayList<>();
        String sql = "SELECT id, nome, cor, fabricante, placa, chassi,"
                + " quilometragem, potencia, data_compra, esta_funcionando,"
                + " permitida_circulacao, qtd_portas, qtd_batidas,"
                + " ano_fabricacao, ano_lancamento, tipo_pneu,"
                + " renavam, descricao FROM carros";
        Connection conexao = Conexao.conectar();
        if (conexao != null) {
            try {
                Statement stmt = conexao.createStatement();
                stmt.execute(sql);
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    Carro c = new Carro();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setCor(rs.getString("cor"));
                    c.setFabricante(rs.getString("fabricante"));
                    c.setPlaca(rs.getString("placa"));
                    c.setChassi(rs.getString("chassi"));
                    c.setAnoFabricacao(rs.getShort("ano_fabricacao"));
                    c.setAnoLancamento(rs.getShort("ano_lancamento"));
                    c.setTipoPneu(rs.getShort("tipo_pneu"));
                    c.setQuilometragem(rs.getFloat("quilometragem"));
                    c.setPotencia(rs.getFloat("potencia"));
                    c.setEstaFuncional(rs.getBoolean("esta_funcionando"));
                    c.setPermitidaCirculacao(rs.getBoolean("permitida_circulacao"));
                    c.setQtdBatidas(rs.getByte("qtd_batidas"));
                    c.setQtdPortas(rs.getByte("qtd_portas"));
                    c.setRenavam(rs.getInt("renavam"));
                    c.setDataCompra(rs.getDate("data_compra"));
                    c.setDescricao(rs.getString("descricao"));

                    carros.add(c);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
                System.exit(0);
            }
        }

        return carros;
    }

    public int inserir(Carro c) {
        int codigoInserido = Utilitarios.NAO_FOI_POSSIVEL_INSERIR;
        Connection conexao = Conexao.conectar();
        if (conexao != null) {
            String sql = "INSERT INTO carros "
                    + "(nome, cor, fabricante, placa, chassi, quilometragem, potencia, data_compra, esta_funcionando, "
                    + "permitida_circulacao, qtd_portas, qtd_batidas, ano_fabricacao, ano_lancamento, tipo_pneu, "
                    + "renavam, descricao) "
                    + "VALUE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            try {
                PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getCor());
                stmt.setString(3, c.getFabricante());
                stmt.setString(4, c.getPlaca());
                stmt.setString(5, c.getChassi());
                stmt.setFloat(6, c.getQuilometragem());
                stmt.setFloat(7, c.getPotencia());
                stmt.setDate(8, c.getDataCompra());
                stmt.setBoolean(9, c.isEstaFuncional());
                stmt.setBoolean(10, c.isPermitidaCirculacao());
                stmt.setByte(11, c.getQtdPortas());
                stmt.setByte(12, c.getQtdBatidas());
                stmt.setShort(13, c.getAnoFabricacao());
                stmt.setShort(14, c.getAnoLancamento());
                stmt.setShort(15, c.getTipoPneu());
                stmt.setInt(16, c.getRenavam());
                stmt.setString(17, c.getDescricao());
                stmt.execute();

                ResultSet rs = stmt.getGeneratedKeys();
                while (rs.next()) {
                    codigoInserido = rs.getInt(1);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.exit(0);
            } finally {
                Conexao.desconectar();
            }
        }

        return codigoInserido;
    }

    public int alterar(Carro c) {
        int codigoAlteracao = Utilitarios.NAO_FOI_POSSIVEL_ALTERAR;
        String sql = "UPDATE carros SET "
                + "nome = ?, cor = ?, fabricante = ?, placa = ?, chassi = ?, "
                + "quilometragem = ?, potencia = ?, data_compra = ?, esta_funcionando = ?, "
                + "permitida_circulacao = ?, qtd_portas = ?, qtd_batidas = ?, "
                + "ano_fabricacao = ?, ano_lancamento = ?, tipo_pneu = ?, "
                + "renavam = ?, descricao = ?"
                + " WHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getCor());
            stmt.setString(3, c.getFabricante());
            stmt.setString(4, c.getPlaca());
            stmt.setString(5, c.getChassi());
            stmt.setFloat(6, c.getQuilometragem());
            stmt.setFloat(7, c.getPotencia());
            stmt.setDate(8, c.getDataCompra());
            stmt.setBoolean(9, c.isEstaFuncional());
            stmt.setBoolean(10, c.isPermitidaCirculacao());
            stmt.setByte(11, c.getQtdPortas());
            stmt.setByte(12, c.getQtdBatidas());
            stmt.setShort(13, c.getAnoFabricacao());
            stmt.setShort(14, c.getAnoLancamento());
            stmt.setShort(15, c.getTipoPneu());
            stmt.setInt(16, c.getRenavam());
            stmt.setString(17, c.getDescricao());
            stmt.setInt(18, c.getId());

            codigoAlteracao = stmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return codigoAlteracao;
    }

    public int excluir(int id) {
        String sql = "DELETE FROM carros WHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return Utilitarios.NAO_FOI_POSSIVEL_EXCLUIR;
    }

    public void truncate() {
        String sql = "TRUNCATE carros;";
        try {
            Statement stmt = Conexao.conectar().createStatement();
            stmt.execute(sql);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }

    }

    public Carro buscarPorId(int id) {
        Carro c = null;
        String sql = "SELECT id, nome, cor, fabricante, placa, chassi,"
                + " quilometragem, potencia, data_compra, esta_funcionando,"
                + " permitida_circulacao, qtd_portas, qtd_batidas,"
                + " ano_fabricacao, ano_lancamento, tipo_pneu,"
                + " renavam, descricao FROM carros WHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                c = new Carro();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setCor(rs.getString("cor"));
                c.setFabricante(rs.getString("fabricante"));
                c.setPlaca(rs.getString("placa"));
                c.setChassi(rs.getString("chassi"));
                c.setAnoFabricacao(rs.getShort("ano_fabricacao"));
                c.setAnoLancamento(rs.getShort("ano_lancamento"));
                c.setTipoPneu(rs.getShort("tipo_pneu"));
                c.setQuilometragem(rs.getFloat("quilometragem"));
                c.setPotencia(rs.getFloat("potencia"));
                c.setEstaFuncional(rs.getBoolean("esta_funcionando"));
                c.setPermitidaCirculacao(rs.getBoolean("permitida_circulacao"));
                c.setQtdBatidas(rs.getByte("qtd_batidas"));
                c.setQtdPortas(rs.getByte("qtd_portas"));
                c.setRenavam(rs.getInt("renavam"));
                c.setDataCompra(rs.getDate("data_compra"));
                c.setDescricao(rs.getString("descricao"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return c;
    }
}
