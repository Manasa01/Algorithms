package mydatastructures;

/* mydatastructures.Queue implementation using Linked List

 */
public class Queue<T> {

    private class Node<T> {
        T data;
        Node<T> next;
    }

    private Node<T> first;
    private Node<T> last;

    public boolean isEmpty() {
        return first == null && last == null;
    }

    public void queueTraversal() {
        Node<T> tmp = first;
        while (tmp != null) {
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }

    public void enQueue(T data) {

        //create a new node
        Node<T> tmp = new Node<T>();
        tmp.data = data;
        //if no nodes
        if (isEmpty()) {
            last = tmp;
            first = tmp;
            // update first and last to new Node
        } else {
            // if nodes exist
            last.next = tmp;
            last = tmp;
            //add the new node to the end
            // update last
        }
    }

    public T deQueue() {
        if (!isEmpty()) {
            //if not empty
            // remove the value in first
            T val = first.data;
            //update first
            first = first.next;
            //if its last item
            if (first == null) {
                // then make last equal to null along with first
                last = null;
            }
            return val;
        } else {
            //if empty
            //show message no more items, Q is empty
            return (T)"Q is empty";
        }
    }

    public static void main(String args[]) {

        Queue<Integer> oQ = new Queue<Integer>();

        System.out.println("IS EMPTY? " + oQ.isEmpty());

        oQ.enQueue(5);
        System.out.println("IS EMPTY? " + oQ.isEmpty());
        oQ.queueTraversal();

        oQ.deQueue();
        System.out.println("IS EMPTY? " + oQ.isEmpty());
        oQ.queueTraversal();

        oQ.enQueue(15);
        oQ.enQueue(5343);
        oQ.enQueue(5425);
        oQ.enQueue(5432);
        oQ.enQueue(5398);
        oQ.enQueue(5243);
        oQ.enQueue(5242);
        oQ.enQueue(5234523);
        System.out.println("IS EMPTY? " + oQ.isEmpty());
        //oQ.queueTraversal();

        while(!oQ.isEmpty()){
            System.out.println(oQ.deQueue());
        }
        System.out.println("IS EMPTY? " + oQ.isEmpty());

    }
}
