import java.util.ArrayList;
import java.util.Stack;

/*
    Topological Sort can be done for Directed Acyclic Graphs only
    If cycle is detected,  message is shown
 */
public class DFSTopologicalSort {

    /* visited[i] is true if vertex i was encountered in the DFS traversal */
    private boolean[] visited;
    /* on DFS traversal the first completed vertex appears at the end */
    private Stack<Integer> reverseTopologicalOrder;
    /* once DFS is completed for all of vertex i's edges, completedVisit[i] is assigned true*/
    private boolean[] completedVisit;
    /* has the inDegree count for each vertex */
    private int[] inDegree;
    /* cycleExists flag is checked for existence of cycle*/
    boolean cycleExists = false;

    public void DFS(DirectedGraph G, int noOfVertices) {
        visited = new boolean[noOfVertices];
        reverseTopologicalOrder = new Stack<>();
        completedVisit = new boolean[noOfVertices];
        inDegree = G.getInDegreeCount();

        //find a vertex with only outgoing edges
        for (int v = 0; v < noOfVertices && !cycleExists; v++) {
            if (inDegree[v] == 0) {
                DFSVisit(G, v);
            }
        }
        //Display the stack
        printTopologicalSort(cycleExists);

    }

    private void DFSVisit(DirectedGraph G, int v) {
        if (!cycleExists) {
            visited[v] = true;
            for (int w : G.getAdjacents(v)) {
                //check for cycle
                if (visited[w] && !completedVisit[w]) {
                    cycleExists = true;
                    findCycle(v, w);
                    return;
                }
                if (!visited[w]) {
                    DFSVisit(G, w);
                }
            }
            //once the vertex is completed include it in the topological list
            reverseTopologicalOrder.push(v);
            completedVisit[v] = true;
        }


    }
    private void findCycle(int v, int w){
        String cycle;


    }
    private void printTopologicalSort(boolean cycleExists) {
        //m and offset are used to display the vertices as named in G1
        char m = 'm';
        int offset = (int) m;
        System.out.println("------------------------------------------------------------------------");
        if (cycleExists) {
            System.out.println("Cycle exists. Cannot find topological sort for cyclic graph");
        } else {

            System.out.println("Topological Sort using DFS");
            System.out.println("");
            while (!reverseTopologicalOrder.isEmpty()) {
                char vertex = (char) (reverseTopologicalOrder.pop() + offset);
                System.out.printf(vertex + " ");
//                System.out.printf(reverseTopologicalOrder.pop() + " ");
            }
            System.out.println("");

        }
        System.out.println("------------------------------------------------------------------------");
    }

    public static void main(String[] args) {

        //Graph G1
        //vertices m to z are internally named from 0 to 13
        DirectedGraph G = new DirectedGraph(14);
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
        DFSTopologicalSort topoSort = new DFSTopologicalSort();
        System.out.println("For Graph G1:");
        topoSort.DFS(G, 14);

        // Graph G2
        //Vertices 1 to 8 are internally named from 0 to 7
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
        topoSort = new DFSTopologicalSort();
        topoSort.DFS(G, 8);

    }
    /*
     * Directed Graph representation
     * Adjacency list
     */
    public static class DirectedGraph {
        private AdjList[] vertices;
        //inDegree counted while adding edges
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
            //outgoing edge from v1 to v2 added to v1's list
            vertices[v1].insertNode(v2);
            //in degree of v2 incremented
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
