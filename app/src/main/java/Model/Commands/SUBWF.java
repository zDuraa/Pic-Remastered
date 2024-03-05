package Model.Commands;

import Model.Command;
import Model.Pic;

public class SUBWF extends Command {
    public SUBWF(Pic pic) {
        super(0b000010, pic, 1);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val = pic.ram.getReg(f) - pic.w;

        checkZ(val);
        checkDC(pic.ram.getReg(f), pic.w, '-');
        checkCNew(pic.ram.getReg(f), pic.w);

        val &= 0b11111111;

        writeD(command, val);

        incPrescaler();
    }

    private void checkCNew(int val, int w) {
        int reg = pic.ram.getReg(3);
        int bit = 0b1;
        int ret = 0;

        w ^= 0b11111111;
        w += 0b1;
        ret = val + w;

        if (ret >= 0b100000000) {
            reg |= bit;
        } else if ((reg & bit) > 0) {
            reg ^= bit;
        }

        pic.ram.setReg(3, reg);
    }

}
