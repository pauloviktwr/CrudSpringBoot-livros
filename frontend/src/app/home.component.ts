import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="py-5 border-bottom">
      <p class="text-success text-uppercase fw-bold small">Livros Web Application</p>
      <h1 class="display-3 fw-bold">Catalogo simples, fluxo confiavel.</h1>
      <p class="lead text-secondary col-lg-7">Uma interface Angular para demonstrar um CRUD conectado a uma API Java Spring Boot.</p>
      <a class="btn btn-success" routerLink="/lista">Abrir catalogo</a>
    </section>
    <section class="row g-3 mt-4" aria-label="Recursos do projeto">
      <article class="col-md-4"><div class="border-start border-4 border-warning bg-white p-4 h-100"><strong class="d-block">REST</strong><span class="text-secondary small">API com contratos JSON e validacao.</span></div></article>
      <article class="col-md-4"><div class="border-start border-4 border-warning bg-white p-4 h-100"><strong class="d-block">Qualidade</strong><span class="text-secondary small">Testes unitarios e de integracao no backend.</span></div></article>
      <article class="col-md-4"><div class="border-start border-4 border-warning bg-white p-4 h-100"><strong class="d-block">Camadas</strong><span class="text-secondary small">Controller, Service, Repository e DTOs.</span></div></article>
    </section>
  `
})
export class HomeComponent {}