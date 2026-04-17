

## Problemas resolvidos
1. God Class em `LibrarySystem`.
   - A classe centralizava menu, orquestração, entrada de dados, debug, logs e cenário de demonstração.
   - Refatoração: separar responsabilidades em componentes de UI/fluxo e serviços de domínio.

2. Mixed Responsibilities em `LibrarySystem.startCli()`.
   - O loop principal misturava interface, regras de execução e tratamento de erros.
   - Refatoração: extrair dispatcher de comandos, separar leitura de entrada e execução de ações.

3. Long Method e Deep Nesting em `LibrarySystem.handleDebugArea()`.
   - Método muito extenso e com muitas condições aninhadas.
   - Refatoração: decompor cada ação de debug em métodos menores e usar retornos antecipados.

4. Tight Coupling / No DI em `LibrarySystem` e em `LoanManager`.
   - Dependências eram instanciadas diretamente com `new`.
   - Refatoração: aplicar injeção por construtor e usar interfaces/contratos em vez de classes concretas.

5. Duplicate Code na validação de cadastro de livros.
   - A validação e normalização de dados aparecia em mais de um lugar.
   - Refatoração: centralizar validação em `BookManager.registerBook()` e evitar duplicação.

6. Primitive Obsession em `handleBorrowBook()`, `handleReturnBook()` e `LoanManager.borrowBook()`.
   - Uso de strings e inteiros para comandos, políticas e flags.
   - Refatoração: criar enums/objetos de valor para parâmetros de domínio.

7. Long Method e Long Parameter List em `LoanManager.borrowBook()`.
   - O método fazia validação, criação de empréstimo, atualização de estado e notificação.
   - Refatoração: dividir em etapas claras e introduzir um objeto de requisição com campos nomeados.

8. Inconsistent Error Handling em `LoanManager.returnBook()`.
   - Um caminho retornava silenciosamente enquanto outro lançava exceção.
   - Refatoração: definir política única de tratamento de erro e mensagens consistentes.

9. Hidden Dependencies e estado global em `LegacyDatabase`.
   - Campos estáticos compartilhavam estado entre todas as classes.
   - Refatoração: encapsular o estado em um repositório controlado, reduzindo dependências implícitas.

10. Breaking Encapsulation em `LegacyDatabase.getBooks()/getUsers()/getLoans()`.
    - Estruturas internas mutáveis eram retornadas diretamente.
    - Refatoração: retornar cópias defensivas ou coleções imutáveis.

11. Magic Numbers e lógica obscura em `ReportGenerator`.
    - Ajuste fixo em `totalLoans` e cálculo frágil em `closedLoans`.
    - Refatoração: extrair constantes nomeadas e reorganizar cálculo com regras explícitas.

12. Utility Class Overuse em `DataUtil`.
    - Muitos métodos estáticos e estado compartilhado (`Scanner`, contador de retry).
    - Refatoração: reduzir estado global e mover utilitários para classes de responsabilidade única.

13. Primitive Obsession em `DataUtil.datePlusDaysApprox()`.
    - Cálculo de data feito por concatenação de string em vez de API de datas.
    - Refatoração: usar classes de data apropriadas e regras de prazo corretas.

14. Long Parameter List em `BookManager.registerBook()` e `UserManager.registerUser()`.
    - Muitos parâmetros primitivos dificultavam uso correto.
    - Refatoração: introduzir DTOs/objetos de dados (`BookData`, `UserData`) com validação centralizada.

## Mudanças feitas nesta branch
- Refatoração de `LibrarySystem` para reduzir acoplamento e separar interface/fluxo de aplicação.
- Extração de métodos específicos para cadastro, empréstimo, devolução e debug.
- Aplicação de injeção de dependências nas classes de serviço principais.
- Centralização de validações em `BookManager` e `UserManager`.
- Reestruturação de `LoanManager` em etapas: validar, criar empréstimo, atualizar estoque e notificar.
- Padronização do tratamento de erros e mensagens de exceção.
- Encapsulamento de estado e retorno de coleções seguras em `LegacyDatabase`.
- Remoção de números mágicos e melhoria da lógica de relatórios em `ReportGenerator`.
- Redução do uso de utilitários estáticos e reforço do uso correto da API de datas em `DataUtil`.


