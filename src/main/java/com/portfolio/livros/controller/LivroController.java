package com.portfolio.livros.controller;

import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.model.Livro;
import com.portfolio.livros.service.LivroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping("formulario")
    public String carregaFormulario(Long id, Model model) {
        livroService.carregaFormulario(id, model);
        return "livros/formulario";
    }

    @GetMapping
    public String carregaLivros(Model model) {
        model.addAttribute("lista", livroService.carregaLivros());
        return "livros/lista";
    }


    @PostMapping
    public String cadastraLivro(@Valid @ModelAttribute DadosCadastraLivro dadosCadastraLivro, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("erro");
            return "livros/formulario";
        }
        var livro = new Livro(dadosCadastraLivro);
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
    public String editarLivro(@Valid @ModelAttribute DadosEditarLivro dadosEditarLivro, BindingResult result){
        if (result.hasErrors()) {
            System.out.println("erro");
            return "livros/formulario";
        }
        Livro livro = livroService.update(dadosEditarLivro);
        livro.atualizarLivro(dadosEditarLivro);

        return "redirect:/livros";
    }
}