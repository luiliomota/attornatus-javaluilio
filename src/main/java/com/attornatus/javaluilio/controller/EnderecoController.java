package com.attornatus.javaluilio.controller;

import com.attornatus.javaluilio.controller.dto.EnderecoDto;
import com.attornatus.javaluilio.controller.form.EditarEnderecoForm;
import com.attornatus.javaluilio.controller.form.EnderecoForm;
import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.repository.EnderecoRepository;
import com.attornatus.javaluilio.repository.PessoaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/endereco/")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    //Criar endereco para uma pessoa
    @PostMapping("/pessoa/{idPessoa}")
    public ResponseEntity<EnderecoDto> criarEndereco (@PathVariable Long idPessoa, @RequestBody EnderecoForm form, UriComponentsBuilder uriComponentsBuilder){
        if(pessoaRepository.existsById(idPessoa)){
            Endereco endereco = form.criar(idPessoa, pessoaRepository);
            enderecoRepository.save(endereco);
            URI uri = uriComponentsBuilder.path("/api/endereco/{id}").buildAndExpand(endereco.getId()).toUri();
            return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
        }
        return ResponseEntity.notFound().build();
    }

    //Listar enderecos por pessoa
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<Page<EnderecoDto>> listarEnderecosPorPessoa (@PathVariable Long idPessoa, Pageable paginacao){
        if(pessoaRepository.existsById(idPessoa)){
            Page<Endereco> enderecos = enderecoRepository.findByPessoa_Id(idPessoa, paginacao);
            return ResponseEntity.ok().body(EnderecoDto.converter(enderecos));
        }
        return ResponseEntity.notFound().build();
    }

    //Listar todos enderecos
    @GetMapping
    public Page<EnderecoDto> listarTodosEnderecos(Pageable paginacao){
        Page<Endereco> enderecos = enderecoRepository.findAll(paginacao);
        return EnderecoDto.converter(enderecos);
    }

    //Editar endereco
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<EnderecoDto> editarEndereco(@PathVariable Long id, @RequestBody EditarEnderecoForm form){
        if(enderecoRepository.existsById(id)){
            Endereco endereco = form.editar(id, enderecoRepository);
            return ResponseEntity.ok(new EnderecoDto(endereco));
        }
        return ResponseEntity.notFound().build();
    }

    //Consultar endereco por id
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> consultarEndereco (@PathVariable Long id){
        if(enderecoRepository.existsById(id)){
            Endereco endereco = enderecoRepository.getReferenceById(id);
            return ResponseEntity.ok(new EnderecoDto(endereco));
        }
        return ResponseEntity.notFound().build();
    }
}
