package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author(name= Guilherme de Liz do Nascimento, date= 08-10-2017)
 */
public class Conexao {

    private static Connection conexao;
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://localhost/minha_garagem";
    private static final String USER = "root";
    private static final String PASS = "";

    public static Connection conectar() {
        try {
            Properties propriedades = new Properties();
            propriedades.setProperty("user", USER);
            propriedades.setProperty("password", PASS);

            Class.forName(DRIVER);
            conexao = DriverManager.getConnection(HOST, propriedades);
            return conexao;
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return null;
        }
    }

    public static void desconectar() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void truncate() {
        conectar();
        if (conexao != null) {
            try {
                Statement st = conexao.createStatement();
                st.addBatch("SET FOREIGN_KEY_CHECKS = 0;");
                st.addBatch("TRUNCATE TABLE carros;");
                st.addBatch("TRUNCATE TABLE categorias;");
                st.addBatch("TRUNCATE TABLE avioes;");
                st.addBatch("SET FOREIGN_KEY_CHECKS = 1;");
                st.executeBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                desconectar();
            }
        }
    }
}
