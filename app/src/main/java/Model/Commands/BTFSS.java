package Model.Commands;

import Model.Command;
import Model.Pic;

public class BTFSS extends Command {
    public BTFSS(Pic pic) {
        super(0b0111, pic,1);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b0001111111);
        int b = (command & 0b1110000000) >> 7;

        int mask = (1 << b);
        if((pic.ram.getReg(f) & mask) >= 1){
            pic.pCounter.inc();
        }
        incPrescaler();
    }
    @Override
    public boolean is(int command)
    {
        int val = command >> 10;
        boolean ret = false;
        if(val == 0b0111)
        {
            ret = true;
        }
        return ret;
    }
}
