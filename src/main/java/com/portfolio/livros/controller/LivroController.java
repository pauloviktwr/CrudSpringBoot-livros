package com.portfolio.livros.controller;

import com.portfolio.livros.model.Livro;
import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.service.LivroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("livros")
public class LivroController {

    private static final Logger logger = LoggerFactory.getLogger(LivroController.class);

    @Autowired
    private LivroService livroService;

    @GetMapping("formulario")
    public String carregaFormulario(Long id, Model model) {
        // Responsabilidade do controller: preparar dados para a view
        if (id != null) {
            var livro = livroService.findById(id);
            model.addAttribute("livro", livro);
        } else {
            model.addAttribute("livro", new Livro());
        }
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
            logger.warn("Erro na validação ao cadastrar livro: {}", result.getAllErrors());
            return "livros/formulario";
        }
        livroService.save(dadosCadastraLivro);
        logger.info("Livro cadastrado com sucesso: {}", dadosCadastraLivro.titulo());
        return "redirect:/livros";
    }

    @DeleteMapping
    public String deleteLivro(Long id){
        livroService.deleteById(id);
        logger.info("Livro com ID {} foi deletado com sucesso", id);
        return "redirect:/livros";
    }

    @PutMapping
    public String editarLivro(@Valid @ModelAttribute DadosEditarLivro dadosEditarLivro, BindingResult result){
        if (result.hasErrors()) {
            logger.warn("Erro na validação ao editar livro ID {}: {}", dadosEditarLivro.id(), result.getAllErrors());
            return "livros/formulario";
        }
        livroService.update(dadosEditarLivro);
        logger.info("Livro ID {} foi atualizado com sucesso", dadosEditarLivro.id());

        return "redirect:/livros";
    }
}