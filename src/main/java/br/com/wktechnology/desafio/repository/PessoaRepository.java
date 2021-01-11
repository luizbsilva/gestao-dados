package br.com.wktechnology.desafio.repository;

import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorEstado;
import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorTipoSanquineo;
import br.com.wktechnology.desafio.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);

    Pessoa findByCpf(String cpf);

    @Query("select new br.com.wktechnology.desafio.dto.DashboardEstatisticaPorEstado( p.endereco.cidade.estado.nome, count (*)) " +
            "from Pessoa p group by p.endereco.cidade.estado.nome " +
            "order by p.endereco.cidade.estado.nome")
    Page<DashboardEstatisticaPorEstado> buscarDadosPorEstado(Pageable pageable);

    @Query("select new br.com.wktechnology.desafio.dto.DashboardEstatisticaPorTipoSanquineo( p.tipoSanguineo, " +
            "round(AVG(extract(year from age(CURRENT_DATE, p.dataNascimento))))) " +
            "from Pessoa p group by p.tipoSanguineo " +
            "order by p.tipoSanguineo")
    List<DashboardEstatisticaPorTipoSanquineo> getTipoSanquineoPorMediaDeIdade();

    List<Pessoa> findBySexo(String sexo);

    @Query("select new br.com.wktechnology.desafio.dto.DashboardEstatisticaPorTipoSanquineo(p.tipoSanguineo, p.dataNascimento)" +
            "from Pessoa p " +
            "where p.peso >= 50")
    List<DashboardEstatisticaPorTipoSanquineo> getPessoaPorPeso();
}