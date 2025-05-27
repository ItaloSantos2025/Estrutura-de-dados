/*
10. Bucket Sort
O que é? O Bucket Sort (ou "ordenação por baldes") é um algoritmo de ordenação que funciona distribuindo os elementos
de um array em v"baldes" (buckets). Cada balde é então ordenado individualmente, seja usando um algoritmo de ordenação
diferente (como Insertion Sort) ou aplicando recursivamente o Bucket Sort. Finalmente, os elementos dos baldes ordenados
são concatenados para formar o array final ordenado.

Para que serve? articularmente eficiente quando os dados de entrada estão distribuídos de forma aproximadamente uniforme
sobre um intervalo. Frequentemente usado para ordenar números de ponto flutuante.

Vantagens:
Pode atingir complexidade de tempo linear O(n+k) no caso médio (onde 'n' é o número de elementos e 'k' é o número de
baldes), se os elementos estiverem uniformemente distribuídos e a ordenação dentro dos baldes for eficiente.
Pode ser mais rápido que algoritmos baseados em comparação O(n log n) sob condideais de distribuição.

Desvantagens:
O desempenho se degrada significativamente se os dados não estiverem uniformemente distribuídos (por exemplo, se a
maioria dos elementos cair em um único balde, a complexidade se aproxima da do algoritmo usado para ordenar os
baldes, podendo ser O(n²) no pior caso se Insertion Sort for usado em um balde grande).
Requer espaço adicional para os baldes, geralmente O(n+k).
Não é tão versátil quanto algoritmos de comparação; mais adequado para tipos de dados específicos (como números de
ponto flutuante em um intervalo conhecido).
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BucketSort {

    public static void bucketSort(float[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // 1. Criar 'n' baldes vazios.
        // Usaremos uma lista de listas (ArrayList de ArrayLists).
        // O número de baldes pode ser ajustado; aqui usamos 'n' baldes para simplicidade.
        @SuppressWarnings("unchecked") // Suprime o warning para o cast de List<Float>[]
        List<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>(); // Inicializa cada balde como uma nova ArrayList
        }

        // 2. Distribuir os elementos do array nos diferentes baldes.
        // Para cada elemento array[i]:
        //   - Calcula o índice do balde: (int) (n * array[i])
        //   - Adiciona array[i] ao balde buckets[indiceDoBalde].
        // Esta fórmula de índice assume que os valores em 'array' estão no intervalo [0, 1).
        // Se o intervalo for diferente, a fórmula de mapeamento para o índice do balde precisa ser ajustada.
        for (int i = 0; i < n; i++) {
            float valor = array[i];
            int indiceDoBalde = (int) (n * valor);
            // Caso especial: se valor for 1.0 (ou muito próximo), pode dar índice 'n',
            // então colocamos no último balde (n-1).
            if (indiceDoBalde >= n) {
                indiceDoBalde = n - 1;
            }
            buckets[indiceDoBalde].add(valor);
        }

        // 3. Ordenar cada balde individualmente.
        // Usaremos Collections.sort() para ordenar cada balde.
        // Collections.sort() usa TimSort, que é eficiente.
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // 4. Concatenar todos os baldes de volta no array original.
        int indice = 0; // Índice para percorrer o array original
        for (int i = 0; i < n; i++) { // Para cada balde
            for (float valor : buckets[i]) { // Para cada valor no balde atual
                array[indice++] = valor; // Coloca o valor de volta no array original
            }
        }
    }

    public static void main(String[] args) {
        float[] dados = {0.897f, 0.565f, 0.656f, 0.1234f, 0.665f, 0.3434f, 0.0f, 0.99f, 0.42f};
        System.out.println("Array antes da ordenação (Bucket Sort):");
        for (float valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        bucketSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Bucket Sort):");
        for (float valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        float[] dados2 = {0.42f, 0.32f, 0.33f, 0.52f, 0.37f, 0.47f, 0.51f};
        System.out.println("\nArray antes da ordenação (Bucket Sort) - dados2:");
        for (float valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        bucketSort(dados2);

        System.out.println("Array depois da ordenação (Bucket Sort) - dados2:");
        for (float valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}