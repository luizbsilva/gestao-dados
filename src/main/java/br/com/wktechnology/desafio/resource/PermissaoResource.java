package br.com.wktechnology.desafio.resource;

import br.com.wktechnology.desafio.repository.filter.PermissaoFilter;
import br.com.wktechnology.desafio.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping("/por-usuario")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and #oauth2.hasScope('read')")
    public List<PermissaoFilter> listar(final Long codigo) {
        return this.permissaoService.buscarPermissaoUsuario(codigo);
    }

    @PutMapping("/{codigoPermissao}/{codigoUsuario}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PERMISSAO') and #oauth2.hasScope('write')")
    public void atualizarPropriedadeAtivo(@PathVariable final Long codigoPermissao, @PathVariable final Long codigoUsuario, @RequestBody final Boolean ativo) {
        this.permissaoService.atualizarPropriedadeAtivo(codigoPermissao, codigoUsuario, ativo);
    }

}
