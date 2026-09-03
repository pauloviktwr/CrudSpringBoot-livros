import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Livro } from './livro.model';
import { LivroService } from './livro.service';

@Component({
  selector: 'app-formulario-livro',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  template: `
    <section class="d-flex flex-column flex-sm-row align-items-sm-end justify-content-between gap-3 mb-4"><div><p class="text-success text-uppercase fw-bold small mb-2">Catalogo</p><h1>{{ editing ? 'Editar livro' : 'Novo livro' }}</h1></div>
      <a class="btn btn-outline-secondary" routerLink="/lista">Voltar para lista</a></section>
    @if (errorMessage) { <p class="alert alert-danger" role="alert">{{ errorMessage }}</p> }
    @if (successMessage) { <p class="alert alert-success" role="status">{{ successMessage }}</p> }
    <form class="bg-white border p-4 p-md-5 col-lg-8" [formGroup]="form" (ngSubmit)="save()" novalidate>
      <label class="form-label" for="titulo">Titulo <span class="text-danger">*</span></label>
      <input class="form-control" id="titulo" type="text" formControlName="titulo" placeholder="Ex.: Clean Code" />
      @if (form.controls.titulo.touched && form.controls.titulo.invalid) { <small class="text-danger">Informe um titulo entre 2 e 100 caracteres.</small> }
      <label class="form-label mt-3" for="autor">Autor <span class="text-danger">*</span></label>
      <input class="form-control" id="autor" type="text" formControlName="autor" placeholder="Ex.: Robert C. Martin" />
      @if (form.controls.autor.touched && form.controls.autor.invalid) { <small class="text-danger">Informe um autor entre 2 e 100 caracteres.</small> }
      <div class="d-flex justify-content-end gap-2 mt-4"><a class="btn btn-outline-secondary" routerLink="/lista">Cancelar</a><button class="btn btn-success" type="submit" [disabled]="saving">{{ saving ? 'Salvando...' : 'Salvar livro' }}</button></div>
    </form>
  `
})
export class FormularioLivroComponent implements OnInit {
  private readonly builder = inject(FormBuilder);
  private readonly service = inject(LivroService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);
  readonly form = this.builder.nonNullable.group({ titulo: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]], autor: ['', [Validators.required, Validators.minLength(2), Validators.maxLength(100)]] });
  editing = false;
  saving = false;
  errorMessage = '';
  successMessage = '';
  private id?: number;

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (!id) return;
    this.id = Number(id);
    this.editing = true;
    this.service.findById(this.id).subscribe({
      next: (livro) => this.form.patchValue({ titulo: livro.titulo, autor: livro.autor }),
      error: (error) => this.errorMessage = this.service.messageFromError(error)
    });
  }

  save(): void {
    this.form.markAllAsTouched();
    if (this.form.invalid) return;
    this.saving = true;
    this.errorMessage = '';
    const livro: Livro = this.form.getRawValue();
    const request = this.editing && this.id ? this.service.update(this.id, livro) : this.service.create(livro);
    request.subscribe({
      next: () => { this.successMessage = 'Livro salvo com sucesso.'; this.saving = false; this.router.navigate(['/lista']); },
      error: (error) => { this.errorMessage = this.service.messageFromError(error); this.saving = false; }
    });
  }
}