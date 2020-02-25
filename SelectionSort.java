package com.sorting;

public class SelectionSort {

    public void selectionSortDataSwap(Node node) {

        while (node != null) {
            Node min = node;
            Node tmp = node.next;
            while (tmp != null) {
                if (tmp.data < min.data) {
                    min = tmp;
                }
                tmp = tmp.next;
            }
            if (node != min) {
                swapValue(node, min);
            }
            node = node.next;
        }
    }

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


    public void swapValue(Node tmp1, Node tmp2) {
        int tmp;
        tmp = tmp1.data;
        tmp1.data = tmp2.data;
        tmp2.data = tmp;
    }

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
