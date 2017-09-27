package br.com.ragah.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Douglas Gusson
 */
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

    /*
        matricula        INTEGER      NOT NULL,
        nome             VARCHAR(50)  NOT NULL, 
        cpf              CHAR(11)     NOT NULL,
        rg               VARCHAR(20)  NOT NULL,
        data_nascimento  DATE         NOT NULL,
        estado_civil     INTEGER      NOT NULL,
        salario          NUMERIC(7,2) NOT NULL,
        data_admissao    DATE         NULL,
        data_demissao    DATE         NOT NULL,
        funcao           INTEGER      NOT NULL,
        endereco         VARCHAR(60)  NOT NULL,
        cep              CHAR(8)      NOT NULL,  
        bairro           VARCHAR(30)  NOT NULL,
        cidade           INTEGER      NOT NULL,
        data_criacao     TIMESTAMP    NOT NULL,
        data_atualizacao TIMESTAMP    NOT NULL,
     */
}
