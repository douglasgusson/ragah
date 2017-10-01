package br.com.ragah.dao.model;

import br.com.ragah.domain.Funcionario;
import br.com.ragah.domain.TelefoneFuncionario;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface TelefoneFuncionarioDAO {

    public List<TelefoneFuncionario> buscarPorFuncionario(Funcionario f);
    public void inserir(TelefoneFuncionario tf);
    public void alterar(TelefoneFuncionario tf);
    public void excluir(TelefoneFuncionario tf);
    public void excluir(Funcionario f);

}
