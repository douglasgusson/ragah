package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.TelefoneFuncionarioDAO;
import br.com.ragah.domain.Funcionario;
import br.com.ragah.domain.TelefoneFuncionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class PgTelefoneFuncionarioDAO implements TelefoneFuncionarioDAO {

    @Override
    public List<TelefoneFuncionario> buscarPorFuncionario(Funcionario f) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();
        List<TelefoneFuncionario> list = new ArrayList<>();

        try {
            String query
                    = "SELECT cod_telefone, matr_funcionario, num_telefone, descricao, data_criacao, \n"
                    + "       data_atualizacao\n"
                    + "  FROM telefone_funcionario WHERE matr_funcionario = ?;";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, f.getMatricula());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                TelefoneFuncionario tf = new TelefoneFuncionario();

                tf.setId(rs.getLong(1));
                tf.setFuncionario(f);
                tf.setNumTelefone(rs.getString(3));
                tf.setDescricao(rs.getString(4));
                tf.setCriacao(rs.getTimestamp(5).toLocalDateTime());
                tf.setAlteracao(rs.getTimestamp(6).toLocalDateTime());

                list.add(tf);
            }

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar telefones em PgTelefoneFuncionarioDAO", ex);
        }

        return list;
    }

    @Override
    public void inserir(TelefoneFuncionario tf) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO telefone_funcionario(\n"
                    + "            matr_funcionario, num_telefone, descricao, data_criacao, \n"
                    + "            data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, tf.getFuncionario().getMatricula());
            ps.setString(2, tf.getNumTelefone());
            ps.setString(3, tf.getDescricao());
            ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir telefone funcionario em PgTelefoneFuncioanrioDAO", ex);
        }

    }

    @Override
    public void alterar(TelefoneFuncionario tf) {

        Connection con = DAOFactory.getDefaultDAOFactory().getConnection();

        try {
            String SQL
                    = "INSERT INTO telefone_funcionario(\n"
                    + "            matr_funcionario, num_telefone, descricao, data_criacao, \n"
                    + "            data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?, ?);";

            PreparedStatement ps = con.prepareStatement(SQL);

            ps.setInt(1, tf.getFuncionario().getMatricula());
            ps.setString(2, tf.getNumTelefone());
            ps.setString(3, tf.getDescricao());
            ps.setTimestamp(4, Timestamp.valueOf(tf.getCriacao()));
            ps.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));

            ps.executeUpdate();

            con.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao alterar telefone funcionario em PgTelefoneFuncioanrioDAO", ex);
        }
    }

    @Override
    public void excluir(TelefoneFuncionario tf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Funcionario f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
