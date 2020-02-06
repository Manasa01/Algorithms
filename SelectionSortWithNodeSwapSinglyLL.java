/*
 *   Program to perform selection sort on a singly linked list by swapping the nodes of the list.
 *
 * */
/*
     Node class consists of the definition of a node: data and next pointer
 */
class Node {
    Node next;
    int data;
}

/*
     LinkedList class consists of the methods needed for the linked list relative to selection sort
     Especially, insertion and changeHead methods and traversal
 */
class LinkedList {
    private Node next;
    private Node head;

    private void constructor() {
        head = new Node();
    }

    public void insert(int data) {
        Node newTmp = new Node();
        newTmp.data = data;
        newTmp.next = head;
        head = newTmp;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    Node getHead() {
        return head;
    }

    void changeHead(Node newHead) {
        head = newHead;
    }

    public void traverse() {
        Node node = head;
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
    }
}

/*
    Selection sort on singly linked list by swapping the nodes.
 */
class SelectionSort {

    // Selection sort on singly linked list by swapping the nodes.
    public Node selectSortNodeSwap(Node node) {
        Node head = node;
        Node prev1 = null;   // prev1 is the previous node for node
        Node prev2 = node;   // prev2 is previous node for min
        Node prevTmp;        // prevTmp is previous node for temp

        while (node != null) {   // for all nodes
            Node min = node;     // initialize the node containing minimum value
            Node temp = node.next;
            prevTmp = node;
            while (temp != null) {  // for all nodes after node
                if (temp.data < min.data) {
                    min = temp;
                    prev2 = prevTmp;
                }
                prevTmp = temp;
                temp = temp.next;
            }
            if (node != min) {
                //head can change during swapping, return this head and reassign it in the linked list instance
                head = swapNode(head, prev1, prev2);
            }
            node = min.next;   //min is in the node position after swapping, hence node = min.next
            prev1 = min;
        }
        return head;
    }

    //method to swap the nodes when values of the node are not in order
    public Node swapNode(Node head, Node prev1, Node prev2) {
        Node n1;
        Node n2 = prev2.next;
        //swap the next pointer for previous nodes
        if (prev1 == null) {  //for head prev1 is initialized to null
            n1 = head;
            head = n2;   //assign new head value in the local var and return it to reassign the head in the linked list instance
        } else {
            n1 = prev1.next;
            prev1.next = n2;
        }

        prev2.next = n1;

        //swap the next pointer for the nodes

        Node tmp = n1.next;
        n1.next = n2.next;
        n2.next = tmp;
        return head;
    }

}

public class Main {

    public static void main(String[] args) {
        //new linked list object
        LinkedList oLL = new LinkedList();
        //selection sort object
        SelectionSort oSort = new SelectionSort();
        //insert values to the linked list
        oLL.insert(4);
        oLL.insert(5);
        oLL.insert(-1);
        oLL.insert(0);
        oLL.insert(1);
        oLL.insert(0);
        oLL.insert(67);
        oLL.insert(81);
        oLL.insert(-45);
        oLL.insert(-16);
        oLL.insert(-112);
        oLL.insert(454);
        oLL.insert(133);
        oLL.insert(23);
        oLL.insert(65);
        oLL.insert(-81);
        oLL.insert(-35);
        oLL.insert(17);
        oLL.insert(2300);
        oLL.insert(6135);
        oLL.insert(-812);
        oLL.insert(-5435);
        //Traversal before sort
        System.out.println("-----------------------------------------");
        System.out.println("Singly linked list traversal before sort");
        oLL.traverse();
        //Sort using selection sort
        Node head = oLL.getHead();
        head = oSort.selectSortNodeSwap(head);
        oLL.changeHead(head);
        //traversal after sort
        System.out.println("");
        System.out.println("-----------------------------------------");
        System.out.println("Singly linked list traversal after sort");
        oLL.traverse();
        System.out.println("");
        System.out.println("-----------------------------------------");

    }
}
