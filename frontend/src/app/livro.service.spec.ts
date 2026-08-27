import { TestBed } from '@angular/core/testing';
import { provideHttpClient } from '@angular/common/http';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { LivroService } from './livro.service';

describe('LivroService', () => {
  let service: LivroService;
  let http: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LivroService, provideHttpClient(), provideHttpClientTesting()]
    });
    service = TestBed.inject(LivroService);
    http = TestBed.inject(HttpTestingController);
  });

  afterEach(() => http.verify());

  it('deve buscar a pagina de livros com ordenacao por titulo', () => {
    service.findAll(1, 5).subscribe((page) => {
      expect(page.content).toHaveLength(1);
      expect(page.content[0].titulo).toBe('Clean Code');
    });

    const request = http.expectOne((request) => request.url === '/api/livros');
    expect(request.request.method).toBe('GET');
    expect(request.request.params.get('page')).toBe('1');
    expect(request.request.params.get('size')).toBe('5');
    expect(request.request.params.get('sort')).toBe('titulo');
    request.flush({ content: [{ id: 1, titulo: 'Clean Code', autor: 'Robert C. Martin' }], totalElements: 1, totalPages: 1, number: 0 });
  });

  it('deve enviar um novo livro no cadastro', () => {
    const livro = { titulo: 'Dom Casmurro', autor: 'Machado de Assis' };
    service.create(livro).subscribe((response) => expect(response.titulo).toBe(livro.titulo));

    const request = http.expectOne('/api/livros');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual(livro);
    request.flush({ id: 2, ...livro });
  });
});
