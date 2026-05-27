package com.portfolio.livros.controller;

import com.portfolio.livros.model.Livro;
import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.model.dto.DadosLivroResposta;
import com.portfolio.livros.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
public class LivroRestController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<DadosLivroResposta>> listarTodos() {
        List<DadosLivroResposta> resposta = livroService.carregaLivros().stream()
                .map(livro -> new DadosLivroResposta(livro.getId(), livro.getTitulo(), livro.getAutor()))
                .toList();
        return ResponseEntity.ok(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosLivroResposta> buscarPorId(@PathVariable Long id) {
        Livro livro = livroService.findById(id);

        // Mapeia a Entidade para o DTO de Resposta
        DadosLivroResposta resposta = new DadosLivroResposta(livro.getId(), livro.getTitulo(), livro.getAutor());
        return ResponseEntity.ok(resposta);
    }

    @PostMapping
    public ResponseEntity<Livro> criar(@Valid @RequestBody DadosCadastraLivro dadosCadastraLivro) {
        Livro livroSalvo = livroService.save(dadosCadastraLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizar(@PathVariable Long id,
                                           @Valid @RequestBody DadosEditarLivro dadosEditarLivro) {
        if (!id.equals(dadosEditarLivro.id())) {
            return ResponseEntity.badRequest().build();
        }
        Livro livroAtualizado = livroService.update(dadosEditarLivro);
        return ResponseEntity.ok(livroAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        livroService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
