/*
1. Bubble Sort
O que é? O Bubble Sort é um algoritmo de ordenação simples que percorre repetidamente a lista, compara elementos
adjacentes e os troca se estiverem na ordem errada. As passagens pela lista são repetidas até que nenhuma troca seja
necessária, o que indica que a lista está ordenada. Os maiores (ou menores, dependendo da ordem) elementos "borbulham"
para o final (ou início) da lista.

Para que serve? Serve para ordenar uma coleção de itens. É mais utilizado para fins didáticos devido à sua
simplicidade, pois não é eficiente para grandes conjuntos de dados.

Vantagens:
Simples de entender e implementar.
Eficiente para listas pequenas ou quase ordenadas (com uma otimização para parar cedo).
Não requer memória adicional significativa (ordenação "in-place", exceto por uma variável temporária para troca).

Desvantagens:
Muito ineficiente para listas grandes. Sua complexidade de tempo no pior caso e no caso médio é O(n²), onde n é o número de itens.
Mesmo no melhor caso (lista já ordenada), sem otimização, ele ainda fará n² comparações (com otimização, O(n)).
 */

public class BubbleSort {

    public static void bubbleSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos, pois nesse caso já está "ordenado" ou não há o que ordenar.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array
        boolean swapped; // Flag para otimizar: se não houver trocas em uma passagem, o array está ordenado

        // Loop externo: controla o número de passagens pelo array
        // Cada passagem "borbulha" o maior elemento não ordenado para sua posição correta no final do array.
        // Por isso, o loop vai até n-1, pois o último elemento estará em sua posição após n-1 passagens.
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reseta a flag de troca para a passagem atual

            // Loop interno: percorre o array comparando elementos adjacentes
            // A cada passagem 'i', o i-ésimo maior elemento já está no lugar,
            // então não precisamos comparar até o final do array, mas sim até n-1-i.
            for (int j = 0; j < n - 1 - i; j++) {
                // Compara o elemento atual (array[j]) com o próximo elemento (array[j+1])
                if (array[j] > array[j + 1]) {
                    // Se o elemento atual for maior que o próximo, troca-os de lugar
                    int temp = array[j];      // Armazena o valor de array[j] em uma variável temporária
                    array[j] = array[j + 1];  // Coloca o valor de array[j+1] em array[j]
                    array[j + 1] = temp;      // Coloca o valor temporário (original de array[j]) em array[j+1]

                    swapped = true; // Indica que uma troca ocorreu nesta passagem
                }
            }

            // Otimização: Se não houve trocas na passagem interna, o array já está ordenado.
            // Podemos interromper o algoritmo mais cedo.
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] dados = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Array antes da ordenação (Bubble Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        bubbleSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Bubble Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}