package Model;

public class PCounter {
    private int counter;
    private Pic pic;

    public PCounter(Pic pic) {
        counter = 0;
        this.pic = pic;
    }

    public void inc() {
        counter++;
        pic.ram.setReg(2, counter);
    }

    public int get() {
        return counter;
    }

    public void set(int val) {
        counter = val;
        pic.ram.setReg(2, counter);
    }

    public void dec() {
        counter--;
        pic.ram.setReg(2, counter);
    }
}
