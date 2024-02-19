package Model;

public class Stack {
    private int[] buffer;
    private int pointer;

    public Stack() {
        buffer = new int[8];
        pointer = 0;
    }

    public void push(int val) {
        buffer[pointer] = val;
        pointer++;

        if (pointer >= 8) {
            pointer = 0;
        }
    }

    public int pop() {
        int ret = buffer[pointer - 1];
        pointer--;

        if (pointer < 0) {
            pointer = 7;
        }

        return ret;
    }

    public int[] getBuffer() {
        return buffer;
    }

    /*
     * public static void main(String[] args) {
     * Stack stack = new Stack();
     * stack.push(1);
     * stack.push(2);
     * System.out.println("" + stack.pop());
     * stack.push(3);
     * System.out.println("" + stack.pop());
     * System.out.println(stack.pop());
     * }
     */

}
