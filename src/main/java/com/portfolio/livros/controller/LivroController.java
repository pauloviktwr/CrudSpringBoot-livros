package com.portfolio.livros.controller;

import com.portfolio.livros.model.DadosCadastraLivro;
import com.portfolio.livros.model.DadosEditarLivro;
import com.portfolio.livros.service.LivroService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("formulario")
    public String carregaFormulario(Long id, Model model) {
        if (id != null) {
            var livro = livroService.findById(id);
            model.addAttribute("livro", livro);
        }

        return "livros/formulario";
    }

    @GetMapping
    public String carregaLivros(Model model) {
        model.addAttribute("lista", livroService.findAll());
        return "livros/lista";
    }

    @PostMapping
    public String cadastraLivro(DadosCadastraLivro dadosCadastraLivro) {
        livroService.save(dadosCadastraLivro);
        return "redirect:/livros";
    }

    @DeleteMapping
    @Transactional
    public String deleteLivro(Long id){
        livroService.deleteById(id);
        System.out.println("excluido");

        return "redirect:/livros";
    }

    @PutMapping
    @Transactional
    public String editarLivro(DadosEditarLivro dadosEditarLivro){
        livroService.update(dadosEditarLivro);
        return "redirect:/livros";
    }
}