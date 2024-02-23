package Model;

import java.util.ArrayList;

public class Pic {
    public Ram ram;
    public Stack stack;
    public PCounter pCounter;
    public Decoder decoder;
    public Prescaler prescaler;

    private ArrayList<String> pCode;
    public int w;

    public Pic(ArrayList<String> ProgrammFile) {
        ram = new Ram();
        stack = new Stack();
        pCounter = new PCounter();
        decoder = new Decoder(this);
        prescaler = new Prescaler(this);

        pCode = ProgrammFile;
    }

    public void next() {
        decoder.decode(pCode.get(pCounter.get()));
        pCounter.inc();
    }

    public void incTmr0() {
        int val = ram.getReg(1);
        val++;

        if (val > 255) {
            val = 0;
        }
    }
}
