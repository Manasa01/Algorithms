import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;
import java.lang.UnsupportedOperationException;

public class Deque<Item> implements Iterable<Item> {
    // pointers to first and last items of the doubly linked list
    private Node first = null;
    private Node last = null;
    //no of items in the dequeue
    private int size;

    //node of a doubly linked list
    private class Node {
        Item item;
        Node right; //next pointer corr. to the stack impl. (going forward)
        Node left; //next pointer corr. to the queue impl. (going backward)
    }

    // construct an empty deque
    public Deque() {
            first = new Node();
            last = first;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first.item == null;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) throws IllegalArgumentException {
        //null items are not allowed
        if (item == null) {
            throw new IllegalArgumentException("Add a non null value");
        } else {
            if (isEmpty()) {
                first.item = item;
            } else {
                //assign previous first to a new variable
                Node oldFirst = first;
                //create a new Node
                first = new Node();
                //insert the new item
                first.item = item;
                //make the first one point to the previous first
                first.right = oldFirst;
                first.left = null;
                oldFirst.left = first;
            }
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Add a non null value");
        }else {
            if (isEmpty()) {
                last.item = item;
            } else {
                //assign the previous last to a new variable
                Node oldLast = last;
                //create a new Node
                last = new Node();
                //insert the new Item
                last.item = item;
                //make the previous last to point to the last
                last.left = oldLast;
                last.right = null;
                oldLast.right = last;
            }
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() throws NoSuchElementException {
        //if notEmpty
        if (!isEmpty()) {
            //get the first item
            Item item = first.item;
            //make first point to the next
            first = first.right;
            first.left = null;
            size--;
            //To ensure that the last pointer points to null, when last item is removed
            if(size == 0){
                last = null;
            }
            //return the item
            return item;
        } else {
            throw new NoSuchElementException("The deque is empty");

        }
    }

    // remove and return the item from the back
    public Item removeLast() throws NoSuchElementException {
        //if notEmpty
        if (!isEmpty()) {
            //get the last item
            Item item = last.item;
            //make last point to the previous
            last = last.left;
            last.right = null;
            size--;
            //To ensure that the first pointer points to null, when last item is removed
            if(size == 0){
                first = null;
            }
            //return the item
            return item;
        } else {
            throw new NoSuchElementException("The deque is empty");
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() throws UnsupportedOperationException { /* not supported */
            throw new UnsupportedOperationException("The remove operation is not supported");
        }

        public Item next() throws NoSuchElementException {
            if (hasNext()) {
                Item item = current.item;
                current = current.right;
                return item;
            } else {
                throw new NoSuchElementException("Iteration is completed over all the items.");
            }
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> dequeue = new Deque<>();
        //Test: isEmpty?
        System.out.println("Expected empty status: true and Actual empty status: " + dequeue.isEmpty());

        //Test: values beyond Integer limit
        //Test: remove an item from the front of an empty deque
        //Test: remove an item from the back of an empty deque
        //Test: add a huge number of items
        //Test: remove a huge number of items
        //Test: Add and Remove items in a random sequence

        //Test: add elements

        dequeue.addLast(0);
        dequeue.addFirst(7);
        dequeue.addFirst(-1);
        dequeue.addFirst(9);
        dequeue.addLast(188);
        System.out.println("Expected size: 5 and Actual size: " + dequeue.size());
        //Expected order:  9 -1 7 0 188
        System.out.println("Expected list: 9 -1 7 0 188");
        //Test: Iteration
        Iterator<Integer> iterator;
        iterator = dequeue.iterator();
        System.out.println("Actual list:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //Test: remove elements
        dequeue.removeLast();
        dequeue.removeLast();
        dequeue.removeFirst();
        System.out.println("Expected size: 2 and Actual size: " + dequeue.size());
        //Expected list: -1 7
        System.out.println("Expected list: -1 7");
        //Test: Iteration
        iterator = dequeue.iterator();
        System.out.println("Actual list:");
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
/*
        //Test: Exception handling for iterator

        int sizeVal = dequeue.size();
        try {
            while (sizeVal > 0) { //should throw exception while trying to access an item after last item.
                iterator.next();
            }
        }
        catch(NoSuchElementException ex){
            System.out.println(ex);
        }

        //Test: Exception handling for removing the item from an empty list front
        //Test: Exception handling for removing the item from an empty list back
        //Test: Exception handling for remove operation of the iterator
        try
        {
            iterator.remove();
        }
        catch (UnsupportedOperationException ex){
            System.out.println(ex);
        }
*/
    }

}