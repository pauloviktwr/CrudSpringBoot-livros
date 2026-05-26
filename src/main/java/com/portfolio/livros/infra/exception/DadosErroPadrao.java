package com.portfolio.livros.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

public record DadosErroPadrao(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path,
    List<DadosFieldError> fieldErrors // Opcional: preenchido apenas em erros de validação (400)
) {
    // Sub-record para detalhar erros de validação de campos específicos
    public record DadosFieldError(String campo, String mensagem) {}
}