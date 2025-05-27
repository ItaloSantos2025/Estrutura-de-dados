/*
8. Radix Sort
O que é? O Radix Sort é um algoritmo de ordenação não comparativo que ordena inteiros processando dígitos
individuais. Ele pode funcionar de duas maneiras: LSD (Least Significant Digit - dígito menos significativo primeiro) ou
MSD (Most Significant Digit - dígito mais significativo primeiro). A abordagem LSD é mais comum e é a que
implementaremos. Para cada posição de dígito (unidades, dezenas, centenas, etc.), ele usa um algoritmo de ordenação
estável (como o Counting Sort) para ordenar os números com base nesse dígito.

Para que serve? Para ordenar eficientemente listas de inteiros (ou strings, que podem ser tratadas como números em uma
base diferente). É particularmente útil quando os números têm um número fixo ou limitado de dígitos.

Vantagens:
Pode ser mais rápido que algoritmos de ordenação baseados em comparação (como QuickSort, MergeSort) para certos tipos
de dados, especialmente grandes conjuntos de inteiros.
Sua complexidade de tempo é O(d * (n + b)), onde 'd' é o número de dígitos no maior número, 'n' é o número de elementos
e 'b' é a base do sistema numérico (por exemplo, 10 para decimal). Se 'd' é constante e 'b' é proporcional a 'n' (ou
menor), a complexidade pode ser linear, O(n).
É estável se o algoritmo de ordenação subjacente (usado para cada dígito) for estável.

Desvantagens:
Pode ser mais complexo de implementar do que algoritmos de comparação simples.
O desempenho depende da escolha da base e do número de dígitos.
Requer espaço adicional para o algoritmo de ordenação auxiliar (por exemplo, O(n+b) se usar Counting Sort para os dígitos).
Menos flexível que os algoritmos de comparação; geralmente aplicado a inteiros ou dados que podem ser facilmente
mapeados para inteiros.
 */

import java.util.Arrays; // Para Arrays.fill() e, opcionalmente, para encontrar o máximo com Streams

public class RadixSort {

    // Função auxiliar para obter o maior valor no array.
    // Isso é necessário para saber o número máximo de dígitos que precisaremos processar.
    private static int getMax(int[] array, int n) {
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    // Função para realizar o Counting Sort em 'array[]' de acordo com
    // o dígito representado por 'exp' (expoente, ex: 1 para unidades, 10 para dezenas, 100 para centenas...).
    private static void countingSortPorDigito(int[] array, int n, int exp) {
        int[] output = new int[n]; // Array de saída que terá os números ordenados pelo dígito atual
        int[] count = new int[10]; // Array de contagem para os dígitos (0 a 9, pois estamos na base 10)
        // Arrays.fill(count, 0); // Não é estritamente necessário, pois arrays de int são inicializados com 0 em Java.

        // 1. Armazena a contagem de ocorrências dos dígitos em count[]
        // (array[i] / exp) % 10  obtém o dígito na posição 'exp'.
        // Ex: Se array[i] = 173 e exp = 1, (173/1)%10 = 3 (unidades)
        // Ex: Se array[i] = 173 e exp = 10, (173/10)%10 = 17%10 = 7 (dezenas)
        // Ex: Se array[i] = 173 e exp = 100, (173/100)%10 = 1%10 = 1 (centenas)
        for (int i = 0; i < n; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // 2. Modifica count[i] para que count[i] agora contenha
        // a posição real (cumulativa) deste dígito no array output[].
        // count[i] armazenará o número de elementos menores ou iguais ao dígito i.
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // 3. Constrói o array de saída (output[])
        // Percorre o array de entrada de trás para frente para manter a estabilidade do Counting Sort.
        for (int i = n - 1; i >= 0; i--) {
            int digitoAtual = (array[i] / exp) % 10;
            // A posição no output é count[digitoAtual] - 1 (índice baseado em zero)
            output[count[digitoAtual] - 1] = array[i];
            count[digitoAtual]--; // Decrementa para a próxima ocorrência do mesmo dígito
        }

        // 4. Copia o array de saída (output[]) para o array original (array[]),
        // de modo que array[] agora contenha os números ordenados pelo dígito atual.
        for (int i = 0; i < n; i++) {
            array[i] = output[i];
        }
        // Alternativa: System.arraycopy(output, 0, array, 0, n);
    }

    // Método principal que implementa o Radix Sort
    public static void radixSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // 1. Encontra o número máximo para saber o número de dígitos.
        // (Este exemplo assume números não negativos)
        int max = getMax(array, n);

        // 2. Faz o Counting Sort para cada dígito.
        // 'exp' é o expoente que representa a casa decimal atual (1, 10, 100...).
        // O loop continua enquanto houver dígitos a serem processados (enquanto max / exp > 0).
        // Para cada 'exp', chamamos countingSortPorDigito para ordenar os elementos
        // com base no dígito correspondente a essa casa decimal.
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortPorDigito(array, n, exp);
        }
    }

    public static void main(String[] args) {
        int[] dados = {170, 45, 75, 90, 802, 24, 2, 66, 901, 0};
        System.out.println("Array antes da ordenação (Radix Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        radixSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Radix Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("------------------------------------");

        int[] dados2 = {10, 21, 1, 5, 303, 2, 99, 1000, 0, 88, 7};
        System.out.println("Array antes da ordenação (Radix Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        radixSort(dados2);

        System.out.println("Array depois da ordenação (Radix Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}