package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.CidadeDAO;
import br.com.ragah.dao.model.FuncaoDAO;
import br.com.ragah.dao.model.UfDAO;
import br.com.ragah.dao.model.UsuarioDAO;
import br.com.ragah.domain.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author douglas
 */
public class PostgreSQLDAOFactory extends DAOFactory {

    private static final Database DATABASE = Database.getDatabase();
    private static final String URL_BANCO = "jdbc:postgresql://" + DATABASE.toString();
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String USUARIO = DATABASE.getUsuario();
    private static final String SENHA = DATABASE.getSenha();

    private static Connection connection;

    @Override
    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
            return connection;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null,
                    "Conector para PostgreSQL não foi encontrado.",
                    "Erro de conexão",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            throw new DAOException("Não foi possível estabelecer conexão com o banco de dados.", ex);
        }
        return null;
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        return new PgUsuarioDAO();
    }

    @Override
    public UfDAO getUfDAO() {
        return new PgUfDAO();
    }

    @Override
    public CidadeDAO getCidadeDAO() {
        return new PgCidadeDAO();
    }

    @Override
    public FuncaoDAO getFuncaoDAO() {
        return new PgFuncaoDAO();
    }

}
