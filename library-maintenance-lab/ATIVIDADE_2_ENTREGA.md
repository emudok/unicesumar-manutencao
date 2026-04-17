# Entrega - Atividade 2

## Bugs corrigidos

### 1. Falha ao listar livros quando nao existem registros
- Arquivo: `src/BookManager.java`
- Problema anterior: o metodo tentava acessar `temp.get(0)` quando a lista estava vazia.
- Correcao aplicada: exibir a mensagem `No books registered.` e encerrar a listagem com `return`.

### 2. Contagem incorreta de emprestimos abertos por livro
- Arquivo: `src/LegacyDatabase.java`
- Problema anterior: o metodo `countOpenLoansByBook` comparava `userId` com `bookId`.
- Correcao aplicada: passar a comparar corretamente o campo `bookId`.

### 3. Totais incorretos no relatorio
- Arquivo: `src/ReportGenerator.java`
- Problema anterior:
- o total de emprestimos era calculado com `loans.size() + 1`
- o total de emprestimos fechados era incrementado para qualquer emprestimo
- Correcao aplicada:
- usar `loans.size()` como total real
- contar emprestimos fechados apenas quando o status for `CLOSED`

### 4. Cadastro de livro com titulo em branco
- Arquivo: `src/BookManager.java`

- Problema anterior: o metodo registerBook substituia o titulo vazio por um espaco simples (" ") por causa de um codigo legado e permitia o salvamento, gerando dados indevidos.

- Correcao aplicada: removida a atribuicao do espaco e adicionado bloqueio imediato, lancando a excecao throw new RuntimeException("title invalid");.

### 5. Copias disponiveis ultrapassando o estoque total
- Arquivo: `src/BookManager.java`

- Problema anterior: o metodo registerBook nao validava se as copias disponiveis excediam o numero total de copias do livro, permitindo uma falha na regra de negocio do estoque.

- Correcao aplicada: adicionada condicional garantindo que, caso availableCopies > totalCopies, as copias disponiveis sao niveladas pelo teto maximo.

## Nova funcionalidade

### Historico de emprestimos por usuario
- Arquivos:
- `src/LoanManager.java`
- `src/LibrarySystem.java`
- Implementacao:
- nova opcao `10 - Loan history by user` no menu principal
- novo fluxo para informar o `User ID`
- listagem de todos os emprestimos do usuario, abertos e fechados

## Como reproduzir antes e depois

### Bug 1 - Listagem de livros vazia
- Antes:
- deixar a base sem livros
- executar a listagem
- ocorre falha por acesso indevido a posicao inexistente
- Depois:
- deixar a base sem livros
- executar a listagem
- o sistema exibe `No books registered.`

### Bug 2 - Contagem por livro
- Antes:
- criar emprestimos para um livro
- consultar a contagem por livro
- o resultado podia ficar inconsistente por filtrar no campo errado
- Depois:
- criar emprestimos para um livro
- consultar a contagem por livro
- a contagem considera corretamente o `bookId`

### Bug 3 - Relatorio
- Antes:
- executar `java -cp ./src Main --report`
- o total de emprestimos aparecia maior do que o real
- o total de emprestimos fechados era calculado incorretamente
- Depois:
- executar `java -cp ./src Main --report`
- os totais passam a refletir os dados reais

## Impactos e riscos conhecidos
- As mudancas preservam a estrutura atual do sistema.
- A nova funcionalidade adiciona apenas consulta, sem alterar regras de negocio existentes.
- A validacao continua manual, por compilacao e execucao do fluxo principal.
