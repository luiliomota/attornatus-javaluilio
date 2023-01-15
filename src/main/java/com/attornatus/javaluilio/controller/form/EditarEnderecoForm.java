package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.repository.EnderecoRepository;

public class EditarEnderecoForm {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;

    public Endereco editar (Long id, EnderecoRepository enderecoRepository){
        Endereco endereco = enderecoRepository.getReferenceById(id);
        if(this.logradouro != null) endereco.setLogradouro(this.logradouro);
        if(this.cep != null) endereco.setCep(this.cep);
        if(this.numero != null) endereco.setNumero(Integer.parseInt(this.numero));
        if(this.cidade != null) endereco.setCidade(this.cidade);
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
