package br.com.ragah.dao.model;

import br.com.ragah.domain.Cidade;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface CidadeDAO {

    public List<Cidade> listar();
    public Cidade buscarPorId(int id);
    public List<Cidade> listarPorUf(String sigla);
    public List<Cidade> buscarPorNome(String nome);

}
