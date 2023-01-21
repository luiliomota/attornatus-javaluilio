package com.attornatus.javaluilio.controller;

import com.attornatus.javaluilio.model.Endereco;
import com.attornatus.javaluilio.model.Pessoa;
import com.attornatus.javaluilio.repository.EnderecoRepository;
import com.attornatus.javaluilio.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class EnderecoControllerTest {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveriaRetornar201AoCriarNovoEnderecoParaUmIdDePessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        URI uri = new URI("/api/endereco/pessoa/"+pessoa.getId());
        String json = "{\"logradouro\":\"Rua Um\",\"cep\":\"77000000\",\"numero\":\"100\",\"cidade\":\"Brasilia\",\"principal\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarListaDeEnderecosPorIdDePessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        URI uri = new URI("/api/endereco/pessoa/"+pessoa.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarListaDeEnderecos() throws Exception {
        URI uri = new URI("/api/endereco/");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarAlteracaoDeAtributosDeEndereco() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);
        String json = "{}";
        System.out.println(endereco.getId());
        URI uri = new URI("/api/endereco/"+endereco.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarEnderecoPorId() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);
        URI uri = new URI("/api/endereco/"+endereco.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
}
