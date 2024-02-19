package Model.Commands;

import Model.Command;
import Model.Pic;

public class SWAPF extends Command {

    public SWAPF(Pic pic) {
        super(0b001110, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

    }
}
