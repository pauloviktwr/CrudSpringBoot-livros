package com.portfolio.livros.controller;

import com.portfolio.livros.model.livro.DadosCadastraLivro;
import com.portfolio.livros.model.livro.Livro;
import com.portfolio.livros.model.livro.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("formulario")
    public String carregaFormulario() {
        return "livros/formulario";
    }

    @GetMapping
    public String carregaLivros(Model model) {
        model.addAttribute("lista", repository.findAll());
        return "livros/lista";
    }
    @PostMapping
    public String cadastraLivro(DadosCadastraLivro dadosCadastraLivro) {
        var livro = new Livro(dadosCadastraLivro);
        repository.save(livro);
        return "redirect:/livros";
    }

    @DeleteMapping
    public String deleteLivro(Long id){

        repository.deleteById(id);
        System.out.println("excluido");

        return "redirect:/livros";
    }

}
