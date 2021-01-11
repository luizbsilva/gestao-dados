package br.com.wktechnology.desafio.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DashboardEstatisticaPorEstado {

    private String nomeEstado;
    private Long qtdePessoas;

    public DashboardEstatisticaPorEstado(String nomeEstado, Long qtdePessoas) {
        this.nomeEstado = nomeEstado;
        this.qtdePessoas = qtdePessoas;
    }
}
