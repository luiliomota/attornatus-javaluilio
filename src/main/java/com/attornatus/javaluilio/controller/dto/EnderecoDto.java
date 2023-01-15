package com.attornatus.javaluilio.controller.dto;

import com.attornatus.javaluilio.model.Endereco;
import org.springframework.data.domain.Page;

public class EnderecoDto {
    private Long id;
    private String logradouro;
    private String cep;
    private Integer numero;
    private String cidade;
    private Long idPessoa;

    public EnderecoDto(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.idPessoa = endereco.getPessoa().getId();
    }

    public static Page<EnderecoDto> converter(Page<Endereco> enderecos) {
        return enderecos.map(EnderecoDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }
}
