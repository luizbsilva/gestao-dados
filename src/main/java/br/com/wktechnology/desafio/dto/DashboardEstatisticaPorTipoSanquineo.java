package br.com.wktechnology.desafio.dto;

import br.com.wktechnology.desafio.enums.TipoSanguineo;
import br.com.wktechnology.desafio.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DashboardEstatisticaPorTipoSanquineo {

    private String tipoSanguineo;
    private BigDecimal mediaDeIdade;
    private Integer quantidade;
    private Integer idade;
    private TipoSanguineo tipo;

    public DashboardEstatisticaPorTipoSanquineo(TipoSanguineo tipoSanguineo, Double mediaDeIdade) {
        this.tipoSanguineo = tipoSanguineo.getDescricao();
        this.mediaDeIdade = new BigDecimal(mediaDeIdade);
    }

    public DashboardEstatisticaPorTipoSanquineo(TipoSanguineo tipoSanguineo, Integer quantidade) {
        this.tipoSanguineo = tipoSanguineo.getDescricao();
        this.quantidade = quantidade;
    }

    public DashboardEstatisticaPorTipoSanquineo(TipoSanguineo tipoSanguineo, LocalDate dataNascimento) {
        this.tipo = tipoSanguineo;
        this.idade = DateUtil.calcularIdade(dataNascimento);
    }


}
