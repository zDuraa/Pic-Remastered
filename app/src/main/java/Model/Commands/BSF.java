package Model.Commands;

import Model.Command;
import Model.Pic;

public class BSF extends Command {
    public BSF(Pic pic) {
        super(0b0101, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b0001111111);
        int b = (command & 0b1110000000) >> 7;

        int mask = (1 << b);
        int val = pic.ram.getReg(f) | mask;
        pic.ram.setReg(f, val);
    }

    @Override
    public boolean is(int command)
    {
        int val = command >> 10;
        boolean ret = false;
        if(val == 0b0101)
        {
            ret = true;
        }
        return ret;
    }
}
