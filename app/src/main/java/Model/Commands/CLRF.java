package Model.Commands;

import Model.Command;
import Model.Pic;

public class CLRF extends Command {


    public CLRF(Pic pic) {
        super(0b0000011, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        pic.ram.setReg(f, 0);
        checkZ(0);
    }

    @Override
    public boolean is(int command)
    {
        int val = command >> 7;
        boolean ret = false;
        if(val == 0b0000011)
        {
            ret = true;
        }
        return ret;
    }
}
