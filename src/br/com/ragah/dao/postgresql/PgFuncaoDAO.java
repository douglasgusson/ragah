package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.FuncaoDAO;
import br.com.ragah.domain.Funcao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PgFuncaoDAO implements FuncaoDAO {

    @Override
    public List<Funcao> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Funcao> list = new ArrayList<>();

        try {
            String query
                    = "SELECT cod_funcao, nome_funcao, data_criacao, data_atualizacao\n"
                    + "  FROM funcao;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Funcao f = new Funcao();

                f.setId(rs.getLong(1));
                f.setDescricao(rs.getString(2));
                f.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                f.setAlteracao(rs.getTimestamp(4).toLocalDateTime());

                list.add(f);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar funções em PgFuncaoDAO", ex);
        }

        return list;
    }

    @Override
    public void inserir(Funcao f) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO funcao(\n"
                    + "            nome_funcao, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, f.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(f.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(f.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir função em PgFuncaoDAO", ex);
        }
    }

    @Override
    public void alterar(Funcao f) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE funcao\n"
                    + "   SET nome_funcao=?, data_criacao=?, data_atualizacao=?\n"
                    + " WHERE cod_funcao=?";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, f.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(f.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(f.getAlteracao()));
                ps.setLong(4, f.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar função em PgFuncaoDAO", ex);
        }

    }

    @Override
    public void excluir(Funcao f) {
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM funcao\n"
                    + " WHERE cod_funcao = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, f.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir função em PgFuncaoDAO", ex);
        }
    }

    @Override
    public Funcao buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Funcao f = new Funcao();

        try {
            String query
                    = "SELECT cod_funcao, nome_funcao, data_criacao, data_atualizacao\n"
                    + "  FROM funcao WHERE cod_funcao = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                f.setId(rs.getLong(1));
                f.setDescricao(rs.getString(2));
                f.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                f.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar função por ID em PgFuncaoDAO", ex);
        }

        return f;
    }

}
