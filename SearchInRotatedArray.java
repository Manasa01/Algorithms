package mydatastructures;

public class SearchInRotatedArray {
    private int[] arr;
    private int realLo = -1;
    private int realHi = -1;

    SearchInRotatedArray(int[] arrayPointer) {
        arr = arrayPointer;
    }

    public static void main(String[] args) {
        int[] testArray = new int[]{5, 6, 7, 9, 1, 2, 3};
        SearchInRotatedArray oRA = new SearchInRotatedArray(testArray);
//        oRA.printArray();
        int lo = oRA.findOrigin();
        System.out.println("Lo is " + lo);
        System.out.println("hi is " + (lo - 1));
        //Test cases
//    {5, 6, 7, 9, 1, 2, 3}  binlo = 4 , binhi = 3
//    {2, 3, 4, 5, 6, 7, 8, 9}
//    {6, 1, 2, 3, 4, 5, 6} binhi = 0 binlo = 1
//    {10, 1, 2, 3, 4, 5, 6, 7, 8} binhi = 0 binlo = 1
//    {10, 11, 12, 13, 14, 1} binhi = 4 binlo = 5

        int index = oRA.binarySearchForRotatedArray(10);
        if (index > 0) {
            System.out.println("The value was found in index " + index);
            System.out.println("The value is " + testArray[index]);

        } else {
            System.out.println("The value was not in the array");
        }
    }

    public void printArray() {
        for (int val : arr) {
            System.out.println(val);
        }
    }

    /*
     * Find the beginning of the array, in the rotated sorted array
     */
    public int findOrigin() {
        int lo = 0;
        int hi = arr.length - 1;
        int mid;
        while (lo <= hi && arr[lo] >= arr[hi]) {

            mid = lo + hi / 2;

            if (arr[lo] > arr[mid]) {
                hi = mid;
            } else if (arr[hi] < arr[mid + 1]) {
                lo = mid + 1;
            } else {
                realHi = mid;
                realLo = mid + 1;
                break;
            }
        }
        //if realLo and realHi were not assigned in the loop
        //then, this case is equivalent to unrotated array
        if (realLo == -1 && realHi == -1) {
            realLo = 0;
            realHi = arr.length - 1;
        }
        return realLo;
    }

    public int binarySearchForRotatedArray(int val) {
        findOrigin();
        int lo = realLo;
        int hi = realHi;
        int size = arr.length;
        int curSize = arr.length;
        int valIndex = -1;
        int mid = Math.floorMod(realLo + (curSize / 2), size);
        while (curSize > 0 && arr[lo] < arr[hi]) {

            if (arr[mid] == val) {
                valIndex = mid;
                break;
            } else if (arr[mid] < val) {
                lo = Math.floorMod(mid + 1, size);
            } else {
                hi = mid;
            }
            curSize = curSize / 2;
            mid = Math.floorMod(lo + curSize / 2, size);
        }
        return valIndex;
    }


}
