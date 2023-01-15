package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Pessoa;
import com.attornatus.javaluilio.repository.PessoaRepository;

import java.time.LocalDate;

public class EditarPessoaForm {
    private String nome;
    private String dataNascimento;

    public Pessoa editar(PessoaRepository pessoaRepository, Long id) {
        Pessoa pessoa = pessoaRepository.getReferenceById(id);
        if(this.nome != null) pessoa.setNome(this.nome);
        if(this.dataNascimento != null) pessoa.setDataNascimento(LocalDate.parse(this.dataNascimento));
        return pessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }
}
