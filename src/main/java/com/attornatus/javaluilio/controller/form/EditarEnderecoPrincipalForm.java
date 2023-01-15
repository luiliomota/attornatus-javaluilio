package com.attornatus.javaluilio.controller.form;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.EnderecoPrincipal;
import com.attornatus.javaluilio.repository.EnderecoPrincipalRepository;
import com.attornatus.javaluilio.repository.EnderecoRepository;

public class EditarEnderecoPrincipalForm {
    private Long idEndereco;

    public EnderecoPrincipal editar(Long id, EnderecoPrincipalRepository enderecoPrincipalRepository, EnderecoRepository enderecoRepository){
        Endereco endereco = new Endereco();
        EnderecoPrincipal enderecoPrincipal = enderecoPrincipalRepository.getReferenceById(id);
        if(idEndereco != null && enderecoRepository.existsById(idEndereco)){
            endereco = enderecoRepository.getReferenceById(idEndereco);
            enderecoPrincipal.setEndereco(endereco);
            return enderecoPrincipal;
        }
        return enderecoPrincipal;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }
}
