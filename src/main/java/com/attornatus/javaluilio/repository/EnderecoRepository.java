package com.attornatus.javaluilio.repository;

import com.attornatus.javaluilio.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByPessoa_Id(Long idPessoa, Pageable paginacao);
    List<Endereco> findAllByPessoa_IdAndPrincipal(Long id, Boolean principal);
}
