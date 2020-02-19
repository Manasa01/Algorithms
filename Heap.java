package mydatastructures;

public class Heap {
    private int[] heap = new int[]{14, 56, 34, 23, 45, 67, 7, 48, 32, 2, 58, 40, 33, 1, 123};
    private int size = heap[0];

    //Floyds method for heapify, in linear time
    public void heapifyMax() {
        int parentIndex = heap[0] / 2;

        while (parentIndex > 0) {
            percolateDownMax(parentIndex);
            parentIndex--;
        }
    }

    private void percolateDownMax(int k) {
        int i;
        while (k > 0 && k <= heap[0]) {
            //find the index i to compare with k
            i = greaterOf(2 * k, 2 * k + 1);
            if (i > 0 && heap[k] < heap[i]) {
                //swap
                swap(k, i);

            }
            k = i;

        }
    }

    private int greaterOf(int k, int l) {
        int i;
        int heapSize = heap[0];
        if (k <= heapSize && l <= heapSize) {
            i = heap[k] > heap[l] ? k : l;
        } else if (l > heapSize && k <= heapSize) {
            i = k;
        } else // same as case where no children
        {
            i = -1; //index does not exist
        }
        return i;
    }

    private void swap(int k, int i) {
        int temp = heap[k];
        heap[k] = heap[i];
        heap[i] = temp;
    }

    public void heapifyMin() {
        int parentIndex = heap[0] / 2;

        while (parentIndex > 0) {
            percolateDownMin(parentIndex);
            parentIndex--;
        }
    }

    private void percolateDownMin(int k) {
        int i;
        while (k > 0 && k <= heap[0]) {
            //find the index i to compare with k
            i = lesserOf(2 * k, 2 * k + 1);
            if (i > 0 && heap[k] > heap[i]) {
                //swap
                swap(k, i);

            }
            k = i;

        }
    }

    private int lesserOf(int k, int l) {
        int i;
        int heapSize = heap[0];
        if (k <= heapSize && l <= heapSize) {
            i = heap[k] < heap[l] ? k : l;
        } else if (l > heapSize && k <= heapSize) {
            i = k;
        } else // same as case where no children
        {
            i = -1; //index does not exist
        }
        return i;
    }

    public void heapSortMax() {
        int size = heap[0];
        while (heap[0] > 1) {
            swap(1, heap[0]);
            heap[0]--;
            percolateDownMax(1);
        }
        heap[0] = size;
    }

    public void heapSortMin() {
        int size = heap[0];
        while (heap[0] > 1) {
            swap(1, heap[0]);
            heap[0]--;
            percolateDownMin(1);
        }
        heap[0] = size;
    }

    public void printHeap() {
        System.out.println("Size " + heap[0]);
        for (int i = 1; i <= heap[0]; i++) {
            System.out.print(heap[i] + " ");
        }
    }

    public static void main(String[] args) {
        Heap oHeap = new Heap();
        oHeap.printHeap();
//        System.out.println("");
//        oHeap.heapifyMax();
//        oHeap.printHeap();
//        System.out.println("");
//        oHeap.heapSortMax();
//        oHeap.printHeap();
        System.out.println("");
        oHeap.heapifyMin();
        oHeap.printHeap();
        System.out.println("");
        oHeap.heapSortMin();
        oHeap.printHeap();
    }
}
