package br.com.wktechnology.desafio.repository;

import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorEstado;
import br.com.wktechnology.desafio.model.Pessoa;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Repository
@Component
public class PessoaRepositoryDAO extends JpaDAO {

    public List<DashboardEstatisticaPorEstado> buscarDadosPorEstados() {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<DashboardEstatisticaPorEstado> criteria = criteriaBuilder.createQuery(DashboardEstatisticaPorEstado.class);
        Root<Pessoa> from = criteria.from(Pessoa.class);

        criteria.multiselect(montarCamposDeRetornoParaDadosPorEstado(from, criteriaBuilder));
        criteria.groupBy(from.get("cidade.estado"));
        criteria.orderBy(criteriaBuilder.asc(from.get("cidade.estado.nome")));

        final TypedQuery<DashboardEstatisticaPorEstado> query = this.getEntityManager().createQuery(criteria);

        return query.getResultList();
    }

    private List<Selection<?>> montarCamposDeRetornoParaDadosPorEstado(Root<Pessoa> from, CriteriaBuilder criteriaBuilder) {
        final List<Selection<?>> camposDeRetorno = new ArrayList<>();

        camposDeRetorno.add(criteriaBuilder.count(from));
        camposDeRetorno.add(from.get("cidade.estado.nome"));

        return camposDeRetorno;
    }


}
