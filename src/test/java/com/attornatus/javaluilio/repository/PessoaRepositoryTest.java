package com.attornatus.javaluilio.repository;

import com.attornatus.javaluilio.model.Pessoa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    //Teste de persistencia em banco de dados
    @Test
    public void deveCarregarListaDePessoasCadastradas(){
        LocalDate dataNascimento = LocalDate.parse("1900-01-01");
        Pessoa pessoa1 = new Pessoa("Fulano1", dataNascimento);
        Pessoa pessoa2 = new Pessoa("Fulano2", dataNascimento);
        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        List<Pessoa> pessoas = pessoaRepository.findAll();

        assertNotNull(pessoas);
    }
}
