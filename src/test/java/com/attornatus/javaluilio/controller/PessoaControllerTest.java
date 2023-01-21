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
import java.net.URISyntaxException;
import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PessoaControllerTest {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void deveriaRetornarStatus201AoCriarUmaPessoa() throws Exception {
        URI uri = new URI("/api/pessoa/");
        String json = "{\"nome\":\"Fulano\",\"dataNascimento\":\"1900-01-01\"}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarAlteracaoDePessoa() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        URI uri = new URI("/api/pessoa/"+pessoa.getId());
        String json = "{}";

        mockMvc.perform(MockMvcRequestBuilders
                .put(uri)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarListaDePessoas() throws Exception {
        URI uri = new URI("/api/pessoa/");

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    public void deveriaRetornarStatus200AoRequisitarPessoaPorId() throws Exception {
        Pessoa pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        URI uri = new URI("/api/pessoa/"+pessoa.getId());

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }
}
