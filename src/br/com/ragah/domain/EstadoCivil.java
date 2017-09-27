package br.com.ragah.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Douglas Gusson
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstadoCivil {

    private Long id;
    private String descricao;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;
    
}
