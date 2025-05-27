/*
6. Heap Sort
O que é? O Heap Sort é um algoritmo de ordenação eficiente baseado na estrutura de dados "Heap Binário". Ele
primeiro transforma a lista de entrada em um Max Heap (onde o pai é sempre maior ou igual aos filhos) ou Min Heap
(onde o pai é sempre menor ou igual aos filhos). Em um Max Heap, o maior elemento está sempre na raiz. O algoritmo
então remove repetidamente o elemento raiz (o maior, no caso do Max Heap), coloca-o no final da porção ordenada do
array e reconstrói o heap com os elementos restantes.

Para que serve? Serve para ordenar coleções de itens de forma eficiente, especialmente quando é necessário garantir
uma complexidade de tempo O(n log n) no pior caso e a ordenação "in-place" (com pouca memória auxiliar) é desejável.

Vantagens:
Complexidade de tempo O(n log n) em todos os casos (melhor, médio e pior).
É um algoritmo de ordenação "in-place", significando que requer apenas uma quantidade constante de espaço de
memória adicional (O(1)) para a ordenação, além do espaço para armazenar a lista (embora a implementação recursiva
do heapify possa usar O(log n) de espaço na pilha de recursão, a versão iterativa é O(1)).
Eficiente para listas grandes.

Desvantagens:
Não é estável (a ordem relativa de elementos com chaves iguais pode não ser preservada).
Pode ser mais lento na prática do que o QuickSort no caso médio para muitos conjuntos de dados, devido a uma pior localidade de cache.
A lógica do heapify pode ser um pouco mais complexa de entender inicialmente.
 */

public class HeapSort {

    // Método principal que realiza o Heap Sort
    public static void heapSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array

        // 1. Construir um Max Heap (reorganizar o array)
        // Começamos do último nó não-folha e vamos até a raiz.
        // O último nó não-folha está no índice (n / 2) - 1.
        // Um nó não-folha é qualquer nó que tenha pelo menos um filho.
        // Todos os nós a partir de n/2 são folhas.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i); // Chama heapify para transformar a subárvore com raiz em 'i' em um Max Heap
        }

        // 2. Extrair elementos um por um do heap
        // O loop vai do último elemento (índice n-1) até o segundo (índice 1),
        // pois o primeiro (índice 0) estará automaticamente em sua posição correta após as trocas.
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual (que é o maior elemento no heap atual) para o final
            // da porção não ordenada do array (posição 'i').
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Chama heapify na heap reduzida. O tamanho da heap agora é 'i'
            // (pois o elemento em array[i] está em sua posição final ordenada).
            // A raiz da heap a ser reconstruída é sempre o índice 0.
            heapify(array, i, 0);
        }
    }

    // Função para transformar uma subárvore com raiz no nó 'i' em um Max Heap.
    // 'n' é o tamanho da heap (ou da porção do array que estamos considerando como heap).
    // 'i' é o índice do nó raiz da subárvore que queremos transformar em heap.
    private static void heapify(int[] array, int n, int i) {
        int maior = i;       // Inicializa 'maior' como a raiz da subárvore atual
        int esquerda = 2 * i + 1; // Índice do filho da esquerda: 2*i + 1
        int direita = 2 * i + 2;  // Índice do filho da direita: 2*i + 2

        // Verifica se o filho da esquerda existe (está dentro dos limites da heap 'n')
        // e se o filho da esquerda é maior que o nó 'maior' atual.
        if (esquerda < n && array[esquerda] > array[maior]) {
            maior = esquerda;
        }

        // Verifica se o filho da direita existe (está dentro dos limites da heap 'n')
        // e se o filho da direita é maior que o nó 'maior' atual (que pode jo filho da esquerda).
        if (direita < n && array[direita] > array[maior]) {
            maior = direita;
        }

        // Se 'maior' não for a raiz original 'i', significa que um dos filhos é maior.
        // Então, trocamos a raiz original 'i' com o maior filho ('maior').
        if (maior != i) {
            int swap = array[i];
            array[i] = array[maior];
            array[maior] = swap;

            // Após a troca, a subárvore com raiz no nó 'maior' (que agora contém o valor original de 'i')
            // pode ter sido desbalanceada (violado a propriedade de Max Heap).
            // Portanto, chamamos heapify recursivamente para essa subárvore afetada
            // para garantir que a propriedade de Max Heap seja mantida abaixo desse nó.
            heapify(array, n, maior);
        }
    }

    public static void main(String[] args) {
        int[] dados = {12, 11, 13, 5, 6, 7, 90, 1, 0, -5};
        System.out.println("Array antes da ordenação (Heap Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        heapSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Heap Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        int[] dados2 = {1, 2, 3, 4, 5}; // Array já ordenado
        System.out.println("\nArray antes da ordenação (Heap Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        heapSort(dados2);

        System.out.println("Array depois da ordenação (Heap Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        int[] dados3 = {5, 4, 3, 2, 1}; // Array inversamente ordenado
        System.out.println("\nArray antes da ordenação (Heap Sort) - dados3:");
        for (int valor : dados3) {
            System.out.print(valor + " ");
        }
        System.out.println();

        heapSort(dados3);

        System.out.println("Array depois da ordenação (Heap Sort) - dados3:");
        for (int valor : dados3) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}