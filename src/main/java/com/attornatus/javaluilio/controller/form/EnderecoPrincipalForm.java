package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.EnderecoPrincipal;
import com.attornatus.javaluilio.repository.EnderecoPrincipalRepository;
import com.attornatus.javaluilio.repository.EnderecoRepository;

public class EnderecoPrincipalForm {
    private Long idEndereco;

    public EnderecoPrincipal definir(EnderecoPrincipalRepository enderecoPrincipalRepository, EnderecoRepository enderecoRepository) {
        Endereco endereco = enderecoRepository.getReferenceById(idEndereco);
        EnderecoPrincipal enderecoPrincipal = new EnderecoPrincipal(endereco);
        return enderecoPrincipal;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }
}
