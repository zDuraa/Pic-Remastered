package Model.Commands;

import Model.Command;
import Model.Pic;

import static java.lang.Thread.sleep;

public class SLEEP extends Command {
    public SLEEP(Pic pic) {
        super(0b00000001100011, pic, 1);
    }

    @Override
    public void execute(int command) {

        while (!pic.reset) {
            pic.watchdog.incWTD(1);
        }

        pic.reset = false;
    }

    public boolean is(int command) {
        boolean ret = false;

        if (command == bitmask) {
            ret = true;
        }

        return ret;
    }
}
