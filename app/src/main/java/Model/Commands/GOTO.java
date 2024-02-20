package Model.Commands;

import Model.*;

public class GOTO extends Command {
    public GOTO(Pic pic) {
        super(0b101, pic);
    }

    @Override
    public void execute(int command) {
        int k = command & 0b11111111111;
        int PCLath = (pic.ram.getReg(10) & 0b11000) << 7;
        pic.pCounter.set((k | PCLath) & 0b1111111111111);
    }

    @Override
    public boolean is(int command) {
        int shortC = (command & 0b11100000000000) >> 11;
        boolean ret = false;

        if (shortC == 0b101) {
            ret = true;
        }

        return ret;
    }
}
