/*
Gnome Sort
O que é?
O Gnome Sort (também conhecido como "Stupid Sort" ou "Simple Sort") é um algoritmo de ordenação que se assemelha muito
ao Insertion Sort, mas de uma maneira mais simples de entender e implementar. Ele opera examinando elementos
adjacentes. Se eles estiverem na ordem correta, ele avança. Se estiverem na ordem errada, ele os troca e "recua" um
passo para garantir que a troca não desordenou o que já estava ordenado. Ele continua avançando até o final da lista ou
até encontrar uma troca que o faça recuar.

Para que serve?
Para ordenar uma coleção de itens. É uma alternativa simples ao Insertion Sort, especialmente útil para fins
educacionais devido à sua lógica intuitiva.

Vantagens:
Simples de implementar: A lógica é bastante direta, usando apenas um loop e algumas condições.
Adaptativo: Assim como o Insertion Sort, ele se comporta bem em listas quase ordenadas.
Estável: Mantém a ordem relativa de elementos com chaves iguais.
In-place: Não requer memória adicional significativa.

Desvantagens:
Ineficiente para listas grandes: A complexidade de tempo no pior caso e caso médio é O(n²), tornando-o impraticável
para grandes volumes de dados.
Muitas comparações e trocas: Pode ser menos eficiente que outros algoritmos O(n²) como o Bubble Sort em termos de
número total de operações em alguns cenários, devido ao "recuo" constante.
 */

public class GnomeSort {

    public static void gnomeSort(int[] array) {
        // Verifica se o array é nulo ou tem menos de 2 elementos.
        if (array == null || array.length < 2) {
            return;
        }

        int n = array.length; // Pega o tamanho do array
        int index = 0;       // Começa do início do array

        // Continua enquanto o índice não atingir o final do array
        while (index < n) {
            // Caso base: se estiver no início do array, avança.
            // Ou, se os elementos estão na ordem correta, avança.
            if (index == 0 || array[index] >= array[index - 1]) {
                index++; // Move para o próximo elemento
            } else {
                // Se os elementos estão na ordem errada (array[index] < array[index - 1])
                // Troca os elementos
                int temp = array[index];
                array[index] = array[index - 1];
                array[index - 1] = temp;

                // Move o índice para trás para verificar se a troca não desordenou
                // a parte anterior da lista
                index--;
            }
        }
    }

    public static void main(String[] args) {
        int[] dados = {12, 11, 13, 5, 6, 90, 25};
        System.out.println("Array antes da ordenação (Gnome Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();

        gnomeSort(dados); // Chama o método de ordenação

        System.out.println("Array depois da ordenação (Gnome Sort):");
        for (int valor : dados) {
            System.out.print(valor + " ");
        }
        System.out.println();
    }
}