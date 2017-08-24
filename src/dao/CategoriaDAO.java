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
import model.Categoria;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-24-2017)
 */
public class CategoriaDAO {

    public ArrayList<Categoria> list() {
        ArrayList<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nome, descricao, ativa"
                + "\nFROM categorias";
        Connection conexao = Conexao.conectar();
        if (conexao != null) {
            try {
                Statement stmt = conexao.createStatement();
                stmt.executeQuery(sql);
                ResultSet rs = stmt.getResultSet();
                while (rs.next()) {
                    Categoria c = new Categoria();
                    c.setId(rs.getInt("id"));
                    c.setNome(rs.getString("nome"));
                    c.setDescricao(rs.getString("descricao"));
                    c.setAtivo(rs.getBoolean("ativa"));
                    categorias.add(c);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
                System.exit(0);
            } finally {
                Conexao.desconectar();
            }
        }
        return categorias;
    }

    public int inserir(Categoria c) {
        int codigo = Utilitarios.NAO_FOI_POSSIVEL_INSERIR;
        Connection conexao = Conexao.conectar();
        if (conexao != null) {
            String sql = "INSERT INTO categorias"
                    + "\n(nome, descricao) VALUE"
                    + "\n(?,?)";
            try {
                PreparedStatement stmt = conexao.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                stmt.setString(1, c.getNome());
                stmt.setString(2, c.getDescricao());
                stmt.execute();
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    codigo = rs.getInt(1);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao inserir Categoria", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            } finally {
                Conexao.desconectar();
            }
        }
        return codigo;
    }

    public int alterar(Categoria c) {
        int codigo = Utilitarios.NAO_FOI_POSSIVEL_ALTERAR;
        String sql = "UPDATE categorias SET"
                + "\nnome=?, descricao=?"
                + "\nWHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getDescricao());
            stmt.setInt(3, c.getId());
            codigo = stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar CategoriaDAO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return codigo;
    }

    public int excluir(int id) {
        int codigo = Utilitarios.NAO_FOI_POSSIVEL_EXCLUIR;
        String sql = "DELETE FROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, id);
            codigo = stmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao excluir CategoriaDAO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return codigo;
    }
    
    public int excluirStatus(int id) {
        String sql = "UPDATE categorias SET ativa = false"
                + "\nWHERE id = ?";
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
        String sql = "TRUNCATE categorias;";
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

    public Categoria getById(int id) {
        Categoria c = null;
        String sql = "SELECT id, nome, descricao, ativa"
                + "\nFROM categorias WHERE id = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setAtivo(rs.getBoolean("ativa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return c;
    }

    public Categoria getByNome(String nome) {
        Categoria c = null;
        String sql = "SELECT id, nome, descricao, ativa"
                + "\nFROM categorias WHERE nome = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setAtivo(rs.getBoolean("ativa"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);
        } finally {
            Conexao.desconectar();
        }
        return c;
    }

    public Categoria getByStatus(boolean status) {
        Categoria c = null;
        String sql = "SELECT id, nome, descricao, ativa"
                + "\nFROM categorias WHERE status = ?";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setBoolean(1, status);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs.next()) {
                c = new Categoria();
                c.setId(rs.getInt("id"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                c.setAtivo(rs.getBoolean("ativa"));
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
