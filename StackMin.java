/*
 *Create a stack with min val retrieval in O(1)
 *
 * Create main stack
 * Create min val stack
 * During push of main stack, compare the min val ,
 *    if current val is lesser push to min stack
 *
 * During pop of main stack, compare the min val,
 *    if its same, then pop the min stack too
 *
 * Another approach is to create a stack with Node containing the min value of the substack, looking like this: 
 * Node { Node next; int data; Node minSubStack; } 
 * **  */

import java.lang.Integer;

public class StackMin {
    private Stack<Integer> mainStack = new Stack<Integer>();
    private Stack<Integer> minValStack = new Stack<Integer>();
    private Integer min;

    Integer getMinVal() {
        return min;
    }

    public void push(Integer val) {

        if(mainStack.isEmpty()){
            min = val;
            minValStack.push(min);
        }
        else if (min.compareTo(val) >= 0) {
            //handle duplicates too - push duplicate values to stack... helps during pop
            minValStack.push(val);
            min = val;
        }
        mainStack.push(val);

    }

    public Integer pop() {

        if (!mainStack.isEmpty()) {
            Integer data = mainStack.pop();
            if (min.compareTo(data) == 0) {
                 minValStack.pop();
                 //check if not empty before this operation
                 min = minValStack.peek();
            }
            return data;
        }
        //exception handling for empty stack..
        return Integer.MAX_VALUE ;
    }

    // testing...
    public static void main(String[] args) {
        StackMin oMinStack = new StackMin();
        //
        /* tested for the below inputs:
        * Ascending order push 1,2,3,4
        * Descending order push 44,33,22,11
        * Random push 33, -22 , 0 , 22 , -22 , 66
         */

        oMinStack.push(33);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(-22);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(0);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(22);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(-22);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(66);
        System.out.println("MinVal " + oMinStack.getMinVal());
        oMinStack.push(-333);
        System.out.println("MinVal " + oMinStack.getMinVal());

        System.out.println("Pop " + oMinStack.pop());
        System.out.println("MinVal " + oMinStack.getMinVal());
        System.out.println("Pop " + oMinStack.pop());
        System.out.println("MinVal " + oMinStack.getMinVal());
        System.out.println("Pop " + oMinStack.pop());
        System.out.println("MinVal " + oMinStack.getMinVal());


    }

}


