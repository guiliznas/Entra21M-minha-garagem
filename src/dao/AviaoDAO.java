package dao;

import database.Conexao;
import database.Utilitarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Aviao;

/**
 *
 * @author(name= Guilherme de Liz, date= 08-28-2017)
 */
public class AviaoDAO {

    public ArrayList<Aviao> list() {
        ArrayList<Aviao> avioes = new ArrayList<>();
        String sql = "SELECT id, nome, id_categoria FROM avioes";
        try {
            Statement st = Conexao.conectar().createStatement();
            st.executeQuery(sql);
            ResultSet rs = st.getResultSet();
            while (rs.next()) {
                Aviao a = new Aviao();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCategoria(new CategoriaDAO().getById(rs.getInt("id_categoria")));
                avioes.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return avioes;
    }

    public int add(Aviao a) {
        String sql = "INSERT INTO avioes (id_categoria, nome)"
                + " VALUE (?,?)";
        int codigo = Utilitarios.NAO_FOI_POSSIVEL_INSERIR;
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, a.getCategoria().getId());
            stmt.setString(2, a.getNome());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                codigo = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return codigo;
    }
    
    public int edit(Aviao a){
        String sql = "UPDATE avioes SET id_categoria = ?, nome = ?"
                + "\nWHERE id = ?";
        int codigo = Utilitarios.NAO_FOI_POSSIVEL_ALTERAR;
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, a.getCategoria().getId());
            stmt.setString(2, a.getNome());
            stmt.setInt(3, a.getId());
            codigo = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return codigo;
    }

    public Aviao getById(int id){
        String sql = "SELECT id, nome, id_categoria FROM avioes WHERE id = ?";
        Aviao a = null;
        try {
            PreparedStatement st = Conexao.conectar().prepareStatement(sql);
            st.setInt(1, id);
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                a = new Aviao();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCategoria(new CategoriaDAO().getById(rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return a;
    }
    
    public Aviao getByName(String name){
        String sql = "SELECT id, nome, id_categoria FROM avioes WHERE nome = ?";
        Aviao a = null;
        try {
            PreparedStatement st = Conexao.conectar().prepareStatement(sql);
            st.setString(1, name);
            st.execute();
            ResultSet rs = st.getResultSet();
            if (rs.next()) {
                a = new Aviao();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCategoria(new CategoriaDAO().getById(rs.getInt("id_categoria")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return a;
    }
    
    public boolean excluir(int id){
        String sql = "DELETE FROM avioes WHERE id = ?;";
        try {
            PreparedStatement stmt = Conexao.conectar().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            if (getById(id) == null) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexao.desconectar();
        }
        return false;
    }
    
    public void truncate(){
        String sql = "TRUNCATE avioes;";
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
}
