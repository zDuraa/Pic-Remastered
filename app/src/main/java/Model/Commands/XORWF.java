package Model.Commands;

import Model.Command;
import Model.Pic;

public class XORWF extends Command {

    public XORWF(Pic pic) {
        super(0b000110, pic, 1);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val = pic.ram.getReg(f) ^ pic.w;

        writeD(command, val);
        checkZ(val);

        incPrescaler();
    }
}
