package com.sorting;

public class LinkedList {
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

    public void delete() {

        if (!this.isEmpty()) {

            Node tmp = head;
            //deletion
            head = head.next;
            //cleaning..
            tmp.data = 0;
            tmp.next = null;
            tmp = null;
        }
    }

    int peek() {
        if (!this.isEmpty()) return head.data;
        return 0; // throw exception
    }

    Node getHead() {
        return head;
    }

    Node getNode(int data) {
        Node node = head;
        while (node != null && node.data != data) {
            node = node.next;
        }
        return node;
    }

    void changeHead(Node newHead) {
        head = newHead;
    }

    public void traverse() {
        Node node = head;
        while (node != null) {
            System.out.println(node.data);
            node = node.next;
        }
    }

    public void insertBefore(int data) {
    }

    public void insertAfter(int data) {
    }

    public void deleteAfter() {
    }

    public void deleteBefore() {
    }
}
