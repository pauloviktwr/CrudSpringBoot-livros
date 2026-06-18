# Plano de Gestão do Projeto Frontend Angular

## Visão do Projeto
Criar uma aplicação frontend Angular independente que consuma o backend Java/Spring Boot existente via API REST. O frontend deve demonstrar competências incluindo navegação, componentes reutilizáveis, formulários reativos, validação, feedback visual, integração API e layout responsivo.

## Objetivos Principais
- Criar a base de projeto Angular em pasta separada do repositório
- Integrar localmente com o backend Java/Spring Boot usando HTTP
- Implementar navegação com rotas Angular: `/home`, `/lista`, `/formulario`
- Construir CRUD completo usando GET, POST, PUT e DELETE
- Criar formulários reativos com validação e mensagens visíveis
- Exibir estados de loading e mensagens de sucesso/erro
- Documentar setup, build e execução em README do frontend
- Escrever testes unitários básicos para componentes e serviços

## Backlog do Produto
1. Configuração inicial do projeto Angular
   - Criar aplicação Angular em pasta separada (`frontend` ou similar)
   - Definir build independente e configurações de proxy para backend
   - Instalar dependências principais: Angular Router, HttpClient, ReactiveFormsModule, Bootstrap ou Angular Material
2. Implementação de rotas e navegação
   - Criar componente `Home`
   - Criar componente `ListaLivros`
   - Criar componente `FormularioLivro`
   - Configurar `routerLink` e `router-outlet`
3. Serviço de comunicação com o backend
   - Criar `LivroService` com métodos `findAll`, `findById`, `create`, `update`, `delete`
   - Consumir endpoints REST existentes do backend
4. CRUD e tela de listagem
   - Implementar exibição de lista de livros
   - Adicionar botões de editar e excluir na listagem
   - Navegar para formulário de cadastro/edição
5. Formulários reativos com validação
   - Configurar `ReactiveFormsModule`
   - Criar formulário de cadastro/edição com campos obrigatórios
   - Exibir mensagens de validação inline
   - Desabilitar submit enquanto o formulário for inválido
6. Estados de loading e feedback
   - Exibir indicador de carregamento em requisições API
   - Mostrar mensagens de sucesso e erro após operações
   - Tratar falhas de rede e respostas inválidas
7. Layout responsivo e UI
   - Aplicar Bootstrap ou Angular Material para visual consistente
   - Garantir adaptabilidade em desktop e mobile
   - Criar componentes de UI reutilizáveis (cards, botões, alerts)
8. Testes unitários básicos
   - Escrever testes para `LivroService`
   - Escrever testes para componentes principais e formulários
9. Documentação de setup e execução
   - Escrever README com passos de instalação, build e execução
   - Incluir notas sobre proxy e comunicação com backend

## Sprint 1: Configuração e Backbone do Frontend
- [ ] Criar pasta de frontend separada e inicializar projeto Angular
- [ ] Configurar build independente e proxy de desenvolvimento
- [ ] Instalar dependências de UI e formulários
- [ ] Definir rotas `/home`, `/lista`, `/formulario`
- [ ] Criar componentes `Home`, `ListaLivros`, `FormularioLivro`

## Sprint 2: Integração com Backend e CRUD
- [ ] Criar `LivroService` usando `HttpClient`
- [ ] Implementar chamadas GET, POST, PUT, DELETE
- [ ] Implementar tela de listagem com dados do backend
- [ ] Adicionar funcionalidade de excluir com confirmação
- [ ] Implementar edição de livro via formulário

## Sprint 3: Formularios Reativos e Validação
- [ ] Construir formulário reativo com `FormGroup` e `FormControl`
- [ ] Adicionar validação de campos obrigatórios e tamanho
- [ ] Exibir feedback visual e mensagens de erro
- [ ] Desabilitar submit quando inválido
- [ ] Validar fluxo de cadastro e edição no formulário

## Sprint 4: Qualidade, UX e Documentação
- [ ] Implementar estados de loading em serviços e componentes
- [ ] Criar mensagens de sucesso/erro consistentes
- [ ] Ajustar layout responsivo com Bootstrap/Material
- [ ] Escrever testes unitários básicos
- [ ] Documentar setup, build e execução no README do frontend

## Critérios de Aceitação
- O frontend roda de forma independente em pasta separada
- Rotas funcionam corretamente e carregam componentes esperados
- O aplicativo consome a API do backend para CRUD de livros
- Formulários apresentam validação e feedback ao usuário
- O layout responde bem em diferentes tamanhos de tela
- O README explica como instalar, rodar e construir o frontend
- Testes básicos de serviço e componente são implementados

## Riscos e Dependências
- Dependência de proxy ou CORS para comunicação local com backend
- O backend deve permanecer inalterado; o frontend deve consumir o REST existente
- A versão do Angular deve ser compatível com bibliotecas de UI escolhidas

## Observações de Metodologia Scrum
- Planejar sprints curtas de 1 ou 2 semanas
- Revisar backlog a cada sprint e priorizar tarefas de maior valor
- Realizar daily standups focados em bloqueios e progresso
- Usar critérios de aceitação para validar cada tarefa entregue
- Documentar decisões importantes no README e nos comentários do projeto
