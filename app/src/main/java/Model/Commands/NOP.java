package Model.Commands;

import Model.Command;
import Model.Pic;

public class NOP extends Command {

    public NOP(Pic pic) {
        super(0b00000000000000, pic,1);
    }
    @Override
    public void execute(int command) {

        incPrescaler();
    }

    @Override
    public boolean is(int command)
    {
        boolean ret = false;
        if(command == 0b00000000000000)
        {
            ret = true;
        }
        return ret;
    }
}
