package Model.Commands;

import Model.*;

public class CALL extends Command {

    public CALL(Pic pic) {
        super(0b100, pic,2);
    }

    @Override
    public void execute(int command) {
        int k = command & 0b11111111111;
        int returnAdr = pic.pCounter.get() + 1;
        int pcLath = pic.ram.getReg(10) << 10;

        pic.stack.push(returnAdr);
        pic.pCounter.set(k | pcLath);
        pic.pCounter.dec();
        incPrescaler();
    }

    @Override
    public boolean is(int command) {
        int shortC = command >> 11;
        boolean ret = false;

        if (0b100 == shortC) {
            ret = true;
        }

        return ret;
    }

}
