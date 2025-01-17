import java.util.LinkedList;
import java.util.Queue;

/*
create an undirected graph with adjacency list representation
 */
public class Graph {
    private AdjNodeList[] vertices;

    //create an array with value true if visited
    private boolean[] visited;

    private class AdjNodeList {

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
                //check for duplicates if needed later...
                Node n = new Node();
                n.vertex = vertex;
                n.next = head;
                head = n;
            }
        }
    }

    Graph(int numOfV) {
        vertices = new AdjNodeList[numOfV]; // vertices named from 0 to numOfV-1
        for (int i = 0; i < numOfV; i++) {
            vertices[i] = new AdjNodeList();


        }
        visited = new boolean[vertices.length];
    }

    public void addEdge(int v1, int v2) {
        vertices[v1].insertNode(v2);
        vertices[v2].insertNode(v1);
    }

    public void printVertices() {
        System.out.println("The vertices of the graph are: ");
        int i = 0;
        while (i < vertices.length) {
            System.out.print(i + " ");
            i++;
        }
        System.out.println("");

    }

    public void printEdges() {

        System.out.println("The vertices and their corresponding edges for the graph are: ");
        int i = 0;
        for (AdjNodeList n : vertices
        ) {
            System.out.print("Vertex " + i + "'s Edges to: ");
            AdjNodeList.Node tmp = n.head;
            while (tmp != null) {
                System.out.print(tmp.vertex + " ");
                tmp = tmp.next;
            }
            System.out.println("");
            i++;
        }


        System.out.println("");

    }

    public void DFS() {

        visited = new boolean[vertices.length];
        for (int vertex = 0; vertex < visited.length; vertex++) {
            if (!visited[vertex]) {
                System.out.println("Start index: " + vertex);
                DFSTraverse(vertex);
            }
        }


    }

    private void DFSTraverse(int start_vertex) {
        //base condition - return if already visited
        if (visited[start_vertex]) {
            return;
        }

        System.out.println(start_vertex);
        visited[start_vertex] = true;
        //for a given start vertex
        AdjNodeList adjList = vertices[start_vertex];

        //choose one of its adjacent vertex, choose first in list
        AdjNodeList.Node tmp = adjList.head;
        //call DFS in a loop
        while (tmp != null) {
            if (!visited[tmp.vertex]) {

                DFSTraverse(tmp.vertex);
            }
            tmp = tmp.next;
        }

    }

    public void BFS() {
        //add one node to Q
        visited = new boolean[vertices.length];


        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                System.out.println("Bread first search starting at vertex: " + i);
                Queue<Integer> oQ = new LinkedList<>();
                oQ.add(i);
                visited[i] = true;
                //while Q is not empty
                while (!oQ.isEmpty()) {
                    //dequeue Q , print it and mark it as visited
                    int vertex = oQ.remove();
                    System.out.println(vertex);

                    //enqueue its adjacent nodes
                    AdjNodeList.Node tmp = vertices[vertex].head;
                    while (tmp != null) {
                        if (!visited[tmp.vertex]) {
                            oQ.add(tmp.vertex);
                            visited[tmp.vertex] = true;
                        }
                        tmp = tmp.next;
                    }
                }
            }
        }

    }


    public static void main(String[] args) {
        int numOfV = 20;
        Graph oGF = new Graph(20);
        //for the current implementation don't add duplicate edges or parallel edges
        oGF.addEdge(1, 2);
        oGF.addEdge(4, 2);
        oGF.addEdge(6, 2);
        oGF.addEdge(7, 3);
        oGF.addEdge(3, 9);
        oGF.addEdge(7, 19);
        oGF.addEdge(9, 6);
        oGF.addEdge(8, 5);
        oGF.addEdge(6, 0);
        oGF.addEdge(16, 5);
        oGF.addEdge(11, 12);
        oGF.addEdge(14, 13);
        oGF.addEdge(16, 8);
        oGF.addEdge(7, 9);
        oGF.addEdge(0, 13);
        oGF.addEdge(10, 14);
        oGF.addEdge(10, 16);
        oGF.addEdge(19, 3);
        oGF.addEdge(12, 15);
        oGF.addEdge(15, 11);
        oGF.addEdge(18, 17);
        oGF.addEdge(16, 14);
        oGF.addEdge(6, 4);
        oGF.addEdge(4, 1);
        oGF.addEdge(4, 9);
        oGF.addEdge(4, 10);
        oGF.addEdge(4, 5);


        //DFS
        //  oGF.DFS(7);
        oGF.BFS();
    }
}
