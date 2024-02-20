package Model.Commands;

import Model.Command;
import Model.Pic;

public class XORLW extends Command {

    public XORLW(Pic pic) {
        super(0b111010, pic);
    }
    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);
        int val = l ^ pic.w;

        pic.w = l;
        checkZ(val);
    }
}
