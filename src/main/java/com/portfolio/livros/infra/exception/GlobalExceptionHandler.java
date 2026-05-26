package com.portfolio.livros.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura erros de validação do @Valid (ex: título ou autor em branco nos DTOs)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<DadosErroPadrao> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<DadosErroPadrao.DadosFieldError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new DadosErroPadrao.DadosFieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        DadosErroPadrao erro = new DadosErroPadrao(
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
    public ResponseEntity<DadosErroPadrao> handleResponseStatus(ResponseStatusException ex, HttpServletRequest request) {
        DadosErroPadrao erro = new DadosErroPadrao(
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
    public ResponseEntity<DadosErroPadrao> handleEntityNotFound(EntityNotFoundException ex, HttpServletRequest request) {
        DadosErroPadrao erro = new DadosErroPadrao(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso Não Encontrado",
                "O registro solicitado não foi encontrado no banco de dados.",
                request.getRequestURI(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // 4. Captura qualquer outra exceção inesperada (Erro 500 global)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<DadosErroPadrao> handleCatchAll(Exception ex, HttpServletRequest request) {
        DadosErroPadrao erro = new DadosErroPadrao(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno do Servidor",
                "Ocorreu um erro inesperado no sistema. Tente novamente mais tarde.",
                request.getRequestURI(),
                null
        );

        // Aqui vale a pena logar a exception original para você debugar depois
        // logger.error("Erro não esperado: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
}