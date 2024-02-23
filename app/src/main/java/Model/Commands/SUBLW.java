package Model.Commands;

import Model.*;

public class SUBLW extends Command {
    public SUBLW(Pic pic) {
        super(0b111100, pic, 1);
    }

    public void execute(int command) {
        int l = (command & 0b11111111);
        int ret = pic.w - l;

        checkC(ret);
        checkZ(ret);
        checkDC(l, pic.w, '-');

        if (ret < 0) {
            ret *= -1;
        }

        pic.w = ret & 0b11111111;
        incPrescaler();
    }
}
