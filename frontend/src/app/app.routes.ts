import { Routes } from '@angular/router';
import { HomeComponent } from './home.component';
import { ListaLivrosComponent } from './lista-livros.component';
import { FormularioLivroComponent } from './formulario-livro.component';

export const routes: Routes = [
	{ path: '', redirectTo: 'home', pathMatch: 'full' },
	{ path: 'home', component: HomeComponent },
	{ path: 'lista', component: ListaLivrosComponent },
	{ path: 'formulario', component: FormularioLivroComponent },
	{ path: 'formulario/:id', component: FormularioLivroComponent },
	{ path: '**', redirectTo: 'home' }
];
