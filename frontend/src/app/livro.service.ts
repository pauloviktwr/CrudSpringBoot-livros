import { inject, Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { ErroApi, Livro, PaginaLivros } from './livro.model';

@Injectable({ providedIn: 'root' })
export class LivroService {
  private readonly http = inject(HttpClient);
  private readonly endpoint = '/api/livros';

  findAll(page = 0, size = 10): Observable<PaginaLivros> {
    const params = new HttpParams().set('page', page).set('size', size).set('sort', 'titulo');
    return this.http.get<PaginaLivros>(this.endpoint, { params });
  }

  findById(id: number): Observable<Livro> {
    return this.http.get<Livro>(`${this.endpoint}/${id}`);
  }

  create(livro: Livro): Observable<Livro> {
    return this.http.post<Livro>(this.endpoint, livro);
  }

  update(id: number, livro: Livro): Observable<Livro> {
    return this.http.put<Livro>(`${this.endpoint}/${id}`, { ...livro, id });
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.endpoint}/${id}`);
  }

  messageFromError(error: unknown): string {
    const response = error as HttpErrorResponse;
    const body = response.error as ErroApi | undefined;
    return body?.message ?? 'Nao foi possivel concluir a operacao. Tente novamente.';
  }
}