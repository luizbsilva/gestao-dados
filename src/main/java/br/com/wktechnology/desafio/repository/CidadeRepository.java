package br.com.wktechnology.desafio.repository;

import br.com.wktechnology.desafio.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findByEstadoCodigo(Long estadoCodigo);

    Cidade findByNomeAndEstado_Uf(String nomeCidade, String uf);

}
