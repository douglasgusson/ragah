
package br.com.ragah.dao.model;

import br.com.ragah.domain.Funcionario;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface FuncionarioDAO {

    public List<Funcionario> listarTodos();
    public void inserir(Funcionario f);
    public void alterar(Funcionario f);
    public void excluir(Funcionario f);
    public Funcionario buscar(int matricula);
    public List<Funcionario> buscarPorNome(String nome);
    public Funcionario buscarUltimoFuncionario();
    
}
