package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.Pessoa;
import com.attornatus.javaluilio.repository.EnderecoRepository;
import com.attornatus.javaluilio.repository.PessoaRepository;

import java.util.List;

public class EnderecoForm {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;

    public Endereco criar(Long idPessoa, EnderecoRepository enderecoRepository, PessoaRepository pessoaRepository) {
        Pessoa pessoa = pessoaRepository.getReferenceById(idPessoa);
        if(this.principal) {
            List<Endereco> enderecos = enderecoRepository.findAllByPessoa_IdAndPrincipal(idPessoa, true);
            enderecos.forEach(enderecoItem -> {
                enderecoItem.setPrincipal(false);
            });
            enderecoRepository.saveAll(enderecos);
        }
        Endereco endereco = new Endereco(this.logradouro, this.cep, Integer.parseInt(this.numero), this.cidade, this.principal);
        endereco.setPessoa(pessoa);
        return endereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public Boolean getPrincipal() {
        return principal;
    }
}
