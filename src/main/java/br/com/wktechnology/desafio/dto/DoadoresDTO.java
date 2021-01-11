package br.com.wktechnology.desafio.dto;

import br.com.wktechnology.desafio.enums.TipoSanguineo;
import br.com.wktechnology.desafio.model.Contato;
import br.com.wktechnology.desafio.model.Pessoa;
import br.com.wktechnology.desafio.util.DateUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class DoadoresDTO {

    private String nome;

    private String cpf;

    private String rg;

    private String data_nasc;

    private String sexo;

    private String mae;

    private String pai;

    private String email;

    private String cep;

    private String endereco;

    private int numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String telefone_fixo;

    private String celular;

    private double altura;

    private int peso;

    private String tipo_sanguineo;

    public void adicionarDados(Pessoa entidade) {
        entidade.setNome(this.nome);
        entidade.setCpf(this.cpf);
        entidade.setRg(this.rg);
        Date dataConvertida = DateUtil.stringToDate(this.data_nasc, "dd/MM/yyyy");
        entidade.setDataNascimento(DateUtil.converteDateParaLocalDate(dataConvertida));
        entidade.setSexo(this.sexo);
        entidade.setMae(this.mae);
        entidade.setPai(this.pai);
        entidade.setAltura(new BigDecimal(this.altura));
        entidade.setPeso(new BigDecimal(this.peso));
        entidade.setTipoSanguineo(TipoSanguineo.getTipoSanguineoPorDescricao(this.tipo_sanguineo));
        entidade.setAtivo(Boolean.TRUE);

        List<Contato> contatos = new ArrayList<>();
        Contato contato = new Contato();
        contato.setEmail(this.email);
        contato.setTelefone(this.telefone_fixo);
        contato.setCelular(this.celular);
        contatos.add(contato);
        entidade.setContatos(contatos);

    }
}
