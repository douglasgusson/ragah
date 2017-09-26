
package br.com.ragah.dao.model;

import br.com.ragah.domain.Usuario;
import java.util.List;

/**
 *
 * @author douglas
 */
public interface UsuarioDAO {

    public List<Usuario> listarTodos();
    public void inserir(Usuario usuario);
    public void alterar(Usuario usuario);
    public void excluir(Usuario usuario);
    public Usuario buscar(Long id);
    public Usuario buscarPorNome(String username);
    public int countUsuarios();
    
}
