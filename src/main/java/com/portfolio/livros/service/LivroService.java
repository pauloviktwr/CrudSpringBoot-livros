package com.portfolio.livros.service;

import com.portfolio.livros.model.dto.DadosCadastraLivro;
import com.portfolio.livros.model.dto.DadosEditarLivro;
import com.portfolio.livros.model.Livro;
import com.portfolio.livros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public Page<Livro> carregaLivros(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Livro findById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Livro não encontrado: " + id));
    }

    @Transactional
    public Livro save(DadosCadastraLivro dadosCadastraLivro) {
        var livro = new Livro(dadosCadastraLivro);
        return repository.save(livro);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Livro update(DadosEditarLivro dadosEditarLivro) {
        var livro = repository.getReferenceById(dadosEditarLivro.id());
        livro.atualizarLivro(dadosEditarLivro);
        return repository.save(livro);
    }
}