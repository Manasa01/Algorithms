import java.util.Queue;
import java.util.LinkedList;

public class BTree {
    /*
    Node class is the representation of every node in BTree
     */
    private class Node {
        private int count; //counts no. of occupied nodes
        private int[] keys; //size is m-1  for m order Btree + 1 for overflow
        private Node[] branch; //size is m
        private Node parent;  //parent needed during insertion deletion process
        private int m; //order

        /*
        Node constructor
         */
        Node(int m) {
            keys = new int[m];
            branch = new Node[m];
            count = 0;
            this.m = m;
        }

        /*
        get the keys array in a node
         */
        public int[] getKeys() {
            int[] existingKeys = new int[count];
            for (int i = 0; i < count; i++) {
                existingKeys[i] = keys[i];
            }
            return existingKeys;
        }

        /*
         set a key in a given index
         */
        public void setKey(int key, int index) {
            keys[index] = key;
        }

        /*
        increment the count after an insert
         */
        public void incrementCount() {
            count++;
        }

        /*
        decrement the count after deletion
         */
        public void decrementCount() {
            count--;
        }

        /*
        getter for parent node
         */
        public Node getParent() {
            return parent;
        }

        /*
        setter for parent node
         */
        public void setParent(Node par) {
            parent = par;
        }

        /*
        getter for branches
         */
        public Node[] getBranches() {
            //no of branches will be count +1 in all cases, except during overflow situation, when it will be count
            //when count is 0 it will be 0
            int updatedCount;

            updatedCount = count == m || count == 0 ? count : count + 1;

            Node[] existingBranches = new Node[updatedCount];
            for (int i = 0; i < updatedCount; i++) {
                existingBranches[i] = branch[i];
            }
            return existingBranches;
        }

        /*
        setter for branch in a given index
         */
        public void setBranch(Node branchRef, int index) {
            branch[index] = branchRef;
        }

        /*
        getter for count
         */
        public int getCount() {
            return count;
        }

    }

    /*
    class to enable a insert method with the given return structure
     */
    private class returnStruct {
        Node n1;
        Node n2;
        int mid;
    }

    private int order; //order of the Btree
    private Node root;

