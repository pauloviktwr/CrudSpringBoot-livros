export interface Livro {
  id?: number;
  titulo: string;
  autor: string;
}

export interface PaginaLivros {
  content: Livro[];
  totalElements: number;
  totalPages: number;
  number: number;
}

export interface ErroApi {
  message?: string;
  fieldErrors?: Array<{ campo: string; mensagem: string }>;
}