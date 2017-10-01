package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.EmpresaDAO;
import br.com.ragah.domain.Empresa;
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
public class PgEmpresaDAO implements EmpresaDAO {

    @Override
    public List<Empresa> listarTodas() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<Empresa> list = new ArrayList<>();

        try {
            String query
                    = "";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Empresa e = new Empresa();

                e.setId(rs.getLong(1));
                e.setRazaoSocial(rs.getString(2));
                e.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                e.setAlteracao(rs.getTimestamp(4).toLocalDateTime());

                list.add(e);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar funções em PgFuncaoDAO", ex);
        }

        return list;
    }

    @Override
    public void inserir(Empresa e) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "";

            try (PreparedStatement ps = con.prepareStatement(SQL)) {

                ps.setString(1, e.getRazaoSocial());
                ps.setTimestamp(2, Timestamp.valueOf(e.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(e.getAlteracao()));

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir função em PgFuncaoDAO", ex);
        }
    }

    @Override
    public void alterar(Empresa e) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String sql
                    = "";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setString(1, e.getRazaoSocial());
                ps.setTimestamp(2, Timestamp.valueOf(e.getCriacao()));
                ps.setTimestamp(3, Timestamp.valueOf(e.getAlteracao()));
                ps.setLong(4, e.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar função em PgFuncaoDAO", ex);
        }

    }

    @Override
    public void excluir(Empresa e) {
        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String sql = "";

            try (PreparedStatement ps = con.prepareStatement(sql)) {

                ps.setLong(1, e.getId());

                ps.executeUpdate();
            }
            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao excluir função em PgFuncaoDAO", ex);
        }
    }

    @Override
    public Empresa buscar(Long id) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        Empresa e = new Empresa();

        try {
            String query
                    = "";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                e.setId(rs.getLong(1));
                e.setRazaoSocial(rs.getString(2));
                e.setCriacao(rs.getTimestamp(3).toLocalDateTime());
                e.setAlteracao(rs.getTimestamp(4).toLocalDateTime());
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar função por ID em PgFuncaoDAO", ex);
        }

        return e;
    }

}
