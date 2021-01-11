package br.com.wktechnology.desafio.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class RecursoCriadoEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = 1L;
    
    private final HttpServletResponse response;
    
    private final Long codigo;
    
    public RecursoCriadoEvent(final Object source, final HttpServletResponse response, final Long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }
    
}
