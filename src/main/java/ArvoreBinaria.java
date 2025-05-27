/*
Arvore Binária (Binary Tree)
O que é? Uma ore binária inear, composta por nós. Cada nó em uma árvore binária pode ter no máximo dois "filhos": um
filho à esquerda e um filho à direita.
O nó no topo da hierarquia é chamado de raiz (root).
Nós que não têm filhos são chamados de folhas (leaves) ou nós terminais.
Nós que não são a raiz nem folhas são chamados de nós internos.
Cada nó (exceto a raiz) tem exatamente um pai.
Uma árvore binária pode ser vazia (não ter nenhum nó). Existem vários tipos especializados de árvores binárias, como
Árvore Binária de Busca (BST), Árvore AVL, Árvore Rubro-Negra, Heap Binário, etc., cada uma com propriedades e regras
específicas.

Para que serve? Árvores binárias são usadas para representar dados com uma relação hierárquica. Elas são a base para
muitas estruturas de dados e algoritmos eficientes, especialmente para:
Busca: Árvores Binárias de Busca (BSTs) permitem buscas, inserções e remoções eficientes (em média O(log n)).
Ordenação: Algoritmos como HeapSort usam uma estrutura de árvore binária (heap).
Representação de Expressatemáticas: Operadores podem ser nós internos e operandos podem ser folhas.
Algoritmos de Compressão: Como árvores de Huffman.
Tomada de Decisão: Árvores de decisão.
Estruturas de Dados de Roteamento em redes.

Vantagens:
Busca Eficiente (em tipos específicos): Em árvores binárias de busca balanceadas, a busca por um elemento é muito
rápida, com complexidade de tempo logarítmica (O(log n)).
Representação Hierárquica Natural: Ideal para dados que possuem uma estrutura inerentemente hierárquica.
Inserção e Remoção Eficientes (em tipos específicos): Assim como a busca, em árvores balanceadas, essas operações
também são O(log n).
Flexibilidade: Podem ser usadas para uma ampla gama de aplicações.

Desvantagens:
Desbalanceamento (em árvores não auto-balanceadas): Se uma Árvore Binária de Busca não for balanceada (ou seja, se os
dados forem inseridos de forma ordenada ou quase ordenada), ela pode degenerar em uma estrutura semelhante a uma lista
encadeada. Nesse caso, o desempenho das operações de busca, inserção e remoção cai para O(n) no pior caso.
Complexidade de Implementação: Algoritmos para manter o balanceamento (como em árvores AVL ou Rubro-Negras) podem ser
complexos de implementar.
Percorrer a Árvore: Existem diferentes formas de percorrer (visitar todos os nós) uma árvore (pré-ordem, em-ordem,
pós-ordem, em nível), e a escolha depende da aplicação. A implementação desses percursos, embora não excessivamente
complexa, requer atenção.
Uso de Memória: Cada nó armazena dados e ponteiros para os filhos, similar às listas encadeadas, mas potencialmente
com mais ponteiros se informações adicionais (como ponteiro para o pai ou fatores de balanceamento) forem necessárias.
 */

public class ArvoreBinaria {
}