package br.com.ragah.domain;

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
public class Cidade {

    private Long id;
    private String nome;
    private Uf uf;

}
