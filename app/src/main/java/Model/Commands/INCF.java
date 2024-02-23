package Model.Commands;

import Model.Command;
import Model.Pic;

public class INCF extends Command {
    public INCF(Pic pic) {
        super(0b001010, pic, 1);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val = pic.ram.getReg(f);
        val++;
        if(val == 256){
            val = 0;
        }
        writeD(command, val);
        checkZ(val);
        incPrescaler();
    }
}
