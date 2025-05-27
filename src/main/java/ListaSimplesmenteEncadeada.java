/*
Lista Encadeada (Singly Linked List)
O que é? Uma lista encadeada (ou lista ligada) é uma estrutura de dados linear e dinâmica onde os elementos não são
armazenados em posições de memória contíguas, como acontece em arrays. Em vez disso, cada elemento, chamado, de "nó"
(node), contém duas partes:
O dado (ou valor) que ele armazena.
Um ponteiro (ou referência) para o próximo nó na sequência. O primeiro nó é chamado de "cabeça" (head) e o último nó
aponta para null, indicando o fim da lista.

Para que serve? Serve, para armazenar uma coleção de elementos de forma ordenada (sequencialmente), permitindo inserções
e remoções eficientes, especialmente no início da lista, sem a necessidade de realocar toda a estrutura como em um
array. É útil quando o tamanho da coleção não é conhecido de antemão ou varia muito durante a execução.

Vantagens:
Tamanho Dinâmico: A lista pode crescer ou diminuir conforme necessário durante a execução, alocando memória apenas
para os nós que realmente existem.
Inserção/Remoção Eficiente no Início: Adicionar ou remover um elemento no início da lista é uma operação de t hempo
constante (O(1)), pois snvolve a atualização de alguns ponteiros.
Uso de Memória Flexível: Não há desperdício de memória com espaços não utilizados, como pode ocorrer em arrays com
capacidade pré-alocada.

Desvantagens:
Acesso Aleatório Lento: Para acessar um elemento em, uma posição específica (por exemplo, o k-ésimo elemento), é
necessário percorrer a lista desde a cabeça até esse elemento. Isso resulta em, um tempo de acesso linear (O(n)) no
pior caso.
Memória Adicional para Ponteiros: Cada nó requer espaço extra para armazenar o ponteiro para o próximo nó, o que pode
ser um overhead se os dados armazenados forem pequenos.
Percorrer para Trás é Ineficiente/Impossível: Em, uma lista simplesmente encadeada, não é possível navegar para o nó
anterior diretamente a partir de um nó atual; seria necessário começar da cabeça novamente.
 */

public class ListaSimplesmenteEncadeada {

    /**
     * Classe interna estática que representa um nó individual na lista encadeada.
     * Cada nó contém um dado e uma referência para o próximo nó na sequência.
     */
    private static class No {
        // O valor inteiro armazenado neste nó. Pode ser de qualquer tipo, mas aqui usamos int.
        int dado;
        // Uma referência (ponteiro) para o próximo nó na lista.
        // Se este for o último nó, 'proximo' será null.
        No proximo;

        /**
         * Construtor para criar um novo nó.
         * @param dado O valor a ser armazenado no nó.
         */
        public No(int dado) {
            this.dado = dado;
            this.proximo = null; // Por padrão, um novo nó não aponta para nenhum outro.
        }
    }

    // A 'cabeça' da lista. É uma referência para o primeiro nó.
    // Se a lista estiver vazia, 'cabeca' será null.
    private No cabeca;
    // Um contador para armazenar o número atual de nós na lista.
    private int tamanho;

    /**
     * Construtor para inicializar uma nova lista simplesmente encadeada.
     * A lista começa vazia.
     */
    public ListaSimplesmenteEncadeada() {
        this.cabeca = null; // A lista está vazia, então a cabeça é null.
        this.tamanho = 0;   // O tamanho inicial é zero.
    }

    /**
     * Verifica se a lista encadeada está vazia.
     * @return true se a lista não contém nenhum nó (cabeça é null), false caso contrário.
     */
    public boolean estaVazia() {
        // A lista está vazia se a cabeça for null.
        return cabeca == null;
    }

    /**
     * Retorna o número de elementos (nós) na lista.
     * @return O número de elementos na lista.
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     * Adiciona um novo elemento (dado) no início da lista.
     * Esta operação é eficiente, pois não requer percorrer a lista.
     * @param dado O valor a ser adicionado.
     */
    public void adicionarNoInicio(int dado) {
        // 1. Cria um novo nó com o dado fornecido.
        No novoNo = new No(dado); // (dado = 5)
        // Neste ponto:
        // novoNo: [dado: 5, proximo: null]

        // 2. O 'proximo' do novo nó aponta para o que era a 'cabeca' anteriormente.
        novoNo.proximo = cabeca;
        // Digamos que a lista era: cabeca -> [10 -> null]
        // Então, 'cabeca' apontava para o nó com valor 10.
        // Agora, novoNo.proximo também aponta para o nó com valor 10.
        // novoNo: [dado: 5, proximo: (referência para o nó 10)]
        // Lista ainda: cabeca -> [10 -> null]

        // 3. O novo nó se torna a nova 'cabeca' da lista.
        cabeca = novoNo;
        // Agora, 'cabeca' naponta mais para o nó 10.
        // 'cabeca' aponta para 'novoNo' (que tem o valor 5).
        // Lista agora: cabeca -> [5 -> (referência para o nó 10)] -> [10 -> null]

        // 4. Incrementa o contador de tamanho da lista.
        tamanho++;
        // Se o tamanho era 1, agora é 2.
    }

