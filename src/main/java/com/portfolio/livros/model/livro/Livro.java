package com.portfolio.livros.model.livro;

public class Livro {
    private String titulo;
    private String autor;

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
}
