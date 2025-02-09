package com.portfolio.livros.service;

import com.portfolio.livros.model.DadosCadastraLivro;
import com.portfolio.livros.model.DadosEditarLivro;
import com.portfolio.livros.model.Livro;
import com.portfolio.livros.model.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository repository;

    public List<Livro> findAll() {
        return repository.findAll();
    }

    public Livro findById(Long id) {
        return repository.getReferenceById(id);
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
        return livro;
    }
}