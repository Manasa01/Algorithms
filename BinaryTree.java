package mydatastructures;

/*
 * Binary Search Tree implementation
 *
 */
public class BinaryTree {
    private class Node {
        int key;
        Node left;
        Node right;
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insertKey(Node tmp, int key) {
        //if root  val < go left
        // else go right >=
        // till you get null node, then insert

        if (tmp == null) {
            tmp = insertValue(key);
            if (root == null) {
                root = tmp;
            }
            return;
        }
        if (tmp.key > key) {
            if (tmp.left == null) {
                tmp.left = insertValue(key);
                return;
            }
            insertKey(tmp.left, key);
        } else {
            if (tmp.right == null) {
                tmp.right = insertValue(key);
                return;
            }
            insertKey(tmp.right, key);
        }

    }

    private Node insertValue(int val) {
        Node tmp = new Node();
        tmp.key = val;
        return tmp;
    }

    public void deleteKeyWithPredecessor(int key) {
        //locate the key
        Node keyNode = findNodeWithKey(root, key); // how to handle duplicates? since this method does pre order traversal accordingly deleted
        //if its a leaf node, just delete it
        if (keyNode.left == null && keyNode.right == null) {
            Node parent = getParent(keyNode, root);
            if (parent == null) {
                //for root node
                root = null;
            }
            if (parent.left == keyNode) parent.left = null;
            else parent.right = null;
        }
        //find the predecessor
        Node pred = getPredecessor(keyNode);
        //replace the key with predecessor
        if (pred != null) {
            //if left child exists then make it the right child of its parent
            keyNode.key = pred.key;

            Node parent = getParent(pred, root);
            if (parent == keyNode) {
                parent.left = pred.left;
            } else {
                parent.right = pred.left;
            }
        } else {// if no predecessor - delete with successor
            deleteKeyWithSuccessor(key);
        }
    }

    public void deleteKeyWithSuccessor(int key) {
        //locate the key
        Node keyNode = findNodeWithKey(root, key); // how to handle duplicates? since this method does pre order traversal accordingly deleted
        //if its a leaf node, just delete it
        if (keyNode.left == null && keyNode.right == null) {
            Node parent = getParent(keyNode, root);
            if (parent == null) {
                //for root node
                root = null;
            }
            if (parent.left == keyNode) parent.left = null;
            else parent.right = null;
        }
        //find the successor
        Node succ = getSuccessor(keyNode);
        //replace the key with predecessor
        if (succ != null) {
            //if left child exists then make it the right child of its parent
            keyNode.key = succ.key;

            Node parent = getParent(succ, root);
            if (parent == keyNode) {
                parent.right = succ.right;
            } else {
                parent.left = succ.right;
            }
        } else {// if no predecessor - delete with successor
            deleteKeyWithPredecessor(key);
        }

    }

    private Node findNodeWithKey(Node tmp, int key) {

        if (tmp == null) return null;
        if (tmp.key == key) return tmp;
        Node tmp1 = findNodeWithKey(tmp.right, key);
        Node tmp2 = findNodeWithKey(tmp.left, key);
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
        System.out.println(tmp.key);
        inOrderTraversal(tmp.right);
    }

    public void postOrderTraversal(Node tmp) {
        if (tmp == null) return;
        postOrderTraversal(tmp.left);
        postOrderTraversal(tmp.right);
        System.out.println(tmp.key);
    }

    public void preOrderTraversal(Node tmp) {
        if (tmp == null) return;
        System.out.println(tmp.key);
        preOrderTraversal(tmp.left);
        preOrderTraversal(tmp.right);
    }

    public void inOrderTraversalWORecur() {


    }

    public void levelWiseTraversal() {
        Queue<Node> oTreeElements = new Queue<Node>();
        //while the queue is not empty
        oTreeElements.enQueue(root);
        while (!oTreeElements.isEmpty()) {
            Node tmp = oTreeElements.deQueue();
            //dequeue the item and print it
            System.out.print(tmp.key + " ");
            //enqueue its children
            if (tmp.left != null)
                oTreeElements.enQueue(tmp.left);
            if (tmp.right != null)
                oTreeElements.enQueue(tmp.right);
        }
    }

    public int getHeightOfTree(Node tmp) {

        if (tmp == null) return 0;
        //count left
        //count right
        //take the maximum of left and right
        return Math.max(1 + getHeightOfTree(tmp.left), 1 + getHeightOfTree(tmp.right));
    }

    public int getTotalNodes(Node tmp) {
        if (tmp == null) return 0;
        return getTotalNodes(tmp.left) + getTotalNodes(tmp.right) + 1;
    }

    public static void main(String[] args) {
        BinaryTree oBT = new BinaryTree();
//        System.out.println("Is Tree Empty?: " + oBT.isEmpty());
        Node root = oBT.getRoot();
        oBT.inOrderTraversal(root);
        oBT.insertKey(root, 4);
//        System.out.println("Is Tree Empty?: " + oBT.isEmpty());
        root = oBT.getRoot();
//        oBT.inOrderTraversal(root);
//        System.out.println("Height : " + oBT.getHeightOfTree(root));
//        System.out.println("Count : " + oBT.getTotalNodes(root));
        oBT.insertKey(root, 10);
        oBT.insertKey(root, -32);
//        System.out.println("Height : " + oBT.getHeightOfTree(root));
//        System.out.println("Count : " + oBT.getTotalNodes(root));
        oBT.insertKey(root, 456);
        oBT.insertKey(root, 34);
//        System.out.println("Height : " + oBT.getHeightOfTree(root));
//        System.out.println("Count : " + oBT.getTotalNodes(root));
        oBT.insertKey(root, -24);
        oBT.insertKey(root, 40);
        oBT.insertKey(root, -1);
        oBT.insertKey(root, 1);
//        System.out.println("Height : " + oBT.getHeightOfTree(root));
//        System.out.println("Count : " + oBT.getTotalNodes(root));
//        oBT.inOrderTraversal(oBT.getRoot());
//
        oBT.levelWiseTraversal();
//        Node newn = oBT.findNodeWithKey(root, 4);
//        // Node newp = oBT.getParent(newn, root);
//        Node pre = oBT.getPredecessor(newn);
//        Node suc = oBT.getSuccessor(newn);
//        if (pre != null)
//            System.out.println(" P " + pre.key);
//        if (suc != null)
//            System.out.println(" S" + suc.key);

        oBT.deleteKeyWithPredecessor(-32);
        System.out.println("after deletion..");
        oBT.levelWiseTraversal();


    }

}
