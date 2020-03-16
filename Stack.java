public class Stack<T> {

    private class Node<T>{
        Node<T> next;
        T data;
    }

    private Node<T> top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T data) {
        Node<T> newTop = new Node<T>();
        newTop.data = data;
        newTop.next = top;
        top = newTop;
    }

    public T pop() {
        T data;
        if (!isEmpty()) {
            data = (T) top.data;
            top = top.next;
        } else {
            //throw exception
            data = (T) "No items";
        }

        return data;
    }
    public T peek() {
        T data;
        if (!isEmpty()) {
            data = (T) top.data;
        } else {
            //throw exception
            data = (T) "No items";
        }

        return data;
    }

    public static void main(String[] args) {
        //Testing...
        Stack<Integer> oStack = new Stack<Integer>();

        System.out.println("IsEmpty: " + oStack.isEmpty());
        oStack.push(5);
        oStack.push(510);
        System.out.println("Pop " + oStack.pop());
        System.out.println("Peek " + oStack.peek());
        System.out.println("Pop " + oStack.pop());
        System.out.println("Pop " + oStack.pop());
    }

}

