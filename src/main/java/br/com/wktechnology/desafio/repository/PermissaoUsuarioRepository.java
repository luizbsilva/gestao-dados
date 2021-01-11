package br.com.wktechnology.desafio.repository;

import br.com.wktechnology.desafio.model.Permissao;
import br.com.wktechnology.desafio.model.PermissaoUsuario;
import br.com.wktechnology.desafio.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {

    PermissaoUsuario findByPermissaoAndUsuario(final Permissao permissao, final Usuario usuario);

}
