import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Livro, PaginaLivros } from './livro.model';
import { LivroService } from './livro.service';

@Component({
  selector: 'app-lista-livros',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="page-heading">
      <div><p class="eyebrow">Catalogo</p><h1>Livros cadastrados</h1></div>
      <a class="button button-primary" routerLink="/formulario">Novo livro</a>
    </section>

    @if (loading) { <p class="state">Carregando catalogo...</p> }
    @if (errorMessage) { <p class="alert alert-error" role="alert">{{ errorMessage }}</p> }
    @if (!loading && !errorMessage && page && page.content.length === 0) {
      <div class="empty state"><h2>Nenhum livro ainda</h2><p>Comece cadastrando o primeiro item do catalogo.</p></div>
    }
    @if (!loading && page && page.content.length > 0) {
      <div class="table-wrap">
        <table>
          <thead><tr><th>Titulo</th><th>Autor</th><th class="actions">Acoes</th></tr></thead>
          <tbody>
            @for (livro of page.content; track livro.id) {
              <tr><td>{{ livro.titulo }}</td><td>{{ livro.autor }}</td><td class="actions">
                <a class="icon-link" [routerLink]="['/formulario', livro.id]">Editar</a>
                <button class="text-button danger" type="button" (click)="remove(livro)">Excluir</button>
              </td></tr>
            }
          </tbody>
        </table>
      </div>
      <div class="pagination"><span>Pagina {{ page.number + 1 }} de {{ page.totalPages }}</span>
        <div><button class="button button-quiet" type="button" (click)="load(page.number - 1)" [disabled]="page.number === 0">Anterior</button>
        <button class="button button-quiet" type="button" (click)="load(page.number + 1)" [disabled]="page.number + 1 >= page.totalPages">Proxima</button></div>
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