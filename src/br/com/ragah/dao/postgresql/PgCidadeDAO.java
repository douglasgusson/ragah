package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.CidadeDAO;
import br.com.ragah.dao.model.UfDAO;
import br.com.ragah.domain.Cidade;
import br.com.ragah.domain.Uf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class PgCidadeDAO implements CidadeDAO {

    @Override
    public List<Cidade> listar() {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        List<Cidade> cidades = new ArrayList<>();

        UfDAO ufDAO = DAOFactory.getDefaultDAOFactory().getUfDAO();

        try {

            String SQL = "SELECT cod_cidade, nome_cidade, sigla_uf FROM cidade ORDER BY nome_cidade;";

            PreparedStatement ps = connection.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Cidade cidade = new Cidade();
                Uf uf = new Uf();

                cidade.setId(rs.getLong("cod_cidade"));
                cidade.setNome(rs.getString("nome_cidade"));

                uf = ufDAO.buscarPorSigla(rs.getString("sigla_uf"));

                cidade.setUf(uf);
                cidades.add(cidade);
            }

            rs.close();

            return cidades;

        } catch (SQLException ex) {
            throw new RuntimeException("Falha ao listar cidades em JDBCCidadeDAO", ex);
        }
    }

    @Override
    public Cidade buscarPorId(Long id) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        Cidade cidade = null;

        try {
            String SQL = "SELECT  \n"
                    + "      cod_cidade,\n"
                    + "      nome_cidade,\n"
                    + "      sigla_uf\n"
                    + "  FROM cidade \n"
                    + "    WHERE cod_cidade = ?;";

            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setLong(1, id);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Long idCidade = rs.getLong(1);
                    String nome = rs.getString(2);
                    String siglaUf = rs.getString(3);

                    UfDAO ufDAO = DAOFactory.getDefaultDAOFactory().getUfDAO();
                    Uf uf = ufDAO.buscarPorSigla(siglaUf);

                    cidade = new Cidade(idCidade, nome, uf);
                }
            }

            connection.close();

            return cidade;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar cidade por id", ex);
        }
    }

    @Override
    public List<Cidade> listarPorUf(String sigla) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        List<Cidade> cidades = new ArrayList<>();

        try {
            String SQL = "SELECT * FROM cidade WHERE sigla_uf = ?;";
            ResultSet rs;
            try (PreparedStatement ps = connection.prepareStatement(SQL)) {
                ps.setString(1, sigla);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Cidade cidade = new Cidade();
                    Uf estado = new Uf();

                    cidade.setId(rs.getLong("cod_cidade"));
                    cidade.setNome(rs.getString("nome_cidade"));
                    estado.setSigla(rs.getString("sigla_uf"));
                    cidade.setUf(estado);
                    cidades.add(cidade);
                }
            }
            rs.close();

            return cidades;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao listar cidades por UF", ex);
        }
    }

    @Override
    public List<Cidade> buscarPorNome(String nome) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            List<Cidade> cidades = new ArrayList<>();

            UfDAO ufDAO = DAOFactory.getDefaultDAOFactory().getUfDAO();

            String SQL = "SELECT cod_cidade, nome_cidade, sigla_uf FROM cidade "
                    + "WHERE LOWER(nome_cidade) LIKE '%" + nome.toLowerCase() + "%';";

            try (PreparedStatement ps = connection.prepareStatement(SQL)) {

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Cidade cidade = new Cidade();
                    Uf uf = new Uf();

                    cidade.setId(rs.getLong("cod_cidade"));
                    cidade.setNome(rs.getString("nome_cidade"));

                    uf = ufDAO.buscarPorSigla(rs.getString("sigla_uf"));
                    cidade.setUf(uf);
                    
                    cidades.add(cidade);
                }
            }
            connection.close();

            return cidades;

        } catch (SQLException ex) {
            throw new DAOException("Erro ao buscar cidades por nome", ex);
        }
    }
}
