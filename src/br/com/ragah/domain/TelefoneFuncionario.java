
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
public class TelefoneFuncionario {
      
    private Long id;
    private Funcionario funcionario;
    private String numTelefone;
    private String descricao;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;
    
}
