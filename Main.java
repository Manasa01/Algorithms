package com.sorting;

public class Main {

    public static void main(String[] args) {
        LinkedList oLL = new LinkedList();
        SelectionSort oSort = new SelectionSort();
        //Test Linked List
        /*
        System.out.println("is Empty" + oLL.isEmpty());
        System.out.println("peek" + oLL.peek());
        oLL.insert(5);
        System.out.println("traverse");
        oLL.traverse();
        System.out.println("peek" + oLL.peek());
        oLL.insert(10);
        oLL.insert(-1);
        oLL.insert(55);
        oLL.traverse();
        System.out.println("traverse");
        oLL.delete();
        oLL.delete();
        oLL.traverse();
    */
        //Selection Sort
/*
        oLL.insert(4);
        oLL.insert(5);
        oLL.insert(-1);
        oLL.insert(0);
        System.out.println("traversal before sort");
        oLL.traverse();
        Node head = oLL.getHead();
        oSort.selectionSortDataSwap(head);
        System.out.println("traversal after sort");
          oLL.traverse();
*/
/*
        //Selection Sort
        oLL.insert(4);
        oLL.insert(5);
        oLL.insert(-1);
        oLL.insert(0);
        System.out.println("traversal before sort");
        oLL.traverse();
        Node head = oLL.getHead();
        Node p1 = oLL.getNode(8);
        Node p2 = oLL.getNode(5);
        System.out.println("head " + head.data);
        System.out.println("p1 " + p1);
        System.out.println("p2 " + p2.data);
        head = oSort.swapNode(head, p1, p2);
        oLL.changeHead(head);
        head = oLL.getHead();
        System.out.println("head " + head.data);
        System.out.println("head " + head.data);
//        oSort.selectSortNodeSwap(head);
        System.out.println("traversal after sort");
        oLL.traverse();

 */
        //Selection Sort

        oLL.insert(4);
        oLL.insert(5);
        oLL.insert(-1);
        oLL.insert(0);
        oLL.insert(1);
        oLL.insert(121);
        oLL.insert(67);
        oLL.insert(81);
        oLL.insert(-45);
        oLL.insert(-16);
        System.out.println("traversal before sort");
        oLL.traverse();

        Node head = oLL.getHead();
        head = oSort.selectSortNodeSwap(head);
        oLL.changeHead(head);

        System.out.println("traversal after sort");
        oLL.traverse();

    }
}
