package com.portfolio.livros.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.livros.model.Livro;
import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.service.LivroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LivroRestController.class)
@DisplayName("Testes REST - LivroRestController")
class LivroRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LivroService livroService;

    @Test
    @DisplayName("GET /api/livros deve retornar lista de livros")
    void listarTodos_RetornaOk() throws Exception {
        Livro livro = new Livro(new DadosCadastraLivro("Spring Boot em Ação", "Craig Walls"));
        when(livroService.carregaLivros()).thenReturn(List.of(livro));

        mockMvc.perform(get("/api/livros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].titulo").value("Spring Boot em Ação"))
                .andExpect(jsonPath("$[0].temp").doesNotExist());
    }

    @Test
    @DisplayName("GET /api/livros/{id} deve retornar livro existente")
    void buscarPorId_RetornaOk() throws Exception {
        Livro livro = new Livro(new DadosCadastraLivro("Spring Boot em Ação", "Craig Walls"));
        when(livroService.findById(anyLong())).thenReturn(livro);

        mockMvc.perform(get("/api/livros/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Spring Boot em Ação"))
                .andExpect(jsonPath("$.temp").doesNotExist());
    }

    @Test
    @DisplayName("POST /api/livros deve criar novo livro")
    void criar_RetornaCreated() throws Exception {
        DadosCadastraLivro payload = new DadosCadastraLivro("Spring Boot em Ação", "Craig Walls");
        Livro livro = new Livro(payload);
        when(livroService.save(any(DadosCadastraLivro.class))).thenReturn(livro);

        mockMvc.perform(post("/api/livros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Spring Boot em Ação"));
    }

    @Test
    @DisplayName("PUT /api/livros/{id} deve atualizar livro existente")
    void atualizar_RetornaOk() throws Exception {
        DadosEditarLivro payload = new DadosEditarLivro(1L, "Spring Boot Avançado", "Craig Walls Atualizado");
        Livro livro = new Livro(new DadosCadastraLivro(payload.titulo(), payload.autor()));
        when(livroService.update(any(DadosEditarLivro.class))).thenReturn(livro);

        mockMvc.perform(put("/api/livros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(payload)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Spring Boot Avançado"));
    }

    @Test
    @DisplayName("DELETE /api/livros/{id} deve retornar no content")
    void excluir_RetornaNoContent() throws Exception {
        Mockito.doNothing().when(livroService).deleteById(anyLong());

        mockMvc.perform(delete("/api/livros/1"))
                .andExpect(status().isNoContent());
    }
}
