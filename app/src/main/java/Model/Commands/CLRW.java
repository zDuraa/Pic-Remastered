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

    @Override
    public boolean is(int command)
    {
        int val = command >> 7;
        boolean ret = false;
        if(val == bitmask)
        {
            ret = true;
        }
        return ret;
    }
}
