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
        ram = new Ram(this);
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

    public void RA4() {
        if ((ram.getOpt() & 0b10000) == 0)
            return;

        prescaler.inc(1);
    }
}
