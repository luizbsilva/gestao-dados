package br.com.wktechnology.desafio.resource;

import br.com.wktechnology.desafio.dto.*;
import br.com.wktechnology.desafio.service.DasboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardResource {

    @Autowired
    private DasboardService dasboardService;

    @GetMapping("/estatisticas/por-estado")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_DASHBOARD') and #oauth2.hasScope('read')")
    public Page<DashboardEstatisticaPorEstado> getImcPorMediaDeIdade(final Pageable pageable) {
        return dasboardService.buscarDadosPorEstados(pageable);
    }

    @GetMapping("/estatisticas/imc-por-faixa-idade")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_DASHBOARD') and #oauth2.hasScope('read')")
    public List<DashboardEstatisticaPorImcPorIdade> getImcPorIdade() {
        return dasboardService.getImcPorIdade();
    }


    @RequestMapping(value = "estatisticas/percentual-obesos", method = RequestMethod.GET)
    public List<DashboardEstatisticaPorObesoPorSexo> getPercentualObesoPorSexo() {
        return dasboardService.getPercentualObesoPorSexo();
    }

    @GetMapping("/estatisticas/tipo-sanguineo-por-media-de-idade")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_DASHBOARD') and #oauth2.hasScope('read')")
    public List<DashboardEstatisticaPorTipoSanquineo> getTipoSanquineoPorMediaDeIdade() {
        return dasboardService.getTipoSanquineoPorMediaDeIdade();
    }

    @RequestMapping(value = "estatisticas/quantidade-por-tipo-sanguineo", method = RequestMethod.GET)
    public List<DashboardEstatisticaPorTipoSanquineo> getPorTipoSanguineoEIdade() {
        return dasboardService.getPorTipoSanguineoEIdade();
    }


}