    BTree(int m) {
        order = m;
    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /*
    creates new root
     */
    private void createNewRoot(int key, Node branch1, Node branch2) {
        Node n = new Node(order);
        n.setParent(null);
        n.setKey(key, 0);
        n.setBranch(branch1, 0);
        n.setBranch(branch2, 1);
        n.incrementCount();
        root = n;
    }

    /*
    main method for insertion in Btree
     */
    public void insertBT(int key) {
        //If empty, create root and insert value
        if (isEmpty()) {
            createNewRoot(key, null, null);
        } else {
            //else, find the node to insert
            Node insertionNode = findNode2Insert(root, key);
            //insert the value
            insertionNode = insertKeyAtNode(insertionNode, key);
            //check for overflow
            //if overflow, then split the nodes and take the middle(or predecessor) up
            while (insertionNode.getCount() == order) {
                //split and go up till overflow resolved
                returnStruct splitVal = splitNode(insertionNode);
                insertionNode = splitVal.n2;
                //if Root
                if (insertionNode.getParent() == null) {
                    createNewRoot(splitVal.mid, splitVal.n1, splitVal.n2);
                    splitVal.n1.setParent(root);
                    splitVal.n2.setParent(root);
                    insertionNode = root;
                } else {
                    insertionNode = insertAtParent(insertionNode.parent, splitVal);
                }
            }
        }
    }

    /*
    to check if leaf node by checking all the branches is null
    returns true if leaf node
     */
    private boolean isLeaf(Node n) {
        Node[] currentBranches = n.getBranches();
        boolean isLeafNode = true;
        for (Node currBranch : currentBranches) {
            if (currBranch != null) {
                isLeafNode = false;
                break;
            }
        }
        return isLeafNode;
    }

    /*
    find the branch associated with the key for downward traversal during key search
     */
    private Node findBranch(Node n, int key) {
        int[] currentKeys = n.getKeys();
        Node[] currentBranches = n.getBranches();
        Node toInsert = null;
        //Better way is to do binsearch and find the position
        for (int i = 0; i < currentKeys.length; i++) {
            if (i == 0 && currentKeys[i] > key) { //first key
                toInsert = currentBranches[i];
            } else if (i == (currentKeys.length - 1)) { //last key
                if (currentKeys[i] < key) {
                    toInsert = currentBranches[i + 1];
                } else if (currentKeys[i] > key && currentKeys[i - 1] < key) {
                    toInsert = currentBranches[i];
                }
            } else if (currentKeys[i] > key && currentKeys[i - 1] < key) {
                toInsert = currentBranches[i];
            }
        }
        return toInsert;
    }

    /*
    find the node for insertion, like in a BST
     */
    private Node findNode2Insert(Node n, int key) {
        //start at the root
        //find the appropriate branch and move till leaf is reached
        boolean isLeafNode = isLeaf(n);
        if (isLeafNode) {
            return n;
        } else {
            //findTheBranch
            n = findBranch(n, key);
            //call recursively for the branch
            return findNode2Insert(n, key);
        }
    }

    /*
    insert the key at the leaf node once found
     */
    private Node insertKeyAtNode(Node n, int key) {
        //find the position to insert
        int[] curKeys = n.getKeys();
        int i;
        for (i = 0; i < curKeys.length && key > curKeys[i]; i++) {
        }
        //shift and insert
        for (int j = curKeys.length; j > i; j--) {
            n.setKey(curKeys[j - 1], j);
        }
        n.setKey(key, i);
        n.incrementCount();
        return n;
    }

    /*
    split node in case of overflow
     */
    private returnStruct splitNode(Node n) {
        returnStruct oRet = new returnStruct();
        //find the middle
        int mid = (n.getCount() - 1) / 2; //considering predecessor as mid
        int[] curKeys = n.getKeys();
        Node[] curBranches = n.getBranches();
        //make two nodes
        Node n1 = new Node(order);
        int i;
        for (i = 0; i < mid; i++) {
            n1.setKey(curKeys[i], i);
            n1.incrementCount();
            n1.setBranch(curBranches[i], i);
        }
        n1.setBranch(curBranches[i], i);
        n1.setParent(n.getParent());
        int j = 0;
        Node n2 = new Node(order);
        for (i = mid + 1; i < n.getCount(); i++) {
            n2.setKey(curKeys[i], j);
            n2.incrementCount();
            n2.setBranch(curBranches[i], j);
            j++;
        }
        n2.setParent(n.getParent());
        oRet.n1 = n1;
        oRet.mid = curKeys[mid];
        oRet.n2 = n2;
        return oRet;
    }

    /*
    insert the split nodes appropriately at the parent
     */
    private Node insertAtParent(Node p, returnStruct splitNodes) {
        //
        p = insertKeyAtNode(p, splitNodes.mid);
        //insert the branches
        //find the position to insert
        int[] curKeys = p.getKeys();
        Node[] curBranches = p.getBranches();
        int i;
        for (i = 0; i < curKeys.length && splitNodes.mid != curKeys[i]; i++) {
        }
        //shift and insert
        for (int j = i; j < curBranches.length - 2; j++) {
            p.setBranch(curBranches[j], j + 2);
        }
        p.setBranch(splitNodes.n1, i);
        p.setBranch(splitNodes.n2, i + 1);

        return p;
    }

    /*
    breadth wise print for BTree
     */
    void printBT() {
        //using Q
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        Node tmp;
        Node[] branches;
        int[] keys;
        while (!q.isEmpty()) {
            tmp = q.remove();
            branches = tmp.getBranches();
            keys = tmp.getKeys();
            for (Node branch : branches) {
                if (branch != null) {
                    q.add(branch);
                }
            }
            System.out.println("Node");
            for (int key : keys) {
                System.out.print(key);
            }
            System.out.println("");
        }

    }

    private class returnStructDel {
        Node n;
        int index;
        int val;
    }

    /*
    main method for deletion of BTree
     */
    public void deleteBT(int key) {

        //find the key
        returnStructDel toDelete = findNodeWithKey(key);
        Node finalDeletionFrom;
        //if leaf node, delete
        if (isLeaf(toDelete.n)) {
            finalDeletionFrom = toDelete.n;
        } else {
            //find the predecessor or successor
            returnStructDel toSwap = findPredecessorOrSuccessor(key);
            //swap the key to be deleted with predecessor or successor
            int temp = toSwap.val;
            toSwap.n.setKey(key, toSwap.index);
            toDelete.n.setKey(temp, toDelete.index);
            finalDeletionFrom = toSwap.n;

        }
        //delete key from the node
        deleteKeyAtNode(key, finalDeletionFrom);

        //check for underflow
        int minKey = (int) (Math.ceil(order / 2.0) - 1);
        //check for underflow again. till root
        while (finalDeletionFrom.getCount() < minKey) {
            //if yes, check borrow opportunity from right and left sibling
            Node sibling = findSiblingToBorrowFrom(finalDeletionFrom);
            if (sibling != null) {
                //rotate
            } else {
                //if not, merge with sibling bring middle down,

                finalDeletionFrom = finalDeletionFrom.parent;
            }

        }


    }

    private returnStructDel findNodeWithKey(int key) {
        returnStructDel ret = new returnStructDel();
        ret.n = new Node(1);
        ret.index = 0;
        ret.val = 0;
        return ret;

    }

    private void deleteKeyAtNode(int key, Node n) {
        //decrement the count of the leaf node or successor/predecessor node

    }

    private returnStructDel findPredecessorOrSuccessor(int key) {
        returnStructDel ret = new returnStructDel();
        ret.n = new Node(1);
        ret.index = 0;
        ret.val = 0;
        return ret;
    }

    private Node findSiblingToBorrowFrom(Node n) {
        return new Node(1);
    }

    /*
    Testing B-Tree
     */
    public static void main(String[] args) {
        BTree oBT = new BTree(3);
        //test isEmpty
//        System.out.println("IsEmpty: " + oBT.isEmpty());

        //test insertion at root
        oBT.insertBT(3);
//        System.out.println("IsEmpty: " + oBT.isEmpty());
//        System.out.println("Keys in Root:");
        Node root = oBT.getRoot();
//        int[] keys = root.getKeys();
//        for (int key : keys){
//            System.out.println(key);
//        }

        //test subsequent insertions
        //1. find the node to insert
//        Node insertionNode = oBT.findNode2Insert(root, 4);
//        System.out.println("isRoot: " + (root == insertionNode));

        oBT.insertBT(4);
        oBT.insertBT(5);
//        root = oBT.getRoot();
//        keys = root.getKeys();
//        System.out.println("New root after split");
//        for (int key : keys){
//            System.out.println(key);
//        }

        oBT.printBT();

    }

}
