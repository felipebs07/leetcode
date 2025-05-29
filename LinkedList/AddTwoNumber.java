package LinkedList;

class ListNode {
  int val;
  ListNode next;
  ListNode() {}
  ListNode(int val) { this.val = val; }
  ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Solution {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        // 1. Criação do Nó "Dummy" (ou Sentinela)
        //    'node1' é um nó inicial temporário, frequentemente chamado de "dummy head" ou "sentinel node".
        //    Ele não fará parte da lista de resultado final, mas serve como um ponto de ancoragem
        //    para começar a construir a nova lista. O valor (0 aqui) é irrelevante, pois
        //    o resultado real começará em 'node1.next'.
        //    Isso simplifica o código, pois não precisamos tratar o primeiro nó a ser adicionado
        //    de forma especial.
        ListNode dummy = new ListNode(0);

        // 2. Ponteiro de Construção (ou "Cauda")
        //    'node2' (poderia se chamar 'currentNode' ou 'tail') será o ponteiro que percorre
        //    a nova lista que estamos construindo. Ele sempre apontará para o ÚLTIMO nó adicionado
        //    à lista resultado. Inicialmente, ele aponta para o nosso nó dummy 'node1'.
        ListNode currentNode = dummy;

        // 3. Variável de "Vai um" (Carry)
        //    'carry' armazenará o valor que "sobe" para a próxima casa decimal na soma,
        //    assim como fazemos na adição manual no papel.
        int carry = 0;

        // 4. Loop Principal
        //    O loop continua enquanto houver dígitos em 'l1' OU em 'l2' OU se ainda existir
        //    um 'carry' (vai um) da soma anterior. Isso garante que todos os dígitos
        //    sejam processados e que qualquer 'carry' final seja incluído.
        while (l1 != null || l2 != null || carry != 0) {
            int digit1 = (l1 != null) ? l1.val : 0;
            int digit2 = (l2 != null) ? l2.val : 0;

            int sum = digit1 + digit2 + carry;
            int digit = sum % 10;
            carry = sum / 10;

            // 1. Criação do Novo Nó para o Resultado
            //    Cria um novo nó da lista (LinkedList.ListNode) contendo o 'digit' calculado.
            ListNode newNode = new ListNode(digit);

            // 2. Ligando o Novo Nó à Lista Resultado
            //     Aqui está a "mágica" de construir a lista:
            //     O 'next' do ÚLTIMO nó atual da lista resultado ('node2') agora aponta
            //     para o 'newNode' que acabamos de criar.
            //     Na primeira iteração, 'node2' é 'node1' (o dummy), então 'node1.next'
            //     passa a ser o primeiro nó real da soma.
            currentNode.next = newNode;

            // 3. Avançando o Ponteiro de Construção
            //     Move 'node2' para que ele agora aponte para o 'newNode' que foi adicionado.
            //     Assim, 'node2' continua sendo o último nó da lista resultado, pronto
            //     para que o próximo 'newNode' seja ligado a ele.
            currentNode = currentNode.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        ListNode result = dummy.next;
        dummy.next = null;
        return result;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        addTwoNumbers(l1, l2);
    }
}

