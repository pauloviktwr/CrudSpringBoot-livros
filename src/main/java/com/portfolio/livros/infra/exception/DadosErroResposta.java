package com.portfolio.livros.infra.exception;

import java.time.LocalDateTime;
import java.util.List;

public record DadosErroResposta(
    LocalDateTime timestamp,
    int status,
    String error,
    String message,
    String path,
    List<DadosFieldError> fieldErrors
) {
    public record DadosFieldError(String campo, String mensagem) {}
}
