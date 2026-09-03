import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Livro, PaginaLivros } from './livro.model';
import { LivroService } from './livro.service';

@Component({
  selector: 'app-lista-livros',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="d-flex flex-column flex-sm-row align-items-sm-end justify-content-between gap-3 mb-4">
      <div><p class="text-success text-uppercase fw-bold small mb-2">Catalogo</p><h1>Livros cadastrados</h1></div>
      <a class="btn btn-success" routerLink="/formulario">Novo livro</a>
    </section>

    @if (loading) { <p class="text-secondary text-center py-5">Carregando catalogo...</p> }
    @if (errorMessage) { <p class="alert alert-danger" role="alert">{{ errorMessage }}</p> }
    @if (!loading && !errorMessage && page && page.content.length === 0) {
      <div class="alert alert-light border text-center py-5"><h2>Nenhum livro ainda</h2><p>Comece cadastrando o primeiro item do catalogo.</p></div>
    }
    @if (!loading && page && page.content.length > 0) {
      <div class="table-responsive bg-white border">
        <table class="table table-hover mb-0 align-middle">
          <thead class="table-light"><tr><th>Titulo</th><th>Autor</th><th class="text-end">Acoes</th></tr></thead>
          <tbody>
            @for (livro of page.content; track livro.id) {
              <tr><td>{{ livro.titulo }}</td><td>{{ livro.autor }}</td><td class="text-end">
                <a class="btn btn-sm btn-outline-success me-2" [routerLink]="['/formulario', livro.id]">Editar</a>
                <button class="btn btn-sm btn-outline-danger" type="button" (click)="remove(livro)">Excluir</button>
              </td></tr>
            }
          </tbody>
        </table>
      </div>
      <div class="d-flex flex-column flex-sm-row align-items-sm-center justify-content-between gap-3 mt-3"><span class="text-secondary small">Pagina {{ page.number + 1 }} de {{ page.totalPages }}</span>
        <div><button class="btn btn-outline-secondary btn-sm me-2" type="button" (click)="load(page.number - 1)" [disabled]="page.number === 0">Anterior</button>
        <button class="btn btn-outline-secondary btn-sm" type="button" (click)="load(page.number + 1)" [disabled]="page.number + 1 >= page.totalPages">Proxima</button></div>
      </div>
    }
  `
})
export class ListaLivrosComponent implements OnInit {
  private readonly livroService = inject(LivroService);
  page?: PaginaLivros;
  loading = false;
  errorMessage = '';

  ngOnInit(): void { this.load(); }

  load(page = 0): void {
    this.loading = true;
    this.errorMessage = '';
    this.livroService.findAll(page).subscribe({
      next: (response) => { this.page = response; this.loading = false; },
      error: (error) => { this.errorMessage = this.livroService.messageFromError(error); this.loading = false; }
    });
  }

  remove(livro: Livro): void {
    if (!livro.id || !window.confirm(`Excluir "${livro.titulo}"?`)) return;
    this.loading = true;
    this.livroService.delete(livro.id).subscribe({
      next: () => this.load(this.page && this.page.content.length === 1 && this.page.number > 0 ? this.page.number - 1 : this.page?.number ?? 0),
      error: (error) => { this.errorMessage = this.livroService.messageFromError(error); this.loading = false; }
    });
  }
}