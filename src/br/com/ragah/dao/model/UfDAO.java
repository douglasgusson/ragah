
package br.com.ragah.dao.model;

import br.com.ragah.domain.Uf;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface UfDAO {
    
    public List<Uf> listar();
    public Uf buscarPorSigla(String siglaUf);
    
}
