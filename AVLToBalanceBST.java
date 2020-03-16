package mydatastructures;

/*In PROGRESS... */
public class AVLToBalanceBST {

    private Node root;

    public static void main(String[] args) {
        AVLToBalanceBST oBST = new AVLToBalanceBST();

        // Insert nodes and the data is not sorted
        Node root = oBST.getRoot();
        oBST.insertValue(root, 15);
        root = oBST.getRoot();
        oBST.insertValue(root, 7);
        oBST.insertValue(root, 30);
        oBST.insertValue(root, 2);
        oBST.insertValue(root, 1);
//        oBST.insertValueAVL(18);
        Node n1 = oBST.findNodeWithKey(root, 7);
        Node n2 = oBST.findNodeWithKey(root, 2);
        Node n3 = oBST.findNodeWithKey(root, 1);
        oBST.BalanceLeftStraightLine(n1,n2,n3);
        // Inorder traversal
        root = oBST.getRoot();
        System.out.println("Level wise traversal of the AVL balanced binary search tree after insertion...");
        oBST.levelWiseTraversal();
        System.out.println("");

    }

    public Node getRoot() {
        return root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insertValueAVL(int value) {
        //insert value similar to BST
        Node iNode = insertValue(root, value);
        // check the height of the parent
        Node par = iNode.parent;

//        boolean flag = false;
//        while(par != null && flag == false){
//            int heightDiff = findHeightofNode(par.left) - findHeightofNode(par.right);
//
//        }
//        if (par != null) {
//            int heightDiff = findHeightofNode(par.left) - findHeightofNode(par.right);
//            heightDiff = Math.abs(heightDiff);
//            // keep checking till the root till the height difference from left and right becomes more than 1
//            while (par != null && heightDiff < 2) {
//                par = par.parent;
//                if (par != null) {
//                    heightDiff = findHeightofNode(par.left) - findHeightofNode(par.right);
//                    heightDiff = Math.abs(heightDiff);
//                }
//            }
//            if (par != null && heightDiff >= 2) {
//                //do AVL balancing
//                AVLBalance(par);
//            }
//        }

    }

    public void AVLBalance(Node n1) {
        //find the sequence of the three nodes
        // nodes from the longest path
        Node n2, n3;
        n2 = findHeightofNode(n1.left) > findHeightofNode(n1.right) ? n1.left : n1.right;
        n3 = findHeightofNode(n2.left) > findHeightofNode(n2.right) ? n2.left : n2.right;
        // Case 1 : all in left straight line: rotate right
        if (n1.left == n2 && n2.left == n3) {
            balanceWithRotateRight(n1, n2, n3);
        }
        // Case 2 : all in right straight line: rotate left
        if (n1.right == n2 && n2.right == n3) {
            balanceWithRotateLeft(n1, n2, n3);
        }
        // Case 3: in left zig zag : make left line and rotate right
        if (n1.left == n2 && n2.right == n3) {
            balanceWithZigzagLeft(n1, n2, n3);
        }
        // Case 4: in right zig zag: make right line and rotate left
        if (n1.right == n2 && n2.left == n3) {
            balanceWithZigzagRight(n1, n2, n3);
        }
        //update the heights
    }

    private void balanceWithRotateRight(Node n1, Node n2, Node n3) {
        System.out.println("BalancedWithRotateRight");
        // n1, n2, n3 in left line
        //n2 becomes parent of n1
        Node n2OldRight = n2.right;
        n2.right = n1;
        Node oldPar = n1.parent;
        n1.parent = n2;
        n2.parent = oldPar;
        if (oldPar.left == n1) {
            oldPar.left = n2;
        } else {
            oldPar.right = n2;
        }
        n1.left = n2OldRight;
        //
    }

    private void balanceWithRotateLeft(Node n1, Node n2, Node n3) {
        System.out.println("BalancedWithRotateLeft");
        // n1, n2, n3 in right line
        //n2 becomes parent of n1
        Node n2OldLeft = n2.left;
        n2.left = n1;
        Node oldPar = n1.parent;
        n1.parent = n2;
        n2.parent = oldPar;
        if (oldPar.left == n1) {
            oldPar.left = n2;
        } else {
            oldPar.right = n2;
        }
        n1.right = n2OldLeft;
        //

    }

    private void balanceWithZigzagLeft(Node n1, Node n2, Node n3) {
        System.out.println("BalancedWithZigZagLeft");
        //n1,n2,n3
        //n3 becomes the child of n1 and parent of n2
        n1.left = n3;
        n3.parent = n1;
        Node n3left = n3.left;
        n3.left = n2;
        n2.parent = n3;
        if (n3left != null) {
            n2.right = n3left;
            n3left.parent = n2;
        }

        //rotate right for n1,n3,n2
        balanceWithRotateLeft(n1, n3, n2);

    }

    private void balanceWithZigzagRight(Node n1, Node n2, Node n3) {
        System.out.println("BalancedWithZigZagRight");
        //n1,n2,n3
        //n3 becomes the child of n1 and parent of n2
        n1.right = n3;
        n3.parent = n1;
        Node n3right = n3.right;
        n3.right = n2;
        n2.parent = n3;
        if (n3right != null) {
            n2.left = n3right;
            n3right.parent = n2;
        }

        //rotate right for n1,n3,n2
        balanceWithRotateRight(n1, n3, n2);
    }

    private void BalanceLeftStraightLine(Node n1, Node n2, Node n3) {
        //parent of n1 is now parent of n2
        n2.parent = n1.parent;
        if (n2.parent.left == n1) {
            n2.parent.left = n2;
        } else {
            n2.parent.right = n2;
        }
        //n2 is parent of n1
        n1.parent = n2;
        //n1 is left child of n2
        Node oldRightChildN2 = n2.right;
        n2.right = n1;
        n1.right = oldRightChildN2;
        if (n1.left != null) {
            n1.left.parent = n1;
        }
    }

    private void BalanceRightStraightLine(Node n1, Node n2, Node n3) {
        //parent of n1 is now parent of n2
        n2.parent = n1.parent;
        if (n2.parent.left == n1) {
            n2.parent.left = n2;
        } else {
            n2.parent.right = n2;
        }
        //n2 is parent of n1
        n1.parent = n2;
        //n1 is left child of n2
        Node oldLeftChildN2 = n2.left;
        n2.left = n1;
        n1.right = oldLeftChildN2;
        if (n1.right != null) {
            n1.right.parent = n1;
        }
    }

    private void BalanceLeftZigZag(Node n1, Node n2, Node n3) {
        n1.left = n3;
        n3.parent = n1;

        Node oldN3Left = n3.left;
        n3.left = n2;
        n2.parent = n3;

        n2.right = oldN3Left;
        if (oldN3Left != null) {
            n2.right.parent = n2;
        }

        //Balance Left Straight Line\
        BalanceLeftStraightLine(n1, n3, n2);
    }

    private void BalanceRightZigZag(Node n1, Node n2, Node n3) {
        n1.right = n3;
        n3.parent = n1;

        Node oldN3Right = n3.right;
        n3.right = n2;
        n2.parent = n3;

        n2.left = oldN3Right;
        if (oldN3Right != null) {
            n2.left.parent = n2;
        }

        //Balance Right Straight Line
        BalanceRightStraightLine(n1, n3, n2);
    }

    public Node insertValue(Node tmp, int val) {
        //if root  val < go left
        // else go right >=
        // till you get null node, then insert

        if (tmp == null) {
            tmp = insertData(val);
            if (root == null) {
                root = tmp;
            }
            return tmp;
        }
        if (tmp.value > val) {
            if (tmp.left == null) {
                tmp.left = insertData(val);
                return tmp.left;
            }
            insertValue(tmp.left, val);
        } else {
            if (tmp.right == null) {
                tmp.right = insertData(val);
                return tmp.right;
            }
            insertValue(tmp.right, val);
        }
        return tmp;
    }

    private Node insertData(int val) {
        Node tmp = new Node();
        tmp.value = val;
       // tmp.height = findHeightofNode(tmp);
        tmp.parent = getParent(tmp, root);
        return tmp;
    }

    private int findHeightofNode(Node tmp) {
        if (tmp == null) return 0;
        //count left
        //count right
        //take the maximum of left and right
        return Math.max(1 + findHeightofNode(tmp.left), 1 + findHeightofNode(tmp.right));
    }

    private Node getParent(Node child, Node tmp) {
        if (tmp == null) return null;
        if (tmp.left == child || tmp.right == child) return tmp;
        Node par = getParent(child, tmp.left);
        par =  par == null ? getParent(child, tmp.right) : par;
     return par;
    }

    public int getHeightOfTree(Node tmp) {

        if (tmp == null) return 0;
        //count left
        //count right
        //take the maximum of left and right
        return Math.max(1 + getHeightOfTree(tmp.left), 1 + getHeightOfTree(tmp.right));
    }

    public void inOrderTraversal(Node tmp) {
        if (tmp == null) return;
        inOrderTraversal(tmp.left);
        System.out.println(tmp.value);
        inOrderTraversal(tmp.right);
    }

    public void levelWiseTraversal() {
        Queue<AVLToBalanceBST.Node> oTreeElements = new Queue<AVLToBalanceBST.Node>();
        //while the queue is not empty
        oTreeElements.enQueue(root);
        while (!oTreeElements.isEmpty()) {
            AVLToBalanceBST.Node tmp = oTreeElements.deQueue();
            //dequeue the item and print it
            System.out.print(tmp.value + " ");
            //enqueue its children
            if (tmp.left != null)
                oTreeElements.enQueue(tmp.left);
            if (tmp.right != null)
                oTreeElements.enQueue(tmp.right);
        }
    }

    private Node findNodeWithKey(Node tmp, int key) {

        if (tmp == null) return null;
        if (tmp.value == key) return tmp;
        Node tmp1 = findNodeWithKey(tmp.right, key);
        Node tmp2 = findNodeWithKey(tmp.left, key);
        return tmp1 == null ? tmp2 : tmp1;
    }

    private class Node {
        int value;
        Node left;
        Node right;
        Node parent;
        int height;
    }
}
