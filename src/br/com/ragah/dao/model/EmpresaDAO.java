
package br.com.ragah.dao.model;

import br.com.ragah.domain.Empresa;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface EmpresaDAO {

    public List<Empresa> listarTodas();
    public void inserir(Empresa e);
    public void alterar(Empresa e);
    public void excluir(Empresa e);
    public Empresa buscar(Long id);

}
