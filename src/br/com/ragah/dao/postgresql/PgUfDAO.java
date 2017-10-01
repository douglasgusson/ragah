package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.UfDAO;
import br.com.ragah.domain.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PgUfDAO implements UfDAO {

    @Override
    public List<Uf> listar() {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        List<Uf> estados = new ArrayList<>();

        try {
            String SQL = "SELECT sigla_uf, nome_uf FROM uf";
            ResultSet rs;
            try (PreparedStatement ps = con.prepareStatement(SQL)) {
                rs = ps.executeQuery();
                while (rs.next()) {
                    Uf estado = new Uf(rs.getString("sigla_uf"), rs.getString("nome_uf"));
                    estados.add(estado);
                }
            }
            rs.close();

            return estados;

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar estados em PgUfDAO", ex);
        }
    }

    @Override
    public Uf buscarPorSigla(String siglaUf) {
        try {

            Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
            Uf uf = new Uf();

            String query = "SELECT  \n"
                    + "      sigla_uf,\n"
                    + "      nome_uf\n"
                    + "  FROM uf\n"
                    + "    WHERE sigla_uf = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, siglaUf);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uf.setSigla(rs.getString("sigla_uf"));
                uf.setNome(rs.getString("nome_uf"));
            }

            return uf;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar UF por sigla.", ex);
        }
    }
}
