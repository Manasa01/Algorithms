import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
    Topological Sort can be done for Directed Acyclic Graphs only
    If cycle is detected,  message is shown
 */

public class BFSTopologicalSort {

    //Directed Acyclic Graph

    /* has the inDegree count for each vertex */
    private int[] inDegree;
    /* on BFS traversal the first completed vertex appears at the beginning  */
    private Queue<Integer> topologicalOrder;
    /* if inDegree is 0, vertex is added to processingQ*/
    private Queue<Integer> processingQ;
    /* cycleExists flag is checked for existence of cycle*/
    boolean cycleExists = false;

    public void BFS(DirectedGraph G, int noOfVertices) {
        topologicalOrder = new LinkedList<>();
        processingQ = new LinkedList<>();
        inDegree = G.getInDegreeCount();
        //find vertices with in degree 0 and put in processingQ
        int v;
        for (v = 0; v < noOfVertices; v++) {
            if (inDegree[v] == 0) {
                processingQ.add(v);
            }
        }

        while (!processingQ.isEmpty()) {
            int vertex = processingQ.remove();
            topologicalOrder.add(vertex);

            for (int w : G.getAdjacents(vertex)) {

                //subtract 1 from adjacent vertices
                //put 0's in Q
                inDegree[w]--;

                if (inDegree[w] == 0) {
                    processingQ.add(w);

                }
            }

        }
        if (topologicalOrder.size() != noOfVertices) {
            cycleExists = true;
        }
        printTopologicalSort(cycleExists);
    }

    private void printTopologicalSort(boolean cycleExists) {
        //m and offset are used to display the vertices as named in G1
        char m = 'm';
        int offset = (int) m;
        System.out.println("------------------------------------------------------------------------");
        if (cycleExists) {
            System.out.println("Cycle exists. Cannot find topological sort for cyclic graph");
        } else {

            System.out.println("Topological Sort using BFS");
            System.out.println("");
            while (!topologicalOrder.isEmpty()) {
                char vertex = (char) (topologicalOrder.remove() + offset);
                System.out.printf(vertex + " ");
            }
            System.out.println("");

        }
        System.out.println("------------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        DirectedGraph G = new DirectedGraph(14);
        //Graph G1
        //vertices m to z are internally named from 0 to 13
        G.addEdge(0, 4);
        G.addEdge(0, 5);
        G.addEdge(0, 11);
        G.addEdge(1, 4);
        G.addEdge(1, 8);
        G.addEdge(1, 2);
        G.addEdge(2, 5);
        G.addEdge(2, 6);
        G.addEdge(2, 9);
        G.addEdge(3, 2);
        G.addEdge(3, 6);
        G.addEdge(3, 13);
        G.addEdge(4, 7);
        G.addEdge(5, 8);
        G.addEdge(5, 12);
        G.addEdge(6, 5);
        G.addEdge(8, 7);
        G.addEdge(9, 10);
        G.addEdge(9, 11);
        G.addEdge(10, 13);
        G.addEdge(12, 9);
        System.out.println("For Graph G1:");
        BFSTopologicalSort topoSort = new BFSTopologicalSort();
        topoSort.BFS(G, 14);

        //Graph G2
        //Vertices 1 to 8 are internally named as 0 to 7
        G = new DirectedGraph(8);
        G.addEdge(0, 1);
        G.addEdge(0, 4);
        G.addEdge(0, 5);
        G.addEdge(1, 2);
        G.addEdge(1, 4);
        G.addEdge(1, 6);
        G.addEdge(2, 3);
        G.addEdge(3, 4);
        G.addEdge(4, 6);
        G.addEdge(4, 7);
        G.addEdge(5, 4);
        G.addEdge(5, 6);
        G.addEdge(6, 3);
        G.addEdge(6, 7);
        System.out.println("For Graph G2:");
        topoSort = new BFSTopologicalSort();
        topoSort.BFS(G, 8);

    }

    public static class DirectedGraph {
        private AdjList[] vertices;
        private int[] inDegreeCount;

        private class AdjList {

            private class Node {
                int vertex;
                Node next;
            }

            private Node head;

            private void insertNode(int vertex) {
                if (head == null) {
                    Node n = new Node();
                    n.vertex = vertex;
                    n.next = null;
                    head = n;
                } else {
                    //assuming edges are added if not there already
                    Node n = new Node();
                    n.vertex = vertex;
                    n.next = head;
                    head = n;
                }
            }

        }

        DirectedGraph(int numOfV) {
            vertices = new AdjList[numOfV]; // vertices named from 0 to numOfV-1
            inDegreeCount = new int[numOfV];
            for (int i = 0; i < numOfV; i++) {
                vertices[i] = new AdjList();

            }
        }

        public void addEdge(int v1, int v2) {
            vertices[v1].insertNode(v2);
            inDegreeCount[v2]++;
        }

        public AdjList[] getV() {
            return vertices;
        }

        public Iterable<Integer> getAdjacents(int v) {
            ArrayList<Integer> adjacents = new ArrayList<>();

            DirectedGraph.AdjList.Node tmp = vertices[v].head;
            while (tmp != null) {
                adjacents.add(tmp.vertex);
                tmp = tmp.next;
            }
            return adjacents;
        }

        public int[] getInDegreeCount() {
            return inDegreeCount;
        }

    }

}
