import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [RouterLink],
  template: `
    <section class="hero">
      <p class="eyebrow">Livros Web Application</p>
      <h1>Catalogo simples, fluxo confiavel.</h1>
      <p class="intro">Uma interface Angular para demonstrar um CRUD conectado a uma API Java Spring Boot.</p>
      <a class="button button-primary" routerLink="/lista">Abrir catalogo</a>
    </section>
    <section class="feature-grid" aria-label="Recursos do projeto">
      <article><strong>REST</strong><span>API com contratos JSON e validacao.</span></article>
      <article><strong>Qualidade</strong><span>Testes unitarios e de integracao no backend.</span></article>
      <article><strong>Camadas</strong><span>Controller, Service, Repository e DTOs.</span></article>
    </section>
  `
})
export class HomeComponent {}