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

    //Teste para criar endereco para pessoa
    @Test
    public void deveRetornarStatus201ELogradouroDeEnderecoCriadoParaPessoaNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        String logradouro = new String("Rua um");
        URI uri = new URI("/api/endereco/pessoa/"+pessoa.getId());
        String json = "{\"logradouro\":\"" + logradouro + "\",\"cep\":\"77000000\",\"numero\":\"100\",\"cidade\":\"Brasilia\",\"principal\":\"true\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"logradouro\": \"" + logradouro + "\"}"));
    }

    //Teste para listar enderecos da pessoa
    @Test
    public void deveRetornarStatus200EConfirmacaoDeListaDaPessoaNaoVaziaNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        pessoaRepository.save(pessoa);
        enderecoRepository.save(endereco);
        URI uri = new URI("/api/endereco/pessoa/"+pessoa.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"empty\": false}"));
    }

    //Teste para informar qual endereço é o principal da pessoa
    @Test
    public void deveRetornarStatus200EIdDoEnderecoPrincipalInformadoNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);
        String json = "{\"principal\":\"true\"}";
        URI uri = new URI("/api/endereco/"+endereco.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\": " + endereco.getId() + "}"));
    }

    //Teste para listar todos enderecos cadastrados
    @Test
    public void deveRetornarStatus200EConfirmacaoDeListaNaoVaziaNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        Endereco endereco = new Endereco();
        endereco.setPessoa(pessoa);
        enderecoRepository.save(endereco);
        URI uri = new URI("/api/endereco/");

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"empty\": false}"));
    }

    //Teste para consultar endereco por id
    @Test
    public void deveRetornarStatus200EIdDoEnderecoConsultadoNoJsonDeResposta() throws Exception {
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
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\": " + endereco.getId() + "}"));
    }
}
