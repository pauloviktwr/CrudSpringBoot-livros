package com.portfolio.livros.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DadosCadastraLivro(
       @NotBlank(message = "Titulo obrigatorio") @Size(min = 2, max = 100) String titulo,
       @NotBlank(message = "Autor obrigatorio") @Size(min = 2, max = 100) String autor) {
}