package com.attornatus.javaluilio.controller;

import com.attornatus.javaluilio.controller.dto.PessoaDto;
import com.attornatus.javaluilio.controller.form.EditarPessoaForm;
import com.attornatus.javaluilio.controller.form.PessoaForm;
import com.attornatus.javaluilio.model.Pessoa;
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
@RequestMapping("/api/pessoa/")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;

    //Criar uma pessoa
    @PostMapping
    public ResponseEntity<PessoaDto> criarPessoa(@RequestBody PessoaForm form, UriComponentsBuilder uriComponentsBuilder) {
        Pessoa pessoa = form.criar();
        pessoaRepository.save(pessoa);
        URI uri = uriComponentsBuilder.path("/api/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
        return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
    }

    //Editar pessoa
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PessoaDto> editarPessoa(@RequestBody EditarPessoaForm form, @PathVariable Long id) {
        if(pessoaRepository.existsById(id)) {
            Pessoa pessoa = form.editar(pessoaRepository, id);
            return ResponseEntity.ok().body(new PessoaDto(pessoa));
        }
        return ResponseEntity.notFound().build();
    }

    //Listar pessoas
    @GetMapping
    public Page<PessoaDto> listarPessoas(Pageable paginacao) {
        Page<Pessoa> pessoas = pessoaRepository.findAll(paginacao);
        return PessoaDto.converter(pessoas);
    }

    //Consultar pessoa por id
    @GetMapping("/{id}")
    public ResponseEntity<PessoaDto> consultarPessoa(@PathVariable Long id) {
        if(pessoaRepository.existsById(id)) {
            Pessoa pessoa = pessoaRepository.getReferenceById(id);
            return ResponseEntity.ok(new PessoaDto(pessoa));
        }
        return ResponseEntity.notFound().build();
    }
}
