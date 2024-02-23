package Model.Commands;

import Model.*;

public class ANDWF extends Command {

    public ANDWF(Pic pic) {
        super(0b000101, pic, 1);
    }

    @Override
    public void execute(int command) {
        int val = 0;
        int f = (command & 0b1111111);

        val = pic.w & pic.ram.getReg(f);
        checkZ(val);
        writeD(command, val);
        incPrescaler();
    }
}
