package br.com.wktechnology.desafio.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {
    
    private String logradouro;
    
    private String numero;
    
    private String bairro;
    
    private String cep;
    
    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
    private Cidade cidade;

}
