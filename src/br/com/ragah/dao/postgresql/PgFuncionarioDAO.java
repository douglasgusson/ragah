package br.com.ragah.dao.postgresql;

import br.com.ragah.dao.DAOException;
import br.com.ragah.dao.DAOFactory;
import br.com.ragah.dao.model.CidadeDAO;
import br.com.ragah.dao.model.EmpresaDAO;
import br.com.ragah.dao.model.EstadoCivilDAO;
import br.com.ragah.dao.model.FuncaoDAO;
import br.com.ragah.dao.model.FuncionarioDAO;
import br.com.ragah.dao.model.TelefoneFuncionarioDAO;
import br.com.ragah.domain.Funcionario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author douglas
 */
public class PgFuncionarioDAO implements FuncionarioDAO {

    @Override
    public List<Funcionario> listarTodos() {
        
        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        EstadoCivilDAO estadoCivilDAO = DAOFactory.getDefaultDAOFactory().getEstadoCivilDAO();
        FuncaoDAO funcaoDAO = DAOFactory.getDefaultDAOFactory().getFuncaoDAO();
        CidadeDAO cidadeDAO = DAOFactory.getDefaultDAOFactory().getCidadeDAO();
        EmpresaDAO empresaDAO = DAOFactory.getDefaultDAOFactory().getEmpresaDAO();
        TelefoneFuncionarioDAO telefoneFuncionarioDAO = DAOFactory.getDefaultDAOFactory().getTelefoneFuncionarioDAO();
        
        List<Funcionario> funcionarios = new ArrayList<>();
    
        try {
            String query
                    = "";

            PreparedStatement ps = connection.prepareStatement(
                    query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Funcionario f = new Funcionario();

                f.setMatricula(rs.getInt(1));
                
                f.setDataNascimento(rs.getDate(12).toLocalDate());
                
                f.setCriacao(rs.getTimestamp(13).toLocalDateTime());
                f.setAlteracao(rs.getTimestamp(14).toLocalDateTime());


                funcionarios.add(f);
            }

            connection.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao listar funcionarios em PgFuncionarioDAO", ex);
        }

        return funcionarios;
        
    }

    @Override
    public void inserir(Funcionario f) {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();

        try {

            String sql = "INSERT INTO funcionario(\n"
                    + "            matricula, nome, cpf, rg, ctps, data_nascimento, estado_civil, \n"
                    + "            salario, data_admissao, data_demissao, funcao, endereco, cep, \n"
                    + "            bairro, cidade, empresa, data_criacao, data_atualizacao)\n"
                    + "    VALUES (?, ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?, ?, \n"
                    + "            ?, ?, ?, ?, ?);";

            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1, f.getMatricula());
            ps.setString(2, f.getNome());
            ps.setString(3, f.getCpf());
            ps.setString(4, f.getRg());
            ps.setString(5, f.getCtps());
            ps.setDate(6, Date.valueOf(f.getDataNascimento()));
            ps.setLong(7, f.getEstadoCivil().getId());
            ps.setBigDecimal(8, f.getSalario());
            ps.setDate(9, Date.valueOf(f.getDataAdmissao()));

            if (f.getDataDemissao() != null) {
                ps.setDate(10, Date.valueOf(f.getDataDemissao()));
            } else {
                ps.setDate(10, null);
            }

            ps.setLong(11, f.getFuncao().getId());
            ps.setString(12, f.getEndereco());
            ps.setString(13, f.getCep());
            ps.setString(14, f.getBairro());
            ps.setLong(15, f.getCidade().getId());
            ps.setLong(16, f.getEmpresa().getId());
            ps.setTimestamp(17, Timestamp.valueOf(f.getCriacao()));
            ps.setTimestamp(18, Timestamp.valueOf(f.getAlteracao()));

            ps.executeUpdate();

            connection.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao inserir funcaionario em PgFuncionarioDAO", ex);
        }

    }

    @Override
    public void alterar(Funcionario f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void excluir(Funcionario f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario buscar(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Funcionario> buscarPorNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Funcionario buscarUltimoFuncionario() {

        Connection connection = DAOFactory.getDefaultDAOFactory().getConnection();
        Funcionario f = new Funcionario();

        try {

            String query = "SELECT matricula FROM funcionario \n"
                    + "  WHERE data_criacao = (SELECT MAX(data_criacao) FROM funcionario);";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                f.setMatricula(rs.getInt(1));
            }

            connection.close();

        } catch (SQLException ex) {
            throw new DAOException("Falha ao buscar ultimo funcionario em PgFuncionarioDAO", ex);
        }

        return f;

    }

}
