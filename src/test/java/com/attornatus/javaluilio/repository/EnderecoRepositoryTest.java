package com.attornatus.javaluilio.repository;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.Pessoa;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EnderecoRepositoryTest {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Test
    public void deveCarregarListaDeEnderecosCadastrados() {
        Endereco endereco1 = new Endereco("Rua um", "99999999", 1, "Brasilia", true);
        Endereco endereco2 = new Endereco("Rua dois", "99999998", 1, "Brasilia", false);
        enderecoRepository.save(endereco1);
        enderecoRepository.save(endereco2);
        List<Endereco> enderecos = enderecoRepository.findAll();

        assertNotNull(enderecos);
    }

    @Test
    public void deveCarregarListaDeEnderecosCadastradosPorPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco endereco1 = new Endereco();
        Endereco endereco2 = new Endereco();
        endereco1.setPessoa(pessoa);
        endereco2.setPessoa(pessoa);
        enderecoRepository.save(endereco1);
        enderecoRepository.save(endereco2);
        Page<Endereco> enderecos = enderecoRepository.findByPessoa_Id(pessoa.getId(), Pageable.ofSize(20));

        assertNotNull(enderecos);
    }

    @Test
    public void deveCarregarListaDeEnderecosPrincipaisPorPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco enderecoPrincipal = new Endereco("Rua um","9999999",1,"Brasilia",true);
        Endereco enderecoNaoPrincipal1 = new Endereco("Rua dois","9999999",1,"Brasilia",false);
        Endereco enderecoNaoPrincipal2 = new Endereco("Rua tres","9999999",1,"Brasilia",false);
        enderecoPrincipal.setPessoa(pessoa);
        enderecoNaoPrincipal1.setPessoa(pessoa);
        enderecoNaoPrincipal2.setPessoa(pessoa);
        enderecoRepository.save(enderecoPrincipal);
        enderecoRepository.save(enderecoNaoPrincipal1);
        enderecoRepository.save(enderecoNaoPrincipal2);
        List<Endereco> enderecosPrincipais = enderecoRepository.findAllByPessoa_IdAndPrincipal(pessoa.getId(),true);

        assertNotNull(enderecosPrincipais);
    }

    //Teste de persistencia em banco de dados
    @Test
    public void deveCarregarListaDeEnderecosNaoPrincipaisPorPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco enderecoPrincipal = new Endereco("Rua um","9999999",1,"Brasilia",true);
        Endereco enderecoNaoPrincipal1 = new Endereco("Rua dois","9999999",1,"Brasilia",false);
        Endereco enderecoNaoPrincipal2 = new Endereco("Rua tres","9999999",1,"Brasilia",false);
        enderecoPrincipal.setPessoa(pessoa);
        enderecoNaoPrincipal1.setPessoa(pessoa);
        enderecoNaoPrincipal2.setPessoa(pessoa);
        enderecoRepository.save(enderecoPrincipal);
        enderecoRepository.save(enderecoNaoPrincipal1);
        enderecoRepository.save(enderecoNaoPrincipal2);
        List<Endereco> enderecosNaoPrincipais = enderecoRepository.findAllByPessoa_IdAndPrincipal(pessoa.getId(),false);

        assertNotNull(enderecosNaoPrincipais);
    }
}
