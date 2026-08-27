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
    <section class="page-heading"><div><p class="eyebrow">Catalogo</p><h1>{{ editing ? 'Editar livro' : 'Novo livro' }}</h1></div>
      <a class="button button-quiet" routerLink="/lista">Voltar para lista</a></section>
    @if (errorMessage) { <p class="alert alert-error" role="alert">{{ errorMessage }}</p> }
    @if (successMessage) { <p class="alert alert-success" role="status">{{ successMessage }}</p> }
    <form class="form-panel" [formGroup]="form" (ngSubmit)="save()" novalidate>
      <label for="titulo">Titulo <span>*</span></label>
      <input id="titulo" type="text" formControlName="titulo" placeholder="Ex.: Clean Code" />
      @if (form.controls.titulo.touched && form.controls.titulo.invalid) { <small class="field-error">Informe um titulo entre 2 e 100 caracteres.</small> }
      <label for="autor">Autor <span>*</span></label>
      <input id="autor" type="text" formControlName="autor" placeholder="Ex.: Robert C. Martin" />
      @if (form.controls.autor.touched && form.controls.autor.invalid) { <small class="field-error">Informe um autor entre 2 e 100 caracteres.</small> }
      <div class="form-actions"><a class="button button-quiet" routerLink="/lista">Cancelar</a><button class="button button-primary" type="submit" [disabled]="saving">{{ saving ? 'Salvando...' : 'Salvar livro' }}</button></div>
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