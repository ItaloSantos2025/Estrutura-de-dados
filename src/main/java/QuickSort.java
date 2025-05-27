/*
5. Quick Sort
O que é? O Quick Sort também é um algoritmo de ordenação eficiente que usa a abordagem "dividir para conquistar". Ele
funciona escolhendo um elemento da lista como "pivô". Em seguida, ele particiona os outros elementos da lista em duas
sublistas, de acordo com se eles são menores ou maiores que o pivô. As sublistas são então ordenadas recursivamente.

Para que serve? Para ordenar eficientemente grandes volumes de dados. É frequentemente mais rápido na prática do que
outros algoritmos O(n log n) como o Merge Sort, especialmente devido a constantes menores e bom uso de cache, embora
seu pior caso seja O(n²).

Vantagens:
Muito eficiente no caso médio, com complexidade de tempo O(n log n).
Ordenação "in-place" (requer apenas uma pequena quantidade de memória auxiliar para a pilha de recursão, O(log n) no caso médio).
Geralmente mais rápido que o Merge Sort na prática para muitos tipos de dados.

Desvantagens:
Complexidade de tempo no pior caso é O(n²), que pode ocorrer se o pivô for consistentemente escolhido de forma
ruim (por exemplo, sempre o menor ou o maior elemento em uma lista já ordenada ou inversamente ordenada).
Não é estável na sua implementação típica.
A escolha do pivô é crucial para o desempenho.
 */

public class QuickSort {

    // Método principal que inicia a ordenação Quick Sort
    public static void quickSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }
        // Chama o método recursivo de ordenação
        sort(array, 0, array.length - 1);
    }

    // Método recursivo que implementa o Quick Sort
    private static void sort(int[] array, int inicio, int fim) {
        // Condição de parada da recursão: se a sublista tem 1 ou 0 elementos, ou se 'inicio' ultrapassa 'fim'.
        if (inicio < fim) {
            // Particiona o array e obtém o índice do pivô na sua posição correta
            int indicePivo = particionar(array, inicio, fim);

            // Ordena recursivamente os elementos antes do pivô
            sort(array, inicio, indicePivo - 1);
            // Ordena recursivamente os elementos depois do pivô
            sort(array, indicePivo + 1, fim);
        }
    }

    // Método para particionar o array em torno de um pivô.
    // Elementos menores que o pivô vão para a esquerda, maiores para a direita.
    // Esta implementação usa o último elemento como pivô.
    private static int particionar(int[] array, int inicio, int fim) {
        // Escolhe o último elemento como pivô
        int pivo = array[fim];

        // 'i' é o índice do último elemento que é menor que o pivô.
        // Inicialmente, 'i' está uma posição antes do início da sublista.
        int i = (inicio - 1);

        // Percorre a sublista (de 'inicio' até 'fim-1', pois 'fim' é o pivô)
        for (int j = inicio; j < fim; j++) {
            // Se o elemento atual (array[j]) for menor ou igual ao pivô
            if (array[j] <= pivo) {
                i++; // Incrementa 'i' para apontar para a próxima posição onde um elemento menor que o pivô será colocado

                // Troca array[i] com array[j]
                // Isso move o elemento array[j] (que é <= pivô) para a parte esquerda (menores)
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Após o loop, todos os elementos menores ou iguais ao pivô estão à esquerda de 'i+1'.
        // Agora, coloca o pivô (que está em array[fim]) em sua posição correta (array[i+1]).
        // Troca array[i+1] com array[fim] (o pivô)
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;

        // Retorna o índice onde o pivô foi colocado, que é sua posição final ordenada.
        return i + 1;
    }

    public static void main(String[] args) {
        int[] dados = {10, 7, 8, 9, 1, 5, 0, 100};
        System.out.println("Array antes da ordenação (Quick Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        quickSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Quick Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}