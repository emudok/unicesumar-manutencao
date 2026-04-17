# Relatório de Refatoração - Atividade 1

## Visão Geral da Branch

Diferenças entre a branch atual e `master`:
- Arquivos alterados: `library-maintenance-lab/src/BookManager.java`, `library-maintenance-lab/src/LegacyDatabase.java`, `library-maintenance-lab/src/LoanManager.java`, `library-maintenance-lab/src/ReportGenerator.java`
- Volume das mudanças: 727 inserções e 706 deleções

## Problemas resolvidos

1. `BookManager.registerBook()` aceitando título em branco
   - Antes: o método aceitava `title` vazio e inseria um valor de workaround (`" "`).
   - Resolvido: validação agora rejeita títulos em branco e lança exceção clara.
   - Smell correlacionado: validação fraca / primitive obsession / inconsistent error handling.

2. `BookManager.listBooksSimple()` falhando com lista vazia
   - Antes: chamava `temp.get(0)` quando não havia livros.
   - Resolvido: adicionada verificação de lista vazia e retorno antecipado com mensagem amigável.
   - Smell correlacionado: edge case mal tratado / long method / mixed responsibilities.

3. `LegacyDatabase.countOpenLoansByBook()` usando `userId` em vez de `bookId`
   - Antes: filtro incorreto fazia a contagem de empréstimos abertos por livro retornar valor errado.
   - Resolvido: correção para comparar `loan.get("bookId")` com o `bookId` passado.
   - Smell correlacionado: poor naming / hidden dependency / calculation bug.

4. `LoanManager.returnBook()` ignorando empréstimo inexistente
   - Antes: retorno silencioso quando o empréstimo não era encontrado.
   - Resolvido: log de erro e exceção explícita em vez de sucesso silencioso.
   - Smell correlacionado: inconsistent error handling / bug logic.

5. `LoanManager.returnBook()` calculando dívida incorretamente
   - Antes: dívida era reduzida (`debt - fine`) em vez de aumentada.
   - Resolvido: corregida a operação para `debt + fine`.
   - Smell correlacionado: bug de cálculo / fragile logic.

6. `LoanManager.calculateFineLegacy()` ordem de thresholds errada
   - Antes: verificava `fine > 50` antes de `fine > 100`, o que tornava alerta de maior prioridade inacessível.
   - Resolvido: alterado para checar `fine > 100` antes de `fine > 50`.
   - Smell correlacionado: long method / nested conditionals / rule ordering bug.

7. `ReportGenerator.generateSimpleReport()` com números inflados
   - Antes: `totalLoans` era `loans.size() + 1`; `closedLoans` era incrementado para todos os empréstimos.
   - Resolvido: removido o ajuste mágico `+1` e contado apenas `CLOSED` para `closedLoans`.
   - Smell correlacionado: magic number / calculation bug / mixed responsibilities.

## Refatorações aplicadas

- `BookManager.registerBook()`
  - reforço de validação de entrada para `title` e `author`
  - remoção de workaround legado que aceitava dados inválidos

- `BookManager.listBooksSimple()`
  - tratamento de caso de lista vazia com early return
  - melhoria de legibilidade do fluxo de saída do relatório

- `LegacyDatabase.countOpenLoansByBook()`
  - correção de filtro para o campo correto
  - eliminação de lógica incorreta que comprometia a disponibilidade de estoque

- `LoanManager.returnBook()`
  - normalização de erro: não mais ignora empréstimos inexistentes
  - ajuste de atualização de dívida para manter consistência financeira

- `LoanManager.calculateFineLegacy()`
  - reorganização da cadeia de condição para alertas de débito
  - correção de regra de negócio de notificação de dívida

- `ReportGenerator.generateSimpleReport()`
  - remoção de ajuste mágico e contagem incorreta de empréstimos fechados
  - extração de constante local para limite de logs recentes

## Pontos adicionais

- O relatório foi baseado em:
  - lista de smells e problemas identificados em `library-maintenance-lab/ATIVIDADE_1.md`
  - diferenças reais entre a branch atual e `master` na pasta `library-maintenance-lab/src`

- A mudança concentra-se em correções de bugs e melhoria de estabilidade, mantendo o comportamento funcional do sistema.
