package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.EstadoCivilDAO;
import br.com.ragah.domain.EstadoCivil;
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
public class PgEstadoCivilDAO implements EstadoCivilDAO {

    @Override
    public List<EstadoCivil> listarTodos() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<EstadoCivil> list = new ArrayList<>();

        try {
            String query
                    = "SELECT cod_estado_civil, nome_estado_civil, data_criacao, data_atualizacao\n"
                    + "  FROM estado_civil;";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EstadoCivil ec = new EstadoCivil();

                ec.setId(rs.getLong(1));
                ec.setDescricao(rs.getString(2));
                ec.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                ec.setAlteracao(rs.getTimestamp(4).toLocalDateTime());

                list.add(ec);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar estados civis em PgEstadoCivilDAO", ex);
        }

        return list;
    }

    @Override
    public void inserir(EstadoCivil ec) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO estado_civil(\n"
                    + "            nome_estado_civil, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?);";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, ec.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(ec.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(ec.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir estado civil em PgEstadoCivilDAO", ex);
        }
    }

    @Override
    public void alterar(EstadoCivil ec) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "UPDATE estado_civil\n"
                    + "   SET nome_estado_civil=?, data_criacao=?, data_atualizacao=?\n"
                    + " WHERE cod_estado_civil=?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, ec.getDescricao());
                ps.setTimestamp(2, Timestamp.valueOf(ec.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(ec.getAlteracao()));
                ps.setLong(4, ec.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar estado civil em PgEstadoCivilDAO", ex);
        }

    }

    @Override
    public void excluir(EstadoCivil ec) {
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String SQL = "DELETE FROM estado_civil\n"
                    + " WHERE cod_estado_civil = ?;";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setLong(1, ec.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir estado civil em PgEstadoCivilDAO", ex);
        }
    }

    @Override
    public EstadoCivil buscar(Long id) {
        
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        EstadoCivil ec = new EstadoCivil();

        try {
            String query
                    = "SELECT cod_estado_civil, nome_estado_civil, data_criacao, data_atualizacao\n"
                    + "  FROM estado_civil WHERE cod_estado_civil = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ec.setId(rs.getLong(1));
                ec.setDescricao(rs.getString(2));
                ec.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                ec.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar estado civil por ID em PgEstadoCivilDAO", ex);
        }

        return ec;
    }

}
