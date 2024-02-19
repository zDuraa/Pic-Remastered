package Model.Commands;

import Model.Command;
import Model.Pic;

public class CLRW extends Command {

    public CLRW(Pic pic) {
        super(0b0000010, pic);
    }
    @Override
    public void execute(int command) {
        pic.w = 0;
        checkZ(0);
    }
}
