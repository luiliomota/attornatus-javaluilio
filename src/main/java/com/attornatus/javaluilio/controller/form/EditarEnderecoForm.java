package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.repository.EnderecoRepository;

import java.util.List;

public class EditarEnderecoForm {
    private String logradouro;
    private String cep;
    private String numero;
    private String cidade;
    private Boolean principal;

    public Endereco editar(Long id, EnderecoRepository enderecoRepository) {
        Endereco endereco = enderecoRepository.getReferenceById(id);
        if(this.logradouro != null) {
            endereco.setLogradouro(this.logradouro);
        }
        if(this.cep != null) {
            endereco.setCep(this.cep);
        }
        if(this.numero != null) {
            endereco.setNumero(Integer.parseInt(this.numero));
        }
        if(this.cidade != null) {
            endereco.setCidade(this.cidade);
        }
        if(this.principal != null) {
            if(this.principal) {
                Long idPessoa = endereco.getPessoa().getId();
                List<Endereco> enderecos = enderecoRepository.findAllByPessoa_IdAndPrincipal(idPessoa, true);
                enderecos.forEach(enderecoItem -> {
                    enderecoItem.setPrincipal(false);
                } );
                enderecoRepository.saveAll(enderecos);
            }
            endereco.setPrincipal(this.principal);
        }
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
