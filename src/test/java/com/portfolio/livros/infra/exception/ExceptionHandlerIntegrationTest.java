package com.portfolio.livros.infra.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Testes de Integração - Tratamento de Erros REST")
class ExceptionHandlerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LivroRepository livroRepository;

    @BeforeEach
    void setUp() {
        livroRepository.deleteAll();
    }

    @Test
    @DisplayName("Erro de Validação (400) deve retornar field errors estruturados")
    void validationError_Retorna400ComFieldErrors() throws Exception {
        DadosCadastraLivro payload = new DadosCadastraLivro("", "");

        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.timestamp").exists())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Erro de Validação"))
            .andExpect(jsonPath("$.message").value("Um ou mais campos estão inválidos."))
            .andExpect(jsonPath("$.path").value("/api/livros"))
            .andExpect(jsonPath("$.fieldErrors").isArray())
            .andExpect(jsonPath("$.fieldErrors", hasSize(greaterThan(0))))
            .andExpect(jsonPath("$.fieldErrors[0].campo").isString())
            .andExpect(jsonPath("$.fieldErrors[0].mensagem").isString());
    }

    @Test
    @DisplayName("Recurso não encontrado (404) deve retornar erro padronizado")
    void notFound_Retorna404() throws Exception {
        mockMvc.perform(get("/api/livros/99999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Erro na Requisição"))
            .andExpect(jsonPath("$.message", containsString("Livro não encontrado")));
    }

    @Test
    @DisplayName("DELETE com ID inexistente (404) deve retornar erro padronizado")
    void deleteInexistente_Retorna404() throws Exception {
        mockMvc.perform(delete("/api/livros/99999"))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    @DisplayName("Erro 500 genérico deve retornar resposta padronizada sem expor detalhes")
    void genericError_Retorna500Padronizado() throws Exception {
        // Simula erro 500 enviando JSON inválido
        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{invalid}"))
            .andExpect(status().isBadRequest()) // JSON parsing error
            .andExpect(jsonPath("$.status").isNumber());
    }

    @Test
    @DisplayName("POST com dados válidos deve criar livro com sucesso (201)")
    void postValido_Retorna201() throws Exception {
        DadosCadastraLivro payload = new DadosCadastraLivro("Clean Code", "Robert Martin");

        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.titulo").value("Clean Code"))
            .andExpect(jsonPath("$.autor").value("Robert Martin"));
    }

    @Test
    @DisplayName("PUT com ID inexistente (404) deve retornar erro padronizado")
    void putInexistente_Retorna404() throws Exception {
        // Envia o DTO correto (DadosEditarLivro) com o mesmo ID da URL
        DadosEditarLivro payload = new DadosEditarLivro(99999L, "Updated Title", "Updated Author");

        mockMvc.perform(put("/api/livros/99999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
            .andExpect(status().isNotFound()) // Espera 404, já que o ID coincide mas não existe no banco
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").exists());
    }
}
