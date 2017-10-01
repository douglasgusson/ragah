package br.com.ragah.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author douglas
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {

    private Long id;
    private String razaoSocial;
    private String cnpj;
    private String endereco;
    private String cep;
    private String bairro;
    private Cidade cidade;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

}
