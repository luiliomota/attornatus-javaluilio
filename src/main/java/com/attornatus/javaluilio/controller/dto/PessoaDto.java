package com.attornatus.javaluilio.controller.dto;

import com.attornatus.javaluilio.model.Pessoa;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoaDto {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private List<EnderecoDto> enderecos = new ArrayList<>();

    public PessoaDto(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        if(pessoa.getEnderecos() != null)
           pessoa.getEnderecos().forEach(
                   endereco -> this.enderecos.add(new EnderecoDto(endereco))
           );
    }

    public static Page<PessoaDto> converter (Page<Pessoa> pessoas){
        return pessoas.map(PessoaDto::new);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<EnderecoDto> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<EnderecoDto> enderecos) {
        this.enderecos = enderecos;
    }
}
