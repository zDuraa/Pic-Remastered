package Model.Commands;

import Model.*;

public class SUBLW extends Command {
    public SUBLW(Pic pic) {
        super(0b111100, pic, 1);
    }

    public void execute(int command) {
        int l = (command & 0b11111111);
        int ret = l - pic.w;

        checkCNew(l, pic.w);
        checkZ(ret);
        checkDC(l, pic.w, '-');

        if (ret < 0) {
            ret *= -1;
        }

        pic.w = ret & 0b11111111;
        incPrescaler();
    }

    private void checkCNew(int l, int w) {
        int reg = pic.ram.getReg(3);
        int bit = 0b1;
        int ret = 0;

        w ^= 0b11111111;
        w += 0b1;
        ret = l + w;

        if (ret >= 0b100000000) {
            reg |= bit;
        } else if ((reg & bit) > 0) {
            reg ^= bit;
        }

        pic.ram.setReg(3, reg);
    }

}
