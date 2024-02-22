package Model;

import java.util.ArrayList;

public class Pic {
    public Ram ram;
    public Stack stack;
    public PCounter pCounter;
    public Decoder decoder;

    private ArrayList<String> pCode;
    public int w;

    public Pic(ArrayList<String> ProgrammFile) {
        ram = new Ram();
        stack = new Stack();
        pCounter = new PCounter();
        decoder = new Decoder(this);

        pCode = ProgrammFile;
    }

    public void next() {
        decoder.decode(pCode.get(pCounter.get()));
        pCounter.inc();
    }

}
