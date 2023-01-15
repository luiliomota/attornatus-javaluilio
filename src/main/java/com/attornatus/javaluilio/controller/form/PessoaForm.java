package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Pessoa;

import java.time.LocalDate;

public class PessoaForm {
    private String nome;
    private String dataNascimento;

    public Pessoa criar (){
        Pessoa pessoa = new Pessoa(this.nome, LocalDate.parse(this.dataNascimento));
        return pessoa;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }


}
