package com.attornatus.javaluilio.controller;

import com.attornatus.javaluilio.model.Pessoa;
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
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MockMvc mockMvc;

    //Teste para criar uma pessoa
    @Test
    public void deveRetornarStatus201ENomeDaPessoaNoJsonDeResposta() throws Exception {
        URI uri = new URI("/api/pessoa/");
        String nome = new String("Fulano");
        String json = "{\"nome\":\""+nome+"\",\"dataNascimento\":\"1900-01-01\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"nome\":\"" + nome + "\"}"));
    }

    //Teste para editar uma pessoa
    @Test
    public void deveRetornarStatus200ENomeAlteradoNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        String nome = new String("Fulano");
        URI uri = new URI("/api/pessoa/"+pessoa.getId());
        String json = "{\"nome\":\""+nome+"\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"nome\":\"" + nome + "\"}"));

    }

    //Teste para listar pessoas
    @Test
    public void deveRetornarStatus200EConfirmarQueListaNaoEstaVaziaNoJsonDeResposta() throws Exception {
        URI uri = new URI("/api/pessoa/");
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"empty\":false}"));
    }

    //Teste para consultar uma pessoa
    @Test
    public void deveRetornarStatus200EIdDaPessoaNoJsonDeResposta() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        URI uri = new URI("/api/pessoa/"+pessoa.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\": " + pessoa.getId() + "}"));
    }
}
