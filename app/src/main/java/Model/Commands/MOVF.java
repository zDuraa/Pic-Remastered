package Model.Commands;

import Model.Command;
import Model.Pic;

public class MOVF extends Command {
    public MOVF(Pic pic) {
        super(0b001000, pic,1);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        int val = pic.ram.getReg(f);
        writeD(command, val);
        checkZ(val);
        incPrescaler();
    }
}
