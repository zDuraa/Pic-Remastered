package Model.Commands;

import Model.*;

public class ANDLW extends Command {

    public ANDLW(Pic pic) {
        super(0b111001, pic, 1);
    }

    public void execute(int command) {
        int l = command & 0b11111111;
        int ret = l & pic.w;

        checkZ(ret);

        pic.w = ret;
        incPrescaler();
    }
}
