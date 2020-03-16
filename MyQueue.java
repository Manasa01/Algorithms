/*
 * MyQueue implements a queue using two stacks
 *
 * Since push and enqueue are same, where insertion is from the top we have one stack for enqueue
 * For dequeue we need the first item in the stack, the first item becomes the last item of the second stack after pushing all items of first stack to second stack
 *
 * Create two stacks one for enqueue (enStack) and the other for dequeue (deStack)
 * To enqueue push val to enStack
 * If enStack is empty and deStack is not empty then push deStack vals to enStack and then enqueue
 *
 * To pop, push enStack vals to deStack and dequeue
 *
 * Also, the following can be done: for dequeue = push2 pop push1 and enqueue = just push1
 * Here, for dequeue = pop if not empty, enqueue = push if not empty; and dump values to another stack if one of them empty
 *  Here, the amortized time is O(1) for push and pop. worst is O(n) and best is O(1)
*/
public class MyQueue<T> {
    private Stack<T> enStack = new Stack<T>();
    private Stack<T> deStack = new Stack<T>();

    public void enqueue(T data) {
        if (enStack.isEmpty() && !deStack.isEmpty()) {
            while (!deStack.isEmpty()) {
                enStack.push(deStack.pop());
            }
        }
        enStack.push(data);
    }

    public T dequeue() {
        T data;
        if (!deStack.isEmpty()) {
            data = deStack.pop();
        } else {

            if (enStack.isEmpty()) {
                return (T) "No items";
            } else {
                while (!enStack.isEmpty()) {
                    deStack.push(enStack.pop());
                }
                data = deStack.pop();
            }
        }
        return data;
    }

    public static void main(String[] args){
        MyQueue<Integer> oQ = new MyQueue<Integer>();
        oQ.enqueue(12);
        oQ.enqueue(152);
        oQ.enqueue(66);
        oQ.enqueue(7);
        oQ.enqueue(-3);
        System.out.println("De: " + oQ.dequeue());
        System.out.println("De: " + oQ.dequeue());
        System.out.println("De: " + oQ.dequeue());
        oQ.enqueue(1532);
        oQ.enqueue(636);
        oQ.enqueue(73);
        System.out.println("De: " + oQ.dequeue());
        System.out.println("De: " + oQ.dequeue());
        System.out.println("De: " + oQ.dequeue());
        oQ.enqueue(-33);
        oQ.enqueue(6360);
        System.out.println("De: " + oQ.dequeue());
        oQ.enqueue(703);

    }
}
