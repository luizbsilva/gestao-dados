package br.com.wktechnology.desafio.repository.filter;

import br.com.wktechnology.desafio.model.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PermissoesFilter {

    private Usuario usuario;

    private List<PermissaoFilter> permissoes;

}
