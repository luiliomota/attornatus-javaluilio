package com.attornatus.javaluilio.repository;

import com.attornatus.javaluilio.model.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Page<Endereco> findByPessoa_Id(Long idPessoa, Pageable paginacao);
}
