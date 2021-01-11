package br.com.wktechnology.desafio.service;

import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorEstado;
import br.com.wktechnology.desafio.dto.DashboardEstatisticaPorTipoSanquineo;
import br.com.wktechnology.desafio.dto.DoadoresDTO;
import br.com.wktechnology.desafio.exceptionhandler.CPFException;
import br.com.wktechnology.desafio.exceptionhandler.DateExpection;
import br.com.wktechnology.desafio.exceptionhandler.ListaVaziaException;
import br.com.wktechnology.desafio.model.Endereco;
import br.com.wktechnology.desafio.model.Pessoa;
import br.com.wktechnology.desafio.repository.CidadeRepository;
import br.com.wktechnology.desafio.repository.PessoaRepository;
import br.com.wktechnology.desafio.util.ValidaDocumentos;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Pessoa salvar(final Pessoa pessoa) {
        pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
        return this.pessoaRepository.save(pessoa);
    }

    public Pessoa atualizar(final Long codigo, final Pessoa pessoa) {
        final Pessoa pessoaSalva = this.buscarPessoaPeloCodigo(codigo);

        pessoaSalva.getContatos().clear();
        pessoaSalva.getContatos().addAll(pessoa.getContatos());
        pessoaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));

        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo", "contatos");
        return this.pessoaRepository.save(pessoaSalva);
    }

    public void atualizarPropriedadeAtivo(final Long codigo, final Boolean ativo) {
        final Pessoa pessoaSalva = this.buscarPessoaPeloCodigo(codigo);
        pessoaSalva.setAtivo(ativo);
        this.pessoaRepository.save(pessoaSalva);
    }

    public Pessoa buscarPessoaPeloCodigo(final Long codigo) {
        final Optional<Pessoa> pessoaSalva = this.pessoaRepository.findById(codigo);
        if (!pessoaSalva.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return pessoaSalva.get();
    }

    public List<Pessoa> adicionarDoadores(List<DoadoresDTO> doadores) throws ListaVaziaException, DateExpection {
        List<Pessoa> doadoresAdicionados = new ArrayList<>();
        if (doadores.isEmpty())
            throw new ListaVaziaException("Lista Sem Doadores");

        for (DoadoresDTO doador : doadores) {

            if (!ValidaDocumentos.isValidoCPF(doador.getCpf())) {
                throw new CPFException("Cpf com formato inválido!");
            }

            if (new Date(doador.getData_nasc()).getTime() > new Date().getTime()) {
                throw new DateExpection("Data de nascimento não pode ser uma data Futura!.");
            }

            Pessoa pessoaExistente = pessoaRepository.findByCpf(doador.getCpf());
            if (Objects.nonNull(pessoaExistente)) {
                throw new CPFException("CPF já cadastrado para outra candidato.");
            }

            doadoresAdicionados.add(adiconarPessoa(doador));

        }
        return doadoresAdicionados;
    }

    private Pessoa adiconarPessoa(DoadoresDTO doador) {
        Pessoa entidade = new Pessoa();
        doador.adicionarDados(entidade);

        Endereco endereco = new Endereco();
        endereco.setLogradouro(doador.getEndereco());
        endereco.setBairro(doador.getBairro());
        endereco.setCep(doador.getCep());

        endereco.setCidade(cidadeRepository.findByNomeAndEstado_Uf(doador.getCidade(), doador.getEstado()));
        entidade.setEndereco(endereco);

        return salvar(entidade);

    }

    public Page<DashboardEstatisticaPorEstado> buscarDadosPorEstados(Pageable pageable) {
        return pessoaRepository.buscarDadosPorEstado(pageable);
    }

    public List<DashboardEstatisticaPorTipoSanquineo> getTipoSanquineoPorMediaDeIdade() {
        return pessoaRepository.getTipoSanquineoPorMediaDeIdade();
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll(Sort.by(Sort.Direction.ASC, "dataNascimento"));
    }

    public List<Pessoa> findBySexo(String sexo) {
        return pessoaRepository.findBySexo(sexo);
    }

    public List<DashboardEstatisticaPorTipoSanquineo> getPessoaPorPeso() {
        return pessoaRepository.getPessoaPorPeso();
    }
}
