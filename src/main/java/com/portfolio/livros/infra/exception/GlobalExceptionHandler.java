package com.portfolio.livros.infra.exception;

import com.portfolio.livros.infra.exception.DadosErroResposta;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 1. Captura erros de validação do @Valid (ex: título ou autor em branco nos DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DadosErroResposta> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<DadosErroResposta.DadosFieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new DadosErroResposta.DadosFieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        DadosErroResposta erro = new DadosErroResposta(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação",
                "Um ou mais campos estão inválidos.",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.badRequest().body(erro);
    }

    // 2. Captura o ResponseStatusException (presente no findById do Service)
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<DadosErroResposta> handleResponseStatus(ResponseStatusException ex, HttpServletRequest request) {
        DadosErroResposta erro = new DadosErroResposta(
                LocalDateTime.now(),
                ex.getStatusCode().value(),
                "Erro na Requisição",
                ex.getReason(),
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(ex.getStatusCode()).body(erro);
    }

    // 3. Captura EntityNotFoundException (do JPA / getReferenceById presente no update do Service)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<DadosErroResposta> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        DadosErroResposta erro = new DadosErroResposta(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                "O registro solicitado não foi encontrado no banco de dados.",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 4. Captura violações de constraint de integridade (ex: título duplicado, unique constraint)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<DadosErroResposta> handleDataIntegrityViolation(DataIntegrityViolationException ex, HttpServletRequest request) {
        String message = "Falha ao processar a operação devido a conflito de dados.";
        
        // Verificar se é erro de unique constraint
        if (ex.getCause() != null && ex.getCause().getMessage() != null) {
            if (ex.getCause().getMessage().contains("Duplicate entry") || 
                ex.getCause().getMessage().contains("duplicate key")) {
                message = "Falha: Um registro com esses dados já existe no sistema.";
            }
        }
        
        logger.warn("Violação de integridade de dados: {}", ex.getMessage());
        
        DadosErroResposta erro = new DadosErroResposta(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflito de Dados",
                message,
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    // 5. Captura qualquer outra exceção inesperada (Erro 500 global)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosErroResposta> handleCatchAll(Exception ex, HttpServletRequest request) {
        logger.error("Erro não esperado no servidor: {} - {}", ex.getClass().getSimpleName(), ex.getMessage(), ex);
        
        DadosErroResposta erro = new DadosErroResposta(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde.",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}