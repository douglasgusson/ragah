
package br.com.ragah.dao.model;

import br.com.ragah.domain.EstadoCivil;
import java.util.List;

/**
 *
 * @author Douglas Gusson
 */
public interface EstadoCivilDAO {
    
    public List<EstadoCivil> listarTodos();
    public void inserir(EstadoCivil ec);
    public void alterar(EstadoCivil ec);
    public void excluir(EstadoCivil ec);
    public EstadoCivil buscar(Long id);
    
}
