/*
7. Counting Sort
O que é? O Counting Sort é um algoritmo de ordenação não baseado em comparações. Ele opera contando o número de
ocorrências de cada valor distinto no array de entrada. Essas contagens são então usadas para determinar as posições
de cada elemento no array de saída ordenado.

Para que serve? É muito eficiente para ordenar coleções de itens (geralmente inteiros) quando o intervalo de valores
dos itens (k) não é significativamente maior que o número de itens (n).

Vantagens:
Complexidade de tempo linear O(n + k), onde n é o número de elementos e k é o intervalo dos valores de
entrada (max - min + 1). Se k = O(n), a complexidade é O(n).
É estável se implementado corretamente (elementos com chaves iguais mantêm sua ordem relativa).
Simples de entender para o caso de inteiros não negativos.

Desvantagens:
Não é eficiente se o intervalo k dos valores de entrada for muito grande, pois requer um array auxiliar de contagem
de tamanho k, o que levaria a um alto consumo de memória (O(k)).
Geralmente do para inteiros ou para dados que podem ser mapeados para inteiros dentro de um intervalo razoável.
Não é um algoritmo de ordenação de propósito geral como QuickSort ou MergeSort.
 */

import java.util.Arrays; // Necessário para Arrays.stream().max().getAsInt() se for usar, e Arrays.fill()

public class CountingSort {

    public static void countingSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos, pois nesse caso já está "ordenado".
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length;

        // 1. Encontrar o maior elemento no array.
        // Isso é necessário para determinar o tamanho do array de contagem.
        // Esta implementação assume que todos os números são não negativos.
        int max = array[0];
        for (int i = 1; i < n; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        // Se fosse usar Streams (Java 8+):
        // int max = Arrays.stream(array).max().orElseThrow(() -> new IllegalArgumentException("Array não pode ser vazio para encontrar o máximo"));


        // 2. Criar o array de contagem (countArray).
        // O tamanho será max + 1 para que possamos usar os próprios números como índices (de 0 a max).
        // Este array armazenará a frequência de cada elemento.
        int[] countArray = new int[max + 1];
        // Em Java, arrays de inteiros são inicializados com 0 por padrão, então Arrays.fill(countArray, 0) não é estritamente necessário.

        // 3. Armazenar a contagem de cada elemento.
        // Percorre o array de entrada e, para cada elemento, incrementa a contagem na posição correspondente do countArray.
        for (int i = 0; i < n; i++) {
            // Se fosse necessário tratar números negativos, aqui seria um ponto de ajuste ou verificação.
            // Para esta versão, assumimos não negativos.
            countArray[array[i]]++;
        }

        // 4. Modificar o countArray para que cada elemento em um determinado índice
        // armazene a soma das contagens anteriores.
        // Agora, countArray[i] conterá o número de elementos menores ou iguais a i.
        // Isso nos dará a posição final (cumulativa) de cada elemento no array ordenado.
        for (int i = 1; i <= max; i++) {
            countArray[i] += countArray[i - 1];
        }

        // 5. Criar um array de saída (outputArray) para armazenar os elementos ordenados.
        // Ele terá o mesmo tamanho do array original.
        int[] outputArray = new int[n];

        // 6. Construir o array de saída.
        // Percorremos o array original de trás para frente (da direita para a esquerda).
        // Fazer isso de trás para frente mantém a estabilidade do algoritmo de ordenação
        // (ou seja, elementos com o mesmo valor mantêm sua ordem relativa original).
        for (int i = n - 1; i >= 0; i--) {
            int valorAtual = array[i]; // Pega o elemento atual do array de entrada.

            // A posição correta para 'valorAtual' no 'outputArray' é dada por 'countArray[valorAtual] - 1'.
            // Subtraímos 1 porque os índices do array são baseados em zero.
            // Por exemplo, se countArray[valorAtual] é 3, significa que há 3 elementos <= valorAtual,
            // então 'valorAtual' deve ir para o índice 2 do outputArray.
            int posicaoCorreta = countArray[valorAtual] - 1;
            outputArray[posicaoCorreta] = valorAtual;

            // Após colocar o elemento em sua posição correta, decrementamos a contagem em countArray[valorAtual].
            // Isso é feito para que a próxima ocorrência do mesmo número (se houver)
            // seja colocada na posição anterior correta.
            countArray[valorAtual]--;
        }

        // 7. Copiar os elementos ordenados do outputArray de volta para o array original.
        // O array original 'array' agora conter elementos ordenados.
        for (int i = 0; i < n; i++) {
            array[i] = outputArray[i];
        }
        // Uma alternativa mais eficiente para copiar arrays em Java:
        // System.arraycopy(outputArray, 0, array, 0, n);
    }

    public static void main(String[] args) {
        int[] dados = {4, 2, 2, 8, 3, 3, 1, 0, 7, 0, 5};
        System.out.println("Array antes da ordenação (Counting Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        countingSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Counting Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("------------------------------------");

        int[] dados2 = {1, 4, 1, 2, 7, 5, 2, 9, 0};
        System.out.println("Array antes da ordenação (Counting Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        countingSort(dados2);

        System.out.println("Array depois da ordenação (Counting Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("------------------------------------");

        int[] dados3 = {9, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 0, 0, 9, 2};
        System.out.println("Array antes da ordenação (Counting Sort) - dados3:");
        for (int valor : dados3) {
            System.out.print(valor + " ");
        }
        System.out.println();

        countingSort(dados3);

        System.out.println("Array depois da ordenação (Counting Sort) - dados3:");
        for (int valor : dados3) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}