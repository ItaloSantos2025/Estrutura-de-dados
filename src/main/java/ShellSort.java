/*
Shell Sort
O que é? O Shell Sort é uma generalização eficiente do Insertion Sort. A ideia principal é permitir a troca de itens
que estão distantes um do outro. Ele começa comparando e trocando elementos que estão a uma certa distância (chamada
de "gap" ou "intervalo"). Depois, gradualmente reduz esse intervalo até que o intervalo seja 1. Quando o intervalo
é 1, o Shell Sort atua como um Insertion Sort comum, mas como os elementos já estão "quase ordenados" devido às
passagens anteriores com intervalos maiores, esta última passagem é muito eficiente.

Para que serve? Serve para ordenar coleções de itens e é consideravelmente mais rápido que o Bubble Sort, Selection Sort
e Insertion Sort simples para listas de tamanho médio. É uma boa opuando a simplicidade de implementação é
desejada, mas um desempenho melhor que O(n²) é necessário.

Vantagens:
Mais eficiente que os algoritmos de ordenação simples O(n²) para a maioria dos casos, especialmente para listas de tamanho moderado.
algoritmo de ordenação "in-place" (requer uma quantidade mínima de memória auxiliar, O(1)).
Relativamente simples de implementar comparado a algoritmos como QuickSort ou MergeSort.

Desvantagens:
A complexidade de tempo do Shell Sort depende muito da sequência de intervalos ("gaps") utilizada. Não é trivial
determinar a melhor sequência de gaps.
Não é um algoritmo estável (a ordem relativa de elementos com chaves iguais pode ser alterada).
Para listas muito grandes, algoritmos com complexidade O(n log n) garantida (como Merge Sort ou Heap Sort) geralmente
são preferíveis.
 */

public class ShellSort {

    public static void shellSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array

        // Calcula o intervalo inicial 'h' usando a sequência de Knuth (3x + 1).
        // O intervalo deve ser menor que n/3.
        int h = 1;
        while (h < n / 3) {
            h = 3 * h + 1; // 1, 4, 13, 40, 121, ...
        }

        // Inicia o processo de ordenação com o maior intervalo 'h' e vai diminuindo.
        while (h >= 1) {
            // Realiza um "h-sort" no array. É como um Insertion Sort,
            // mas em vez de comparar elementos adjacentes (distância 1),
            // compara elementos que estão a uma distância 'h'.
            // Este loop percorre os elementos que iniciam cada "sublista" h-ordenada.
            for (int i = h; i < n; i++) {
                // 'temp' armazena o elemento atual que será inserido na sua posição correta
                // dentro da sublista h-ordenada.
                int temp = array[i];
                int j;

                // Desloca os elementos anteriores da sublista h-ordenada que são maiores que 'temp'
                // para a direita, abrindo espaço para 'temp'.
                // Compara array[i] (ou 'temp') com array[i-h], array[i-2h], e assim por diante.
                for (j = i; j >= h && array[j - h] > temp; j -= h) {
                    array[j] = array[j - h]; // Desloca o elemento maior para a direita
                }
                // Insere 'temp' na sua posição correta.
                array[j] = temp;
            }
            // Reduz o intervalo para a próxima passagem.
            // (h-1)/3 é o inverso da sequência de Knuth para obter o próximo h menor.
            h = (h - 1) / 3;
        }
    }

    public static void main(String[] args) {
        int[] dados = {35, 33, 42, 10, 14, 19, 27, 44, 26, 31};
        System.out.println("Array antes da ordenação (Shell Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        shellSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Shell Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        int[] dados2 = {12, 11, 13, 5, 6, 7, 0, -1, 100};
        System.out.println("\nArray antes da ordenação (Shell Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        shellSort(dados2);

        System.out.println("Array depois da ordenação (Shell Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}