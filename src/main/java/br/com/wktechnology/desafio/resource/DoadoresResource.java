package br.com.wktechnology.desafio.resource;

import br.com.wktechnology.desafio.dto.DoadoresDTO;
import br.com.wktechnology.desafio.event.RecursoCriadoEvent;
import br.com.wktechnology.desafio.exceptionhandler.DateExpection;
import br.com.wktechnology.desafio.exceptionhandler.ListaVaziaException;
import br.com.wktechnology.desafio.model.Pessoa;
import br.com.wktechnology.desafio.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/doadores")
public class DoadoresResource {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ApplicationEventPublisher publisher;


    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody final List<DoadoresDTO> doadores, final HttpServletResponse response) throws DateExpection, ListaVaziaException {
        final List<Pessoa> doadoresSalvos = this.pessoaService.adicionarDoadores(doadores);
        this.publisher.publishEvent(new RecursoCriadoEvent(this, response, doadoresSalvos.get(0).getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(doadoresSalvos.get(0));
    }
}
