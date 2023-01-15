package com.attornatus.javaluilio.repository;

import com.attornatus.javaluilio.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
