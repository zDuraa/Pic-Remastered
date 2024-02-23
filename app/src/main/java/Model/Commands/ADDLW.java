package Model.Commands;

import Model.*;

public class ADDLW extends Command {

    public ADDLW(Pic pic) {
        super(0b111110, pic, 1);
    }

    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);
        int ret = l + pic.w;

        checkC(ret);
        checkZ(ret);
        checkDC(l, pic.w, '+');

        pic.w = ret & 0b11111111;
        incPrescaler();
    }

}
