package Model;

import java.util.ArrayList;

public class Pic {
    public Ram ram;
    public Stack stack;
    public PCounter pCounter;
    public Decoder decoder;
    public Prescaler prescaler;
    private Interrupt interrupt;
    public Watchdog watchdog;

    public ArrayList<String> pCode;
    public int w;

    public Pic(ArrayList<String> ProgrammFile) {
        ram = new Ram(this);
        stack = new Stack();
        pCounter = new PCounter();
        decoder = new Decoder(this);
        prescaler = new Prescaler(this);
        interrupt = new Interrupt(this);
        watchdog = new Watchdog(this);
        pCode = ProgrammFile;


    }

    public void next() {
        decoder.decode(pCode.get(pCounter.get()));
        pCounter.inc();
        interrupt.checkIe();
    }

    public void RA4() {
        if ((ram.getOpt() & 0b10000) == 0)
            return;

        prescaler.inc(1);
    }

    public void reset() {
        ram = new Ram(this);
        stack = new Stack();
        pCounter = new PCounter();
        decoder = new Decoder(this);
        prescaler = new Prescaler(this);
        interrupt = new Interrupt(this);
        watchdog = new Watchdog(this);

        pCounter.dec();
        w = 0;
    }
}
