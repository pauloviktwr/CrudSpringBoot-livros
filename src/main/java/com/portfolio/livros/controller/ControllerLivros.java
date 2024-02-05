package com.portfolio.livros.controller;

import com.portfolio.livros.model.livro.DadosCadastraFilme;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("livros")
public class ControllerLivros {

    @GetMapping
    public String carregaLivros() {
        return "/livros/lista";
    }

    @GetMapping("formulario")
    public String carregaFormulario() {
        return "/livros/formulario";
    }
    @PostMapping("formulario")
    public String cadastraLivro(DadosCadastraFilme dadosCadastraFilme) {
        System.out.println(dadosCadastraFilme);
        return "/livros/formulario";
    }
}
