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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    @DisplayName("GET /api/livros deve retornar página de livros estruturada")
    void listarTodos_RetornaOk() throws Exception {
        // --- ARRANGE ---
        Livro livro = new Livro(new DadosCadastraLivro("Spring Boot em Ação", "Craig Walls"));
        Page<Livro> paginaMock = new PageImpl<>(List.of(livro));

        when(livroService.carregaLivros(any(Pageable.class))).thenReturn(paginaMock);

        // --- ACT) ---
        var resultado = mockMvc.perform(get("/api/livros")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON));

        // --- ASSERT ---
        resultado.andExpect(status().isOk())
                 .andExpect(jsonPath("$.content").isArray())
                 .andExpect(jsonPath("$.content[0].titulo").value("Spring Boot em Ação"))
                 .andExpect(jsonPath("$.totalElements").value(1));
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