    /**
     * Adiciona um novo elemento (dado) no final da lista.
     * Esta operação requer percorrer a lista até o último nó.
     * @param dado O valor a ser adicionado.
     */
    public void adicionarNoFinal(int dado) {
        // 1. Cria um novo nó com o dado fornecido.
        No novoNo = new No(dado);

        // 2. Se a lista estiver vazia, o novo nó se torna a cabeça.
        if (estaVazia()) {
            cabeca = novoNo;
        } else {
            // 3. Caso contrário, precisamos encontrar o último nó.
            No atual = cabeca;
            // Percorre a lista enquanto o nó atual tiver um próximo.
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            // 4. Quando 'atual' for o último nó, seu 'proximo' agora aponta para o novo nó.
            atual.proximo = novoNo;
        }
        // 5. Incrementa o contador de tamanho da lista.
        tamanho++;
    }

    /**
     * Remove o elemento do início da lista.
     * @return O nó que foi removido, ou null se a lista estiver vazia.
     */
    public No removerDoInicio() {
        // 1. Verifica se a lista está vazia.
        if (estaVazia()) {
            System.out.println("A lista está vazia. Não é possível remover do início.");
            return null;
        }
        // 2. Guarda uma referência para o nó que será removido (a cabeça atual).
        No noRemovido = cabeca;
        // 3. A nova cabeça da lista se torna o próximo nó da cabeça antiga.
        cabeca = cabeca.proximo;
        // 4. Opcional: Quebra a ligação do nó removido para o resto da lista.
        // Isso ajuda o coletor de lixo a liberar a memória mais eficientemente.
        noRemovido.proximo = null;
        // 5. Decrementa o contador de tamanho da lista.
        tamanho--;
        // 6. Retorna o nó que foi removido.
        return noRemovido;
    }

    /**
     * Remove o elemento do final da lista.
     * Esta operação requer percorrer a lista até o penúltimo nó.
     * @return O nó que foi removido, ou null se a lista estiver vazia.
     */
    public No removerDoFinal() {
        // 1. Verifica se a lista está vazia.
        if (estaVazia()) {
            System.out.println("A lista está vazia. Não é possível remover do final.");
            return null;
        }

        No noRemovido;
        // 2. Se há apenas um elemento na lista (a cabeça é o único nó).
        if (cabeca.proximo == null) {
            noRemovido = cabeca; // O nó a ser removido é a própria cabeça.
            cabeca = null;      // A lista fica vazia.
        } else {
            // 3. Caso contrário, precisamos encontrar o penúltimo nó.
            No atual = cabeca;
            No anterior = null; // Mantém uma referência para o nó anterior ao 'atual'.

            // Percorre a lista até que 'atual.proximo' seja null, indicando que 'atual' é o último nó.
            while (atual.proximo != null) {
                anterior = atual; // 'anterior' guarda o nó antes de 'atual'.
                atual = atual.proximo; // 'atual' avança para o próximo nó.
            }
            // 4. 'atual' é agora o último nó, e 'anterior' é o penúltimo.
            noRemovido = atual; // O nó a ser removido é o 'atual'.
            // O penúltimo nó agora aponta para null, efetivamente removendo o último nó.
            if (anterior != null) {
                anterior.proximo = null;
            }
        }
        // 5. Decrementa o contador de tamanho da lista.
        tamanho--;
        // 6. Retorna o nó que foi removido.
        return noRemovido;
    }

    /**
     * Busca um elemento específico na lista.
     * @param dado O valor a ser buscado.
     * @return O nó que contém o dado, ou null se o dado não for encontrado.
     */
    public No buscar(int dado) {
        No atual = cabeca; // Começa a busca na cabeça da lista.
        // Percorre a lista enquanto o nó atual não for null.
        while (atual != null) {
            // Se o dado do nó atual for igual ao dado procurado, retorna o nó.
            if (atual.dado == dado) {
                return atual;
            }
            // Move para o próximo nó.
            atual = atual.proximo;
        }
        // Se o loop terminar, significa que o dado não foi encontrado.
        return null;
    }

