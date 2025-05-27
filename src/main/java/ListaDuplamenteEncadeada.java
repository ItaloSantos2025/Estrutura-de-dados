/*
Lista Duplamente Encadeada (Doubly Linked List)
O que é? Uma lista duplamente encadeada é uma variação da lista encadeada onde cada nó, além de conter o dado e um
ponteiro para o próximo nó, também possui um ponteiro para o nó anterior. Isso permite a travessia da lista em ambas
as direções (para frente e para trás). Assim como na lista simplesmente encadeada, ela tem uma "cabeça" (head) e,
opcionalmente, pode ter uma "cauda" (tail) para facilitar operações no final. O ponteiro anterior da cabeça é null, e
o ponteiro proximo da cauda (ou do último nó) é null.

Para que serve? Similar à lista simplesmente encadeada, serve para armazenar coleções dinâmicas de elementos. A
principal vantagem é a capacidade de percorrer a lista em ambas as direções, o que torna certas operações, como a
remoção de um nó específico (quando se tem a referência para ele) ou a inserção antes de um nó específico, mais eficientes.

Vantagens:
Travessia Bidirecional: Pode-se percorrer a lista tanto do início para o fim quanto do fim para o início.
Remoção Eficiente (com referência ao nó): Se você tem uma referência direta para um nó que deseja remover, a remoção
pode ser feita em tempo constante (O(1)), pois você pode acessar diretamente os nós vizinhos (anterior e próximo) para
atualizar seus ponteiros. Na lista simples, para remover um nó (que não seja a cabeça), você precisaria primeiro
encontrar seu nó anterior.
Inserção Antes/Depois Eficiente (com referência ao nó): Similar à remoção, a inserção antes ou depois de um nó
específico é mais direta.
Tamanho Dinâmico e Uso de Memória Flexível: Mesmas vantagens da lista simplesmente encadeada.

Desvantagens:
Maior Overhead de Memória: Cada nó requer espaço para dois ponteiros (próximo e anterior), em vez de um, aumentando o
consumo de memória por nó.
Implementação Mais Complexa: As operações de inserção e remoção envolvem a atualização de mais ponteiros (quatro
atualizações de ponteiros para inserção/remoção no meio, em vez de duas na lista simples), o que pode tornar a
implementação um pouco mais propensa a erros.
Acesso Aleatório Lento: Assim como na lista simples, o acesso a um elemento por índice ainda é O(n).

 */

public class ListaDuplamenteEncadeada {

    // Classe interna para representar um nó da lista duplamente encadeada
    private static class No {
        int dado;     // O valor armazenado no nó
        No proximo;  // Referência para o próximo nó na lista
        No anterior; // Referência para o nó anterior na lista

        // Construtor do nó
        public No(int dado) {
            this.dado = dado;
            this.proximo = null;
            this.anterior = null;
        }
    }

    private No cabeca; // Referência para o primeiro nó da lista (cabeça)
    private No cauda;  // Referência para o último nó da lista (cauda) - opcional, mas útil
    private int tamanho; // Mantém o controle do número de nós na lista

    // Construtor da lista duplamente encadeada
    public ListaDuplamenteEncadeada() {
        this.cabeca = null;
        this.cauda = null;
        this.tamanho = 0;
    }

    // Método para verificar se a lista está vazia
    public boolean estaVazia() {
        return tamanho == 0; // Ou cabeca == null
    }

    // Método para obter o tamanho da lista
    public int getTamanho() {
        return tamanho;
    }

    // Método para adicionar um elemento no início da lista
    public void adicionarNoInicio(int dado) {
        No novoNo = new No(dado);
        if (estaVazia()) {
            // Se a lista está vazia, o novo nó é tanto a cabeça quanto a cauda
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            novoNo.proximo = cabeca; // O próximo do novo nó é a antiga cabeça
            cabeca.anterior = novoNo; // O anterior da antiga cabeça é o novo nó
            cabeca = novoNo;          // A cabeça agora é o novo nó
        }
        tamanho++;
    }

    // Método para adicionar um elemento no final da lista
    public void adicionarNoFinal(int dado) {
        No novoNo = new No(dado);
        if (estaVazia()) {
            // Se a lista está vazia, o novo nó é tanto a cabeça quanto a cauda
            cabeca = novoNo;
            cauda = novoNo;
        } else {
            cauda.proximo = novoNo;  // O próximo da antiga cauda é o novo nó
            novoNo.anterior = cauda; // O anterior do novo nó é a antiga cauda
            cauda = novoNo;          // A cauda agora é o novo nó
        }
        tamanho++;
    }

    // Método para remover um elemento do início da lista
    public No removerDoInicio() {
        if (estaVazia()) {
            System.out.println("A lista está vazia.");
            return null;
        }
        No noRemovido = cabeca;
        if (tamanho == 1) { // Se houver apenas um nó
            cabeca = null;
            cauda = null;
        } else {
            cabeca = cabeca.proximo; // A cabeça se torna o próximo nó
            cabeca.anterior = null;  // O anterior da nova cabeça é null
            noRemovido.proximo = null; // Limpa a referência do nó removido
        }
        tamanho--;
        return noRemovido;
    }

    // Método para remover um elemento do final da lista
    public No removerDoFinal() {
        if (estaVazia()) {
            System.out.println("A lista está vazia.");
            return null;
        }
        No noRemovido = cauda;
        if (tamanho == 1) { // Se houver apenas um nó
            cabeca = null;
            cauda = null;
        } else {
            cauda = cauda.anterior; // A cauda se torna o nó anterior
            cauda.proximo = null;   // O próximo da nova cauda é null
            noRemovido.anterior = null; // Limpa a referência do nó removido
        }
        tamanho--;
        return noRemovido;
    }

    // Método para exibir os elementos da lista (da cabeça para a cauda)
    public void exibirParaFrente() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }
        System.out.print("Lista (->): Cabeça -> ");
        No atual = cabeca;
        while (atual != null) {
            System.out.print(atual.dado + (atual.proximo != null ? " <-> " : ""));
            atual = atual.proximo;
        }
        System.out.println(" -> null (Fim)");
    }

    // Método para exibir os elementos da lista (da cauda para a cabeça)
    public void exibirParaTras() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }
        System.out.print("Lista (<-): Cauda -> ");
        No atual = cauda;
        while (atual != null) {
            System.out.print(atual.dado + (atual.anterior != null ? " <-> " : ""));
            atual = atual.anterior;
        }
        System.out.println(" -> null (Início)");
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        System.out.println("Lista está vazia? " + lista.estaVazia());

        lista.adicionarNoInicio(10);
        lista.adicionarNoInicio(5);    // Lista: 5 <-> 10
        lista.adicionarNoFinal(20);    // Lista: 5 <-> 10 <-> 20
        lista.adicionarNoFinal(25);    // Lista: 5 <-> 10 <-> 20 <-> 25

        System.out.println("\nApós adições:");
        lista.exibirParaFrente();
        lista.exibirParaTras();
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        System.out.println("\nRemovendo do início: " + lista.removerDoInicio().dado); // Remove 5
        lista.exibirParaFrente(); // Lista: 10 <-> 20 <-> 25

        System.out.println("Removendo do final: " + lista.removerDoFinal().dado);   // Remove 25
        lista.exibirParaFrente(); // Lista: 10 <-> 20
        System.out.println("Tamanho da lista: " + lista.getTamanho());

        lista.adicionarNoInicio(1);
        lista.adicionarNoFinal(30);
        lista.exibirParaFrente();
        lista.exibirParaTras();
    }
}