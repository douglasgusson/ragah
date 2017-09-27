package br.com.ragah.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class Funcionario {

    private int matricula;
    private String nome;
    private String cpf;
    private String rg;
    private String ctps;
    private LocalDate dataNascimento;
    private EstadoCivil estadoCivil;
    private BigDecimal salario;
    private LocalDate dataAdmissao;
    private LocalDate dataDemissao;
    private Funcao funcao;
    private String endereco;
    private String cep;
    private String bairro;
    private Cidade cidade;
    private LocalDateTime criacao;
    private LocalDateTime alteracao;

}
