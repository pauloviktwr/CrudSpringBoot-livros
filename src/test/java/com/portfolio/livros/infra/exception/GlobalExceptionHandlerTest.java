package com.portfolio.livros.infra.exception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Testes de Integração - GlobalExceptionHandler")
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("POST com validação inválida deve retornar 400 com field errors")
    void postComValidacaoInvalida_Retorna400ComFieldErrors() throws Exception {
        String payload = """
            {
                "titulo": "",
                "autor": ""
            }
        """;

        mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.error").value("Erro de Validação"))
            .andExpect(jsonPath("$.fieldErrors").isArray())
            .andExpect(jsonPath("$.fieldErrors[0].campo").exists())
            .andExpect(jsonPath("$.fieldErrors[0].mensagem").exists());
    }

    @Test
    @DisplayName("GET com ID de livro inexistente deve retornar 404")
    void getComIdInexistente_Retorna404() throws Exception {
        mockMvc.perform(get("/api/livros/99999")) // Alterado para GET
            .andExpect(status().isNotFound())     // Valida diretamente o 404
            .andExpect(jsonPath("$.status").value(404))
            .andExpect(jsonPath("$.error").value("Erro na Requisição"));
    }

@Test
    @DisplayName("POST com JSON malformado (Regra 5) deve retornar 400 Bad Request padronizado")
    void handleHttpMessageNotReadable_Retorna400ComJsonMalformado() throws Exception {
        // --- ACT (Executa enviando um JSON quebrado) ---
        var resultado = mockMvc.perform(post("/api/livros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{invalid json")); // <-- Isso faz o Spring lançar HttpMessageNotReadableException

        // --- ASSERT (Valida se a Regra 5 respondeu com o formato correto) ---
        resultado.andExpect(status().isBadRequest()) // Valida status 400
                 .andExpect(jsonPath("$.status").value(400))
                 .andExpect(jsonPath("$.error").value("Requisição Inválida")) // Bate com o texto da Regra 5
                 .andExpect(jsonPath("$.message").exists()) // Contém o detalhe técnico do erro de parsing
                 .andExpect(jsonPath("$.path").value("/api/livros"));
    }
}
