/*
3. Insertion Sort
O que é? O Insertion Sort constrói a lista ordenada final um item por vez. Ele funciona pegando cada elemento da lista
não ordenada e inserindo-o na posição correta dentro da lista já ordenada. É como organizar cartas de um baralho na mão.

Para que serve? Para ordenar uma coleção de itens. É eficiente para listas pequenas ou listas que já
estão "quase ordenadas".

Vantagens:
Simples de implementar.
Eficiente para conjuntos de dados pequenos.
Adaptativo: se a lista já estiver ordenada ou quase ordenada, o desempenho é muito bom (próximo de O(n)).
Estável (não muda a ordem relativa de elementos com chaves iguais).
Ordenação "in-place".
Bom para ordenação online (pode ordenar a lista à medida que recebe novos elementos).

Desvantagens:
Ineficiente para listas grandes, com complexidade de tempo no pior caso e caso médio de O(n²).
 */

public class InsertionSort {

    public static void insertionSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array

        // Loop externo: começa do segundo elemento (índice 1), pois o primeiro elemento (índice 0)
        // é considerado a "lista ordenada" inicial de um único elemento.
        for (int i = 1; i < n; i++) {
            // 'chave' é o elemento atual que queremos inserir na parte ordenada do array.
            int chave = array[i];

            // 'j' é o índice do último elemento na parte já ordenada do array (à esquerda de 'chave').
            int j = i - 1;

            // Loop interno: move os elementos da parte ordenada que são maiores que a 'chave'
            // uma posição para a direita, para abrir espaço para a 'chave'.
            // Continua enquanto 'j' for um índice válido (>=0) e o elemento array[j] for maior que 'chave'.
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j]; // Desloca o elemento array[j] para a direita (array[j+1])
                j = j - 1;               // Move para o próximo elemento à esquerda na parte ordenada
            }

            // Após o loop while, 'j+1' é a posição correta para inserir a 'chave'.
            // Isso ocorre porque 'j' parou no elemento que é menor ou igual à 'chave',
            // ou 'j' se tornou -1 (se a 'chave' for o menor elemento até agora).
            array[j + 1] = chave;
        }
    }

    public static void main(String[] args) {
        int[] dados = {12, 11, 13, 5, 6, 90, 25};
        System.out.println("Array antes da ordenação (Insertion Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        insertionSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Insertion Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}