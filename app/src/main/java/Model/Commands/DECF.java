package Model.Commands;

import Model.Command;
import Model.Pic;

public class DECF extends Command {

    public DECF(Pic pic) {
        super(0b000011, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val = pic.ram.getReg(f);
        val--;
        if(val == -1){
            val = 255;
        }
        writeD(command, val);
        checkZ(val);
    }
}
