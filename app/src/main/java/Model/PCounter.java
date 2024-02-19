package Model;

public class PCounter {
    private int counter;

    public PCounter() {
        counter = 0;
    }

    public void inc() {
        counter++;
    }

    public int get() {
        return counter;
    }

    public void incWith(int val) {
        counter += val;
    }

    /*
     * public static void main(String[] args) {
     * PCounter pCounter = new PCounter();
     * pCounter.inc();
     * System.out.println(pCounter.get());
     * pCounter.incWith(20);
     * System.out.println(pCounter.get());
     * }
     */
}
