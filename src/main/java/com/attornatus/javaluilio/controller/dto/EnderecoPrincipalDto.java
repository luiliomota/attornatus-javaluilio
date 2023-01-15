package com.attornatus.javaluilio.controller.dto;

import com.attornatus.javaluilio.model.EnderecoPrincipal;

public class EnderecoPrincipalDto {
    private Long id;
    private Long idEndereco;

    public EnderecoPrincipalDto(EnderecoPrincipal enderecoPrincipal) {
        this.id = enderecoPrincipal.getId();
        this.idEndereco = enderecoPrincipal.getEndereco().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }
}
