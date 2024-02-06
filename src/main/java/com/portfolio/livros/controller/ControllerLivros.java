package com.portfolio.livros.controller;

import com.portfolio.livros.model.livro.DadosCadastraLivro;
import com.portfolio.livros.model.livro.Livro;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("livros")
public class ControllerLivros {
    private List<Livro> livros = new ArrayList<>();

    @GetMapping("formulario")
    public String carregaFormulario() {
        return "livros/formulario";
    }

    @GetMapping
    public String carregaLivros(Model model) {
        model.addAttribute("lista", livros);

        return "livros/lista";
    }
    @PostMapping
    public String cadastraLivro(DadosCadastraLivro dadosCadastraLivro) {
        var livro = new Livro(dadosCadastraLivro);
        livros.add(livro);

        return "redirect:/livros";
    }
}
