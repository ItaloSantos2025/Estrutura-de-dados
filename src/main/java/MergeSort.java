/*
4. Merge Sort
O que é? O Merge Sort é um algoritmo de ordenação eficiente que usa a abordagem "dividir para conquistar". Ele divide
recursivamente a lista em sublistas menores até que cada sublista contenha apenas um elemento (que é trivialmente ordenado).
Em seguida, ele mescla (combina) essas sublistas de forma ordenada para produzir novas sublistas ordenadas, até que
toda a lista esteja mesclada e ordenada.

Para que serve? Para ordenar eficientemente grandes volumes de dados. É um dos algoritmos de ordenação de propósito
geral mais rápidos.

Vantagens:
Eficiente para grandes conjuntos de dados, com complexidade de tempo de O(n log n) em todos os casos (pior, médio e melhor).
Estável (mantém a ordem relativa de elementos com chaves iguais).
Bom para ordenação externa (quando os dados não cabem na memória principal).

Desvantagens:
Requer espaço adicional na memória (O(n)) para armazenar as sublistas temporárias durante o processo de mesclagem. Não
é "in-place" na sua forma mais comum.
Para listas pequenas, pode ser mais lento que algoritmos mais simples como o Insertion Sort devido à sobrecarga da
recursão e da mesclagem.
 */

public class MergeSort {

    // Método principal que inicia a ordenação Merge Sort
    public static void mergeSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }
        // Chama o método recursivo de ordenação
        sort(array, 0, array.length - 1);
    }

    // Método recursivo que divide o array e chama a mesclagem
    private static void sort(int[] array, int esquerda, int direita) {
        // Condição de parada da recursão: se a sublista tem 1 ou 0 elementos, já está ordenada.
        if (esquerda < direita) {
            // Encontra o ponto médio para dividir o array em duas metades
            int meio = esquerda + (direita - esquerda) / 2; // Evita overflow para 'esquerda' e 'direita' grandes

            // Ordena recursivamente a primeira metade (da 'esquerda' até o 'meio')
            sort(array, esquerda, meio);
            // Ordena recursivamente a segunda metade (do 'meio + 1' até a 'direita')
            sort(array, meio + 1, direita);

            // Mescla (merge) as duas metades ordenadas
            merge(array, esquerda, meio, direita);
        }
    }

    // Método que mescla duas sublistas ordenadas array[esquerda..meio] e array[meio+1..direita]
    private static void merge(int[] array, int esquerda, int meio, int direita) {
        // Calcula os tamanhos das duas sublistas a serem mescladas
        int tamanhoSublista1 = meio - esquerda + 1;
        int tamanhoSublista2 = direita - meio;

        // Cria arrays temporários para armazenar as sublistas
        int[] arrayEsquerda = new int[tamanhoSublista1];
        int[] arrayDireita = new int[tamanhoSublista2];

        // Copia os dados para os arrays temporários
        // Copia elementos da primeira sublista (array[esquerda...meio]) para arrayEsquerda
        for (int i = 0; i < tamanhoSublista1; ++i) {
            arrayEsquerda[i] = array[esquerda + i];
        }
        // Copia elementos da segunda sublista (array[meio+1...direita]) para arrayDireita
        for (int j = 0; j < tamanhoSublista2; ++j) {
            arrayDireita[j] = array[meio + 1 + j];
        }

        // Índices iniciais para percorrer as sublistas temporárias e o array original (para mesclagem)
        int i = 0; // Índice para arrayEsquerda
        int j = 0; // Índice para arrayDireita
        int k = esquerda; // Índice inicial para o array original onde a mesclagem começa

        // Mescla os arrays temporários de volta no array original (array[esquerda..direita])
        // Compara elementos de arrayEsquerda e arrayDireita e coloca o menor no array original
        while (i < tamanhoSublista1 && j < tamanhoSublista2) {
            if (arrayEsquerda[i] <= arrayDireita[j]) {
                array[k] = arrayEsquerda[i];
                i++;
            } else {
                array[k] = arrayDireita[j];
                j++;
            }
            k++;
        }

        // Copia quaisquer elementos restantes de arrayEsquerda (se houver)
        // Isso acontece se arrayDireita foi totalmente consumido primeiro
        while (i < tamanhoSublista1) {
            array[k] = arrayEsquerda[i];
            i++;
            k++;
        }

        // Copia quaisquer elementos restantes de arrayDireita (se houver)
        // Isso acontece se arrayEsquerda foi totalmente consumido primeiro
        while (j < tamanhoSublista2) {
            array[k] = arrayDireita[j];
            j++;
            k++;
        }
    }

    public static void main(String[] args) {
        int[] dados = {38, 27, 43, 3, 9, 82, 10, 1};
        System.out.println("Array antes da ordenação (Merge Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        mergeSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Merge Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}