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

    public void set(int val) {
        counter = val;
    }

    public void dec() {
        counter--;
    }
}
