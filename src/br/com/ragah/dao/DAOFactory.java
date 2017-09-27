package br.com.ragah.dao;

import br.com.ragah.dao.model.CidadeDAO;
import br.com.ragah.dao.model.FuncaoDAO;
import br.com.ragah.dao.model.UfDAO;
import br.com.ragah.dao.model.UsuarioDAO;
import br.com.ragah.dao.postgresql.PostgreSQLDAOFactory;
import java.sql.Connection;

/**
 *
 * @author douglas
 */
public abstract class DAOFactory {

    public static final int POSTGRESQL = 0;
    public static final int MYSQL = 1;
    public static final int OUTRO = 2;

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract UfDAO getUfDAO();
    public abstract CidadeDAO getCidadeDAO();
    public abstract FuncaoDAO getFuncaoDAO();
    public abstract Connection getConnection();

    public static DAOFactory getDAOFactory(int whichfactory) {
        switch (whichfactory) {
            case POSTGRESQL:
                return new PostgreSQLDAOFactory();
            case MYSQL:
                return null;
            case OUTRO:
                return null;
            default:
                return null;
        }
    }

    public static DAOFactory getDefaultDAOFactory() {
        return getDAOFactory(POSTGRESQL);
    }

}
