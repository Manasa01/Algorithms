import java.util.LinkedList;
import java.util.Queue;

/*
create an undirected graph with adjacency list representation
 */
public class Graph {
    private AdjNodeList[] vertices;

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
    }

    public void addEdge(int v1, int v2) {
        vertices[v1].insertNode(v2);
        vertices[v2].insertNode(v1);
    }

    public void DFS(int start_vertex) {
        //create an array with value true if visited
        boolean[] visited = new boolean[vertices.length];

        //base condition - return if already visited
        if (visited[start_vertex]) {
            return;
        }

        System.out.println(start_vertex);
        //for a given start vertex
        AdjNodeList adjlist = vertices[start_vertex];

        //choose one of its adjacent vertex, choose first in list
        int v = adjlist.head.vertex;
        System.out.println(v);
        visited[v] = true;

        //keep the rest of the vertices in a queue
        Queue<Integer> oQ = new LinkedList<Integer>();
        AdjNodeList.Node tmp = adjlist.head.next;
        while (tmp != null) {
            oQ.add(tmp.vertex);
            tmp = tmp.next;
        }

        while (!oQ.isEmpty()) {

        }
        DFS(v);
        //repeat


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
        oGF.addEdge(1, 2);

        //DFS
        oGF.DFS(7);
    }
}
