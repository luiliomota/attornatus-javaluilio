package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.Pessoa;
import com.attornatus.javaluilio.repository.PessoaRepository;

public class EnderecoForm {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    public Endereco criar(Long id, PessoaRepository pessoaRepository){
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        Endereco endereco = new Endereco(this.logradouro, this.cep, Integer.parseInt(this.numero), this.cidade);
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

}
