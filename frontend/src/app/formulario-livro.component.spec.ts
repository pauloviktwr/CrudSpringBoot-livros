import { provideHttpClient } from '@angular/common/http';
import { provideHttpClientTesting, HttpTestingController } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { FormularioLivroComponent } from './formulario-livro.component';

describe('FormularioLivroComponent', () => {
  let component: FormularioLivroComponent;
  let fixture: ComponentFixture<FormularioLivroComponent>;
  let http: HttpTestingController;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormularioLivroComponent],
      providers: [provideRouter([]), provideHttpClient(), provideHttpClientTesting()]
    }).compileComponents();

    fixture = TestBed.createComponent(FormularioLivroComponent);
    component = fixture.componentInstance;
    http = TestBed.inject(HttpTestingController);
    fixture.detectChanges();
  });

  afterEach(() => http.verify());

  it('nao deve enviar formulario invalido', () => {
    component.save();

    expect(component.form.invalid).toBe(true);
    expect(component.saving).toBe(false);
    http.expectNone('/api/livros');
  });

  it('deve enviar formulario valido para criar um livro', () => {
    component.form.setValue({ titulo: 'O Cortico', autor: 'Aluisio Azevedo' });
    component.save();

    const request = http.expectOne('/api/livros');
    expect(request.request.method).toBe('POST');
    expect(request.request.body).toEqual({ titulo: 'O Cortico', autor: 'Aluisio Azevedo' });
    request.flush({ id: 3, titulo: 'O Cortico', autor: 'Aluisio Azevedo' });
    expect(component.saving).toBe(false);
  });
});
