package com.portfolio.livros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro")

public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;

    public Long getId() {
        return id;
    }

    public Livro() {
    }

    public Livro(DadosCadastraLivro dadosCadastraLivro) {
        this.titulo = dadosCadastraLivro.titulo();
        this.autor = dadosCadastraLivro.autor();
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void atualizarLivro(DadosEditarLivro dadosEditarLivro) {
        this.autor = dadosEditarLivro.autor();
        this.titulo = dadosEditarLivro.titulo();
    }
}
