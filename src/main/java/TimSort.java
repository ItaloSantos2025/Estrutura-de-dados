/*
11. TimSort
O que é? O TimSort é um algoritmo de ordenação híbrido, derivado do Merge Sort e do Insertion Sort, projetado para ter
um bom desempenho em muitos tipos de dados do mundo real. Ele foi inventado por Tim Peters em 2002 para ser usado na
linguagem de programação Python e, desde então, foi adotado em outras plataformas, incluindo Java (para Arrays.sort() de
objetos e Collections.sort() a partir do Java 7) e Android. A ideia principal é identificar "runs" (sequências já
ordenadas ou inversamente ordenadas) no array de entrada. Runs curtos são estendidos e ordenados usando Insertion Sort.
Em seguida, esses runs ordenados são mesclados (merged) de forma eficiente usando uma variação do Merge Sort, que inclui
otimizações como "galloping" (galope) para acelerar a mesclagem quando um run consistentemente "ganha" do outro.

Para que serve? É um algoritmo de ordenação de propósito geral, altamente otimizado, estável e eficiente. É
especialmente bom para dados parcialmente ordenados, o que é comum em cenários práticos.

Vantagens:
Eficiente: Complexidade de tempo O(n log n) no pior caso e no caso médio. Para dados quase ordenados, pode se aproximar de O(n).
Estável: Mantém a ordem relativa de elementos com chaves iguais.
Adaptável: Tira proveito da ordem preexistente nos dados.
Bom uso de memória: Embora o Merge Sort tradicional precise de O(n) de espaço auxiliar, o TimSort tenta minimizar
isso, precisando de espaço para mesclar runs. No pior caso, pode ser O(n), mas para dados com boa estrutura de
runs, pode ser O(log n) ou até menos para o espaço temporário.

Desvantagens:
Complexidade de implementação: É significativamente mais complexo de implementar corretamente do zero em comparação
com algoritmos como QuickSort, MergeSort ou ShellSort. Envolve várias heurísticas e otimizações.
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TimSort {

    // Define o tamanho base para os "runs". Em implementações reais,
    // este valor (ou um "minrun" calculado) é tipicamente entre 32 e 65.
    // Para esta versão didática, usamos um valor fixo.
    static final int RUN = 32;

    /**
     * Ordena um subarray array[esquerda...direita] usando o algoritmo Insertion Sort.
     * É eficiente para arrays pequenos ou quase ordenados.
     *
     * @param array O array a ser ordenado.
     * @param esquerda O índice inicial do subarray.
     * @param direita O inal do subarray.
     */
    public static void insertionSort(int[] array, int esquerda, int direita) {
        for (int i = esquerda + 1; i <= direita; i++) {
            int elementoAtual = array[i];
            int j = i - 1;

            // Move os elementos de array[esquerda...j] que são maiores que elementoAtual
            // uma posição para a frente de suas posições atuais.
            while (j >= esquerda && array[j] > elementoAtual) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = elementoAtual; // Insere o elementoAtual em sua posição correta.
        }
    }

    /**
     * Mescla (merge) dois subarrays ordenados de array[].
     * O primeiro subarray é array[inicio...meio].
     * O segundo subarray é array[meio+1...fim].
     *
     * @param array O array contendo os subarrays a serem mesclados.
     * @param inicio O índice inicial do primeiro subarray.
     * @param meio O índice final do primeiro subarray.
     * @param fim O índice final do segundo subarray.
     */
    public static void merge(int[] array, int inicio, int meio, int fim) {
        // Calcula os tamanhos dos dois subarrays a serem mesclados.
        int tamanhoSubarrayEsquerdo = meio - inicio + 1;
        int tamanhoSubarrayDireito = fim - meio;

        // Cria arrays temporários para armazenar os dados dos subarrays.
        int[] subarrayEsquerdo = new int[tamanhoSubarrayEsquerdo];
        int[] subarrayDireito = new int[tamanhoSubarrayDireito];

        // Copia os dados para os arrays temporários.
        for (int i = 0; i < tamanhoSubarrayEsquerdo; i++) {
            subarrayEsquerdo[i] = array[inicio + i];
        }
        for (int j = 0; j < tamanhoSubarrayDireito; j++) {
            subarrayDireito[j] = array[meio + 1 + j];
        }

        // Índices para percorrer os subarrays temporários e o array original.
        int i = 0; // Índice para subarrayEsquerdo
        int j = 0; // Índice para subarrayDireito
        int k = inicio; // Índice para o array original (onde a mesclagem começa)

        // Mescla os arrays temporários de volta no array[inicio...fim].
        while (i < tamanhoSubarrayEsquerdo && j < tamanhoSubarrayDireito) {
            if (subarrayEsquerdo[i] <= subarrayDireito[j]) {
                array[k] = subarrayEsquerdo[i];
                i++;
            } else {
                array[k] = subarrayDireito[j];
                j++;
            }
            k++;
        }

        // Copia quaisquer elementos restantes de subarrayEsquerdo (se houver).
        while (i < tamanhoSubarrayEsquerdo) {
            array[k] = subarrayEsquerdo[i];
            i++;
            k++;
        }

        // Copia quaisquer elementos restantes de subarrayDireito (se houver).
        while (j < tamanhoSubarrayDireito) {
            array[k] = subarrayDireito[j];
            j++;
            k++;
        }
    }

    /**
     * Função principal que implementa o TimSort (versão simplificada).
     *
     * @param array O array a ser ordenado.
     */
    public static void timSort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return; // Já está ordenado ou não há o que ordenar.
        }
        // Se o array for muito pequeno, usar Insertion Sort diretamente é mais eficiente.
        if (n < RUN) {
            insertionSort(array, 0, n - 1);
            return;
        }

        // Passo 1: Ordenar "runs" individuais de tamanho RUN usando Insertion Sort.
        // O loop percorre o array em blocos de tamanho RUN.
        // Para cada bloco, chama insertionSort.
        // O último bloco pode ser menor que RUN.
        for (int i = 0; i < n; i += RUN) {
            // 'Math.min((i + RUN - 1), (n - 1))' garante que não ultrapassemos o final do array.
            // O índice final do run é o menor entre (incio do run + RUN - 1) e (último índice do array).
            insertionSort(array, i, Math.min((i + RUN - 1), (n - 1)));
        }

        // Passo 2: Começar a mesclar os "runs" ordenados.
        // Começa com o tamanho do run 'RUN' e dobra a cada iteração (RUN, 2*RUN, 4*RUN, ...).
        // O loop continua enquanto o tamanho do run atual for menor que 'n' (o tamanho total do array),
        // pois isso significa que ainda há runs para serem mesclados em runs maiores.
        for (int tamanhoRunAtual = RUN; tamanhoRunAtual < n; tamanhoRunAtual = 2 * tamanhoRunAtual) {
            // Pega o ponto de partida do subarray esquerdo a ser mesclado.
            // O loop percorre o array, pegando pares de subarrays (runs) para mesclar.
            // O incremento é de 2 * tamanhoRunAtual porque estamos processando dois runs de cada vez.
            for (int inicio = 0; inicio < n; inicio += 2 * tamanhoRunAtual) {
                // Encontra o ponto meio' é o final do primeiro subarray (run).
                // 'fim' é o final do segundo subarray (run).
                int meio = Math.min((inicio + tamanhoRunAtual - 1), (n - 1)); // Garante que 'meio' não ultrapasse o array
                int fim = Math.min((inicio + 2 * tamanhoRunAtual - 1), (n - 1)); // Garante que 'fim' não ultrapasse o array

                // Mescla array[inicio...meio] e array[meio+1...fim].
                // Só mescla se o segundo subarray (run) existir (ou seja, meio < fim).
                // Se meio >= fim, significa que não há um segundo run completo para mesclar ou
                // que estamos no final do array com apenas um run restante (que já está ordenado).
                if (meio < fim) {
                    merge(array, inicio, meio, fim);
                }
            }
        }
    }

    // Método main para testar o TimSort simplificado
    public static void main(String[] args) {
        System.out.println("--- Testando TimSort Simplificado ---");
        int[] dados1 = {5, 1, 4, 2, 8, 0, 3, 9, 6, 7, -1, 100, 55, 32, 67, 89, 12, 43, 76, 98, 23, 45, 65, 87, 19, 21, 37, 53, 71, 93, 11, 29, 41, 59, 79, 97};
        System.out.println("Array antes: " + Arrays.toString(dados1));
        timSort(dados1);
        System.out.println("Array depois: " + Arrays.toString(dados1));

        int[] dados2 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        System.out.println("\nArray antes: " + Arrays.toString(dados2));
        timSort(dados2);
        System.out.println("Array depois: " + Arrays.toString(dados2));

        int[] dados3 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nArray antes: " + Arrays.toString(dados3));
        timSort(dados3);
        System.out.println("Array depois: " + Arrays.toString(dados3));

        int[] dados4 = new int[70]; // Array maior para testar a mesclagem de runs
        for(int i=0; i < dados4.length; i++) {
            dados4[i] = (int)(Math.random() * 200 - 50); // Números aleatórios entre -50 e 150
        }
        System.out.println("\nArray grande antes: " + Arrays.toString(dados4));
        timSort(dados4);
        System.out.println("Array grande depois: " + Arrays.toString(dados4));

        int[] dados5 = {4, 7, 1}; // Array menor que RUN
        System.out.println("\nArray menor que RUN antes: " + Arrays.toString(dados5));
        timSort(dados5);
        System.out.println("Array menor que RUN depois: " + Arrays.toString(dados5));

        int[] dados6 = {}; // Array vazio
        System.out.println("\nArray vazio antes: " + Arrays.toString(dados6));
        timSort(dados6);
        System.out.println("Array vazio depois: " + Arrays.toString(dados6));

        int[] dados7 = {42}; // Array com um elemento
        System.out.println("\nArray com um elemento antes: " + Arrays.toString(dados7));
        timSort(dados7);
        System.out.println("Array com um elemento depois: " + Arrays.toString(dados7));


        System.out.println("\n--- Demonstração do TimSort da Biblioteca Padrão Java ---");
        // Exemplo com array de Objetos (Strings neste caso)
        String[] nomes = {"Alice", "David", "Charlie", "Bob", "Eve", "Fiona", "George"};
        System.out.println("Array de Strings antes da ordenação: " + Arrays.toString(nomes));
        // Arrays.sort() para objetos usa TimSort
        Arrays.sort(nomes);
        System.out.println("Array de Strings depois da ordenação: " + Arrays.toString(nomes));

        // Exemplo com Lista de Integers
        List<Integer> numeros = new ArrayList<>(Arrays.asList(50, 10, 90, 30, 70, 40, 80, 60, 20, 0, 100, 5, 15, 25, 35, 45, 55, 65, 75, 85, 95));
        System.out.println("\nLista de Integers antes da ordenação: " + numeros);
        // Collections.sort() usa TimSort
        Collections.sort(numeros);
        System.out.println("Lista de Integers depois da ordenação: " + numeros);
    }
}