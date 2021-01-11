package br.com.wktechnology.desafio.repository;

import br.com.wktechnology.desafio.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByPermissoesDescricao(String permissaoDescricao);

    Page<Usuario> findByNomeContaining(String nome, Pageable pageable);

}
