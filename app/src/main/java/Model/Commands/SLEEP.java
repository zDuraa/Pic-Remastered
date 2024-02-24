package Model.Commands;

import Model.Command;
import Model.Pic;

public class SLEEP extends Command {
    public SLEEP(Pic pic) {
        super(0b00000001100011, pic, 1);
    }

    @Override
    public void execute(int command) {

        pic.pCounter.dec();
        incPrescaler();
    }

    public boolean is(int command) {
        boolean ret = false;

        if (command == bitmask) {
            ret = true;
        }

        return ret;
    }
}
