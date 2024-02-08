package com.portfolio.livros.controller;

import com.portfolio.livros.model.livro.DadosCadastraLivro;
import com.portfolio.livros.model.livro.DadosEditarLivro;
import com.portfolio.livros.model.livro.Livro;
import com.portfolio.livros.model.livro.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("formulario")
    public String carregaFormulario(Long id, Model model) {
        if (id != null) {
            var livro = repository.getReferenceById(id);
            model.addAttribute("livro", livro);
        }

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
    @Transactional
    public String deleteLivro(Long id){
        repository.deleteById(id);
        System.out.println("excluido");

        return "redirect:/livros";
    }

    @PutMapping
    @Transactional
    public String editarLivro(DadosEditarLivro dadosEditarLivro){
        var livro = repository.getReferenceById(dadosEditarLivro.id());
        livro.atualizarLivro(dadosEditarLivro);

        return "redirect:/livros";

    }

}
