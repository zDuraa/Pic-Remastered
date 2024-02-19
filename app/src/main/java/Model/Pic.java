package Model;

import java.util.ArrayList;
import Model.Commands.*;

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
        decoder = new Decoder();

        pCode = ProgrammFile;
    }

    public void next() {
        decoder.decode(pCode.get(pCounter.get()), this);
        pCounter.inc();
    }

    /*static public void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("3E01");

        Pic pic = new Pic(list);
        pic.decoder.addCmd(new ADDLW());
        pic.next();
        System.out.println(pic.w);
    }*/
}
