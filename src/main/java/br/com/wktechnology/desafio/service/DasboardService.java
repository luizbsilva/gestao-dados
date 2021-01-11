package br.com.wktechnology.desafio.service;

import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorEstado;
import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorImcPorIdade;
import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorObesoPorSexo;
import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorTipoSanquineo;
import br.com.wktechnology.desafio.enums.TipoSanguineo;
import br.com.wktechnology.desafio.model.Pessoa;
import br.com.wktechnology.desafio.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DasboardService {

    @Autowired
    private PessoaService pessoaService;

    public Page<DashboardEstatisticaPorEstado> buscarDadosPorEstados(Pageable pageable) {
        return pessoaService.buscarDadosPorEstados(pageable);
    }

    public List<DashboardEstatisticaPorTipoSanquineo> getTipoSanquineoPorMediaDeIdade() {
        return pessoaService.getTipoSanquineoPorMediaDeIdade();
    }

    public List<DashboardEstatisticaPorImcPorIdade> getImcPorIdade() {

        List<Pessoa> pessoas = pessoaService.findAll();
        List<DashboardEstatisticaPorImcPorIdade> dadosRelatorio = new ArrayList<>();

        LocalDate maiorDataNascimento = pessoas.stream().max(Comparator.comparing(Pessoa::getDataNascimento))
                .get().getDataNascimento();
        Integer maiorIndade = DateUtil.calcularIdade(maiorDataNascimento);

        Integer faixaEtaria = Math.round(maiorIndade / 10) + 1;
        int idadeInicial = 0;
        int idadeFinal = 11;

        for (int i = 0; i < faixaEtaria + 1; i++) {

            int vInicial = idadeInicial;
            int vFinal = idadeFinal;

            List<Pessoa> listaEntreFaixaDeIdadeDoLoop = pessoas.stream()
                    .filter(p -> DateUtil.calcularIdade(p.getDataNascimento()) >= vInicial && DateUtil.calcularIdade(p.getDataNascimento()) < vFinal).collect(Collectors.toList());

            DashboardEstatisticaPorImcPorIdade imcPorFaixaDeIdadeDeDezAnosDto = new DashboardEstatisticaPorImcPorIdade();

            if (listaEntreFaixaDeIdadeDoLoop.size() > 0) {

                Double valorMedioImc = listaEntreFaixaDeIdadeDoLoop.stream()
                        .map(e -> e.getPeso().divide((e.getAltura().multiply(e.getAltura())), BigDecimal.ROUND_UP)).mapToDouble(BigDecimal::doubleValue).sum();

                if (i == 0) {

                    int idadeF = idadeFinal - 1;
                    imcPorFaixaDeIdadeDeDezAnosDto.setFaixaEtaria(idadeInicial + " a " + idadeF + " anos");
                    imcPorFaixaDeIdadeDeDezAnosDto
                            .setImcMedio(new BigDecimal(Math.round(valorMedioImc / listaEntreFaixaDeIdadeDoLoop.size())));
                } else {
                    imcPorFaixaDeIdadeDeDezAnosDto.setFaixaEtaria(idadeInicial + " a " + idadeFinal + " anos");
                    imcPorFaixaDeIdadeDeDezAnosDto
                            .setImcMedio(new BigDecimal(Math.round(valorMedioImc / listaEntreFaixaDeIdadeDoLoop.size())));
                }

                dadosRelatorio.add(imcPorFaixaDeIdadeDeDezAnosDto);

            }

            idadeInicial += 10;
            idadeFinal += 10;

        }

        return dadosRelatorio;

    }

    public List<DashboardEstatisticaPorObesoPorSexo> getPercentualObesoPorSexo() {
        List<DashboardEstatisticaPorObesoPorSexo> dashboardEstatisticaPorObesoPorSexos = new ArrayList();

        List<Pessoa> listaDoadoresMasculinos = pessoaService.findBySexo("Masculino");

        if (listaDoadoresMasculinos.size() == 0)
            throw new ArithmeticException(
                    "Não é possivel continuar, pois não foram encontrados homens na base de dados");

        List<Pessoa> listaFiltradaHomensAcimaDe30IMC = listaDoadoresMasculinos.stream()
                .filter(e -> e.getPeso().divide((e.getAltura().multiply(e.getAltura())), BigDecimal.ROUND_UP).doubleValue() > 30).collect(Collectors.toList());

        Double percentualHomens = Double.valueOf(listaFiltradaHomensAcimaDe30IMC.size() * 100) / listaDoadoresMasculinos.size();
        DashboardEstatisticaPorObesoPorSexo homens = new DashboardEstatisticaPorObesoPorSexo("Masculino", percentualHomens);
        dashboardEstatisticaPorObesoPorSexos.add(homens);

        List<Pessoa> listaFiltradaMulheres = pessoaService.findBySexo("Feminino");

        if (listaFiltradaMulheres.size() == 0)
            throw new ArithmeticException(
                    "Não é possivel continuar, pois não foram encontrados mulheres na base de dados");

        List<Pessoa> listaFiltradeMulheresAcimaDe30IMC = listaFiltradaMulheres.stream()
                .filter(e -> e.getPeso().divide((e.getAltura().multiply(e.getAltura())), BigDecimal.ROUND_UP).doubleValue() > 30).collect(Collectors.toList());
        Double percentualMulheres = Double.valueOf((listaFiltradeMulheresAcimaDe30IMC.size() * 100) / listaFiltradeMulheresAcimaDe30IMC.size());
        DashboardEstatisticaPorObesoPorSexo mulheres = new DashboardEstatisticaPorObesoPorSexo("Feminino", percentualMulheres);
        dashboardEstatisticaPorObesoPorSexos.add(mulheres);

        return dashboardEstatisticaPorObesoPorSexos;
    }

    public List<DashboardEstatisticaPorTipoSanquineo> getPorTipoSanguineoEIdade() {

        List<DashboardEstatisticaPorTipoSanquineo> pessoas = pessoaService.getPessoaPorPeso();


        List<DashboardEstatisticaPorTipoSanquineo> listaDoadorPorTipoSanguineo = new ArrayList();

        List<DashboardEstatisticaPorTipoSanquineo> listaCandidatosDoadoresFiltrada = pessoas.stream().filter(e ->e.getIdade()  > 15 && e.getIdade() < 70).collect(Collectors.toList());

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoAPositivo = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.A_POSITIVO) || e.getTipo().equals(TipoSanguineo.A_NEGATIVO)
                        || e.getTipo().equals(TipoSanguineo.O_POSITIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.A_POSITIVO, doadoresParaTipoAPositivo.size()));


        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoANegativo = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.A_POSITIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.A_NEGATIVO, doadoresParaTipoANegativo.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoBMais = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.B_POSITIVO) || e.getTipo().equals(TipoSanguineo.B_NEGATIVO)
                        || e.getTipo().equals(TipoSanguineo.O_POSITIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.B_POSITIVO, doadoresParaTipoBMais.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoBMenos = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.B_NEGATIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO)
                        || e.getTipo().equals(TipoSanguineo.O_POSITIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.B_NEGATIVO, doadoresParaTipoBMenos.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoABMais = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.A_POSITIVO) || e.getTipo().equals(TipoSanguineo.B_POSITIVO)
                        || e.getTipo().equals(TipoSanguineo.O_POSITIVO) || e.getTipo().equals(TipoSanguineo.AB_POSITIVO)
                        || e.getTipo().equals(TipoSanguineo.A_NEGATIVO) || e.getTipo().equals(TipoSanguineo.B_NEGATIVO)
                        || e.getTipo().equals(TipoSanguineo.O_NEGATIVO) || e.getTipo().equals(TipoSanguineo.AB_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.AB_POSITIVO, doadoresParaTipoABMais.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoABMenos = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.A_NEGATIVO) || e.getTipo().equals(TipoSanguineo.B_NEGATIVO)
                        || e.getTipo().equals(TipoSanguineo.O_NEGATIVO) || e.getTipo().equals(TipoSanguineo.AB_NEGATIVO))
                .collect(Collectors.toList());
        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.AB_NEGATIVO, doadoresParaTipoABMenos.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoOmais = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.O_POSITIVO) || e.getTipo().equals(TipoSanguineo.O_NEGATIVO))
                .collect(Collectors.toList());

        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.O_POSITIVO, doadoresParaTipoOmais.size()));

        List<DashboardEstatisticaPorTipoSanquineo> doadoresParaTipoOmenos = listaCandidatosDoadoresFiltrada.stream()
                .filter(e -> e.getTipo().equals(TipoSanguineo.O_NEGATIVO)).collect(Collectors.toList());

        listaDoadorPorTipoSanguineo.add(new DashboardEstatisticaPorTipoSanquineo(TipoSanguineo.O_NEGATIVO, doadoresParaTipoOmenos.size()));

        return listaDoadorPorTipoSanguineo;
    }
}
