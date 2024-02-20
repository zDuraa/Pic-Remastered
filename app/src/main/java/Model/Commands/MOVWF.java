package Model.Commands;

import Model.Command;
import Model.Pic;

public class MOVWF extends Command {

    public MOVWF(Pic pic) {
        super(0b0000001, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        pic.ram.setReg(f, pic.w);

    }
}