    /**
     * Exibe todos os elementos da lista em ordem, começando da cabeça.
     */
    public void exibirLista() {
        if (estaVazia()) {
            System.out.println("Lista vazia.");
            return;
        }
        System.out.print("Lista: Cabeça -> ");
        No atual = cabeca; // Começa a exibição na cabeça.
        // Percorre a lista enquanto o nó atual não for null.
        while (atual != null) {
            System.out.print(atual.dado + " -> "); // Imprime o dado do nó.
            atual = atual.proximo; // Move para o próximo nó.
        }
        System.out.println("null (Fim)"); // Indica o final da lista.
    }

    /**
     * Método principal (main) para testar a implementação da Lista Simplesmente Encadeada.
     * Aqui você pode ver exemplos de uso de todos os métodos.
     */
    public static void main(String[] args) {
        // Cria uma nova instância da lista encadeada.
        ListaSimplesmenteEncadeada lista = new ListaSimplesmenteEncadeada();

        // Testa se a lista está vazia inicialmente e exibe a lista.
        System.out.println("Lista está vazia? " + lista.estaVazia()); // Esperado: true
        lista.exibirLista(); // Esperado: Lista vazia.

        // Adiciona elementos no início e no final.
        lista.adicionarNoInicio(10); // Lista: 10
        lista.adicionarNoInicio(5);  // Lista: 5 -> 10
        lista.adicionarNoFinal(20);  // Lista: 5 -> 10 -> 20
        lista.adicionarNoFinal(25);  // Lista: 5 -> 10 -> 20 -> 25

        System.out.println("\n--- Após adições ---");
        lista.exibirLista(); // Esperado: Lista: Cabeça -> 5 -> 10 -> 20 -> 25 -> null (Fim)
        System.out.println("Tamanho da lista: " + lista.getTamanho()); // Esperado: 4

        // Testa a busca de elementos.
        System.out.println("\n--- Buscando elementos ---");
        System.out.println("Buscando o elemento 10: " + (lista.buscar(10) != null ? "Encontrado" : "Não encontrado")); // Esperado: Encontrado
        System.out.println("Buscando o elemento 100: " + (lista.buscar(100) != null ? "Encontrado" : "Não encontrado")); // Esperado: Não encontrado

        // Testa a remoção do início.
        System.out.println("\n--- Removendo do início ---");
        No removidoInicio = lista.removerDoInicio(); // Remove 5
        if (removidoInicio != null) {
            System.out.println("Removido do início: " + removidoInicio.dado); // Esperado: 5
        }
        lista.exibirLista(); // Esperado: Lista: Cabeça -> 10 -> 20 -> 25 -> null (Fim)
        System.out.println("Tamanho da lista: " + lista.getTamanho()); // Esperado: 3

        // Testa a remoção do final.
        System.out.println("\n--- Removendo do final ---");
        No removidoFinal = lista.removerDoFinal(); // Remove 25
        if (removidoFinal != null) {
            System.out.println("Removido do final: " + removidoFinal.dado); // Esperado: 25
        }
        lista.exibirLista(); // Esperado: Lista: Cabeça -> 10 -> 20 -> null (Fim)
        System.out.println("Tamanho da lista: " + lista.getTamanho()); // Esperado: 2

        // Adiciona mais alguns elementos e exibe novamente.
        System.out.println("\n--- Adições finais ---");
        lista.adicionarNoInicio(1);  // Lista: 1 -> 10 -> 20
        lista.adicionarNoFinal(30); // Lista: 1 -> 10 -> 20 -> 30
        lista.exibirLista(); // Esperado: Lista: Cabeça -> 1 -> 10 -> 20 -> 30 -> null (Fim)
        System.out.println("Tamanho da lista: " + lista.getTamanho()); // Esperado: 4

        // Testa remover até a lista ficar vazia
        System.out.println("\n--- Removendo tudo ---");
        while (!lista.estaVazia()) {
            System.out.println("Removendo: " + lista.removerDoInicio().dado);
        }
        lista.exibirLista(); // Esperado: Lista vazia.
        System.out.println("Tamanho da lista: " + lista.getTamanho()); // Esperado: 0
    }
}