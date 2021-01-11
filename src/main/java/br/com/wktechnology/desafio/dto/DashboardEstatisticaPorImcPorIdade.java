package br.com.wktechnology.desafio.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DashboardEstatisticaPorImcPorIdade {

    private String faixaEtaria;
    private BigDecimal imcMedio;

    public DashboardEstatisticaPorImcPorIdade() {
    }

    public DashboardEstatisticaPorImcPorIdade(String faixaEtaria, BigDecimal imcMedio) {
        this.faixaEtaria = faixaEtaria;
        this.imcMedio = imcMedio;
    }
}
