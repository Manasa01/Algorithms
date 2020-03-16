package mydatastructures;

/*
 * Binary Search Tree implementation
 * Student name : Manasa M Bhat
 * Net Id: mmb190005
 */
public class BinarySearchTree {
    /* Tree node definition */
    private class Node {
        int data;
        Node left;
        Node right;
    }

    /* root node */
    private Node root;

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /* *************************************Insert data by creating new tree node**************************** */
    public void insertToTree(Node tmp, int data) {
        //if root  value < data, then go left
        // else go right
        // till you get null node which is a leaf node, then insert

        if (tmp == null) {
            tmp = insertValue(data);
            //for the first insert, assign the root variable too
            if (root == null) {
                root = tmp;
            }
            return;
        }
        if (tmp.data > data) {
            if (tmp.left == null) {
                tmp.left = insertValue(data);
                return;
            }
            insertToTree(tmp.left, data);
        } else {
            if (tmp.right == null) {
                tmp.right = insertValue(data);
                return;
            }
            insertToTree(tmp.right, data);
        }

    }

    private Node insertValue(int val) {
        //create a new node
        Node tmp = new Node();
        //insert the value
        tmp.data = val;
        return tmp;
    }

    /* ********************************* Deletion of node using predecessor ******************************** */
    public void deleteWithPredecessor(int data) {
        //locate the key
        Node dataNode = findNodeWithData(root, data);   //For duplicates: The first data value which equals given value is deleted
        if (dataNode != null) { // if the node exists
            //if its a leaf node, just delete it
            if (dataNode.left == null && dataNode.right == null) {
                Node parent = getParent(dataNode, root);
                if (parent == null) {
                    //for root node
                    root = null;
                }
                if (parent.left == dataNode) parent.left = null;
                else parent.right = null;
            }
            //find the predecessor
            Node pred = getPredecessor(dataNode);
            //replace the key with predecessor
            if (pred != null) {
                //if left child exists then make it the right child of its parent
                dataNode.data = pred.data;

                Node parent = getParent(pred, root);
                if (parent == dataNode) {
                    parent.left = pred.left;
                } else {
                    parent.right = pred.left;
                }
            } else {// if no predecessor - delete with successor
                deleteWithSuccessor(data);
            }
        }
    }

    /* ************************************** Deletion of node using successor ********************************* */
    public void deleteWithSuccessor(int data) {
        //locate the key
        Node dataNode = findNodeWithData(root, data); //For duplicates: The first data value which equals given value is deleted
        if (dataNode != null) { //if this node exists
            //if its a leaf node, just delete it
            if (dataNode.left == null && dataNode.right == null) {
                Node parent = getParent(dataNode, root);
                if (parent == null) {
                    //for root node
                    root = null;
                }
                if (parent.left == dataNode) parent.left = null;
                else parent.right = null;
            }
            //find the successor
            Node succ = getSuccessor(dataNode);
            //replace the key with predecessor
            if (succ != null) {
                //if left child exists then make it the right child of its parent
                dataNode.data = succ.data;

                Node parent = getParent(succ, root);
                if (parent == dataNode) {
                    parent.right = succ.right;
                } else {
                    parent.left = succ.right;
                }
            } else {// if no predecessor - delete with successor
                deleteWithPredecessor(data);
            }
        }

    }

    private Node findNodeWithData(Node tmp, int data) {

        if (tmp == null) return null;
        if (tmp.data == data) return tmp;
        Node tmp1 = findNodeWithData(tmp.right, data);
        Node tmp2 = findNodeWithData(tmp.left, data);
        return tmp1 == null ? tmp2 : tmp1;
    }

    private Node getParent(Node child, Node tmp) {
        if (tmp == null) return null;
        if (tmp.left == child || tmp.right == child) return tmp;
        Node par = getParent(child, tmp.left);
        return par == null ? getParent(child, tmp.right) : par;
    }

    private Node getPredecessor(Node tmp) {
        tmp = tmp.left;
        while (tmp != null && tmp.right != null) tmp = tmp.right;
        return tmp;
    }

    private Node getSuccessor(Node tmp) {
        tmp = tmp.right;
        while (tmp != null && tmp.left != null) tmp = tmp.left;
        return tmp;
    }

    public void inOrderTraversal(Node tmp) {
        if (tmp == null) return;
        inOrderTraversal(tmp.left);
        System.out.print(tmp.data + " ");
        inOrderTraversal(tmp.right);
    }

    public static void main(String[] args) {
        //Create a new BinarySearchTree
        BinarySearchTree oBST = new BinarySearchTree();

        //Get the root node *****************************************************
        Node root = oBST.getRoot();
        //a. *********************************************************************
        // Insert nodes and the data is not sorted
        oBST.insertToTree(root, 15);
        root = oBST.getRoot();
        oBST.insertToTree(root, 7);
        oBST.insertToTree(root, 30);
        oBST.insertToTree(root, 2);
        oBST.insertToTree(root, 9);
        oBST.insertToTree(root, 18);
        oBST.insertToTree(root, 39);
        oBST.insertToTree(root, 1);
        oBST.insertToTree(root, 8);
        oBST.insertToTree(root, 11);
        oBST.insertToTree(root, 23);
        oBST.insertToTree(root, 34);
        oBST.insertToTree(root, 55);
        oBST.insertToTree(root, 10);
        oBST.insertToTree(root, 13);
        oBST.insertToTree(root, 21);
        oBST.insertToTree(root, 29);
        oBST.insertToTree(root, -1);
        oBST.insertToTree(root, -5);
        oBST.insertToTree(root, 80);
        oBST.insertToTree(root, 101);

        //b) *********************************************************************
        // Inorder traversal
        root = oBST.getRoot();
        System.out.println("Inorder traversal of the binary search tree after insertion...");
        oBST.inOrderTraversal(root);
        System.out.println("");

        //c) *********************************************************************
        //Deletion of the root node
        oBST.deleteWithPredecessor(root.data);
        //Inorder traversal
        root = oBST.getRoot();
        System.out.println("Inorder traversal of the binary search tree after deletion of the root node...");
        oBST.inOrderTraversal(root);
        System.out.println("");

        //d) *********************************************************************
        //Delete a node with predecessor
        oBST.deleteWithPredecessor(30);

        //Inorder traversal
        root = oBST.getRoot();
        System.out.println("Inorder traversal of the binary search tree after deletion of the node with value 30...");
        oBST.inOrderTraversal(root);
        System.out.println("");

    }

}

