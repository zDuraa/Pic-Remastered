package Model.Commands;

import Model.Command;
import Model.Pic;

public class IORLW extends Command {
    public IORLW(Pic pic) {
        super(0b111000, pic);
    }
    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);
        int val = pic.w | l;

        pic.w = val;
        checkZ(val);
    }
}
