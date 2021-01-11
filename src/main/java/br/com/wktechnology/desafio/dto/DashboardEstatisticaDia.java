package br.com.wktechnology.desafio.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DashboardEstatisticaDia {

    private LocalDate dia;
    private Long vagas;
    private Long vagasOcupadas;
    private Long vagasLivres;

    public DashboardEstatisticaDia(LocalDate dia, Long vagas, Long vagasOcupadas, Long vagasLivres) {
        this.dia = dia;
        this.vagas = vagas;
        this.vagasOcupadas = vagasOcupadas;
        this.vagasLivres = vagasLivres;
    }
}
