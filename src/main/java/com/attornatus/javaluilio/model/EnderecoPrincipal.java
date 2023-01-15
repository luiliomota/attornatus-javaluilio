package com.attornatus.javaluilio.model;

import jakarta.persistence.*;

@Entity
public class EnderecoPrincipal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Endereco endereco;

    public EnderecoPrincipal(Endereco endereco) {
        this.endereco = endereco;
    }

    public EnderecoPrincipal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
