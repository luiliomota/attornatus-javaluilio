package com.attornatus.javaluilio.controller;

import com.attornatus.javaluilio.controller.dto.EnderecoPrincipalDto;
import com.attornatus.javaluilio.controller.form.EditarEnderecoPrincipalForm;
import com.attornatus.javaluilio.controller.form.EnderecoPrincipalForm;
import com.attornatus.javaluilio.model.EnderecoPrincipal;
import com.attornatus.javaluilio.repository.EnderecoPrincipalRepository;
import com.attornatus.javaluilio.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/enderecoPrincipal/")
public class EnderecoPrincipalController {
    @Autowired
    private EnderecoPrincipalRepository enderecoPrincipalRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    //Definir endereço principal
    @PostMapping
    public ResponseEntity<EnderecoPrincipalDto> definirEnderecoPrincipal(
            @RequestBody EnderecoPrincipalForm form, UriComponentsBuilder uriComponentsBuilder){
        if(enderecoPrincipalRepository.count() == 0){
            EnderecoPrincipal enderecoPrincipal = form.definir(enderecoPrincipalRepository, enderecoRepository);
            enderecoPrincipalRepository.save(enderecoPrincipal);
            URI uri = uriComponentsBuilder.path("/api/enderecoPrincipal/{id}").buildAndExpand(enderecoPrincipal.getId()).toUri();
            return ResponseEntity.created(uri).body(new EnderecoPrincipalDto(enderecoPrincipal));
        }
        return ResponseEntity.notFound().build();
    }

    //Consultar endereço principal via id
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoPrincipalDto> mostraEnderecoPrincipal(@PathVariable Long id){
        if(enderecoPrincipalRepository.existsById(id)){
            EnderecoPrincipal enderecoPrincipal = enderecoPrincipalRepository.getReferenceById(id);
            return ResponseEntity.ok(new EnderecoPrincipalDto(enderecoPrincipal));
        }
        return ResponseEntity.notFound().build();
    }

    //Editar endereco principal
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EnderecoPrincipalDto> editarEnderecoPrincipal(@PathVariable Long id, @RequestBody EditarEnderecoPrincipalForm form){
        if(enderecoPrincipalRepository.existsById(id)){
            EnderecoPrincipal enderecoPrincipal = form.editar(id, enderecoPrincipalRepository, enderecoRepository);
            return ResponseEntity.ok(new EnderecoPrincipalDto(enderecoPrincipal));
        }
        return ResponseEntity.notFound().build();
    }
}
