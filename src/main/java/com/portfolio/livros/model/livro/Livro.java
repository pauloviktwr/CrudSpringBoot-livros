package com.portfolio.livros.model.livro;

public class Livro {
    public String titulo;
    public String autor;

    public Livro(DadosCadastraFilme dadosCadastraFilme) {
        this.titulo = dadosCadastraFilme.titulo();
        this.autor = dadosCadastraFilme.autor();
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
}