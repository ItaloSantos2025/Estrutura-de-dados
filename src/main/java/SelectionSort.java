/*
2. Selection Sort
O que é? O Selection Sort é um algoritmo de ordenação que divide a lista em duas partes: a sublista ordenada e a
sublista não ordenada. A cada passo, ele encontra o menor (ou maior) elemento na sublista não ordenada e o move para
o final da sublista ordenada.

Para que serve? Para ordenar uma coleção de itens. Assim como o Bubble Sort, é simples de entender, mas não muito
eficiente para grandes volumes de dados.

Vantagens:
Simples de implementar.
Faz no máximo O(n) trocas, o que pode ser útil se as operações de escrita (trocas) forem caras.
Desempenho consistente, pois sempre faz O(n²) comparações, independentemente da ordenação inicial dos dados.
Ordenação "in-place".

Desvantagens:
Ineficiente para grandes listas devido à sua complexidade de tempo O(n²) para comparações.
Não é adaptativo, ou seja, mesmo que a lista esteja quase ordenada, ele ainda realizará o mesmo número de comparações.
 */

public class SelectionSort {

    public static void selectionSort(int[] array) {
        // Verifica se o array ulo ou tem menos de 2 elementos,
        // pois nesse caso já está "ordenado" ou não há o que ordenar.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array

        // Loop externo: percorre o array para colocar cada elemento em sua posição correta.
        // O loop vai até n-1 porque o último elemento (o n-ésimo) automaticamente
        // estará na posição correta quando todos os outros n-1 elementos estiverem ordenados.
        for (int i = 0; i < n - 1; i++) {
            // Assume que o primeiro elemento da porção não ordenada (a partir do índice 'i')
            // é o menor. 'indiceDoMenor' guarda o índice desse suposto menor elemento.
            int indiceDoMenor = i;

            // Loop interno: percorre a parte não ordenada do array (de i+1 até o final)
            // para encontrar o índice do menor elemento real nessa sublista.
            for (int j = i + 1; j < n; j++) {
                // Se encontrar um elemento (array[j]) que seja menor que o atual
                // 'menor' (array[indiceDoMenor]), atualiza 'indiceDoMenor'.
                if (array[j] < array[indiceDoMenor]) {
                    indiceDoMenor = j;
                }
            }

            // Após o loop interno, 'indiceDoMenor' contém o índice do menor elemento
            // na porção não ordenada do array (de 'i' até 'n-1').

            // Se o menor elemento encontrado (array[indiceDoMenor]) não for o elemento
            // que está no início da porção não ordenada (array[i]), então troca-os.
            // Esta verificação (if indiceDoMenor != i) é uma pequena otimização para evitar
            // uma troca desnecessária se o elemento já estiver na posição correta.
            if (indiceDoMenor != i) {
                int temp = array[i];                // Armazena o valor de array[i] em uma variável temporária.
                array[i] = array[indiceDoMenor];    // Coloca o menor elemento encontrado (array[indiceDoMenor]) na posição array[i].
                array[indiceDoMenor] = temp;        // Coloca o valor original de array[i] na posição onde o menor estava.
            }
            // Neste ponto, o elemento em array[i] está em sua posição final ordenada.
            // A porção ordenada do array (da esquerda para a direita) cresceu em um elemento.
        }
    }

    public static void main(String[] args) {
        int[] dados = {64, 25, 12, 22, 11, 90, 34};
        System.out.println("Array antes da ordenação (Selection Sort) - dados1:");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        selectionSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Selection Sort) - dados1:");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("------------------------------------");

        int[] dados2 = {5, 1, 4, 2, 8, -3, 0};
        System.out.println("Array antes da ordenação (Selection Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();

        selectionSort(dados2); // Chama o método de ordenação para o segundo array

        System.out.println("Array depois da ordenação (Selection Sort) - dados2:");
        for (int valor : dados2) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}