
package br.com.ragah.dao.model;

import br.com.ragah.domain.Funcao;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface FuncaoDAO {

    public List<Funcao> listarTodos();
    public void inserir(Funcao f);
    public void alterar(Funcao f);
    public void excluir(Funcao f);
    public Funcao buscar(Long id);

}
