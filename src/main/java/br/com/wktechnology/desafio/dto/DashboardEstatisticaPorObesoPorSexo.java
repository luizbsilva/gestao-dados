package br.com.wktechnology.desafio.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DashboardEstatisticaPorObesoPorSexo {

    private String sexo;
    private BigDecimal percentual;

    public DashboardEstatisticaPorObesoPorSexo(String sexo, Double percentual) {
        this.sexo = sexo;
        this.percentual = new BigDecimal(percentual);
    }
}
