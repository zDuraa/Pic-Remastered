package Model.Commands;

import Model.Command;
import Model.Pic;

public class IORWF extends Command {
    public IORWF(Pic pic) {
        super(0b000100, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        writeD(command, val);
    }
}
