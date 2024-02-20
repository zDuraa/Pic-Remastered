package Model.Commands;

import Model.*;

public class ADDWF extends Command {
    public ADDWF(Pic pic) {
        super(0b000111, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val = pic.w + pic.ram.getReg(f);

        checkC(val);
        checkZ(val);
        checkDC(val,pic.w, '+');

        writeD(command, val);
    }


}
