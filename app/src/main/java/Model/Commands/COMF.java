package Model.Commands;

import Model.Command;
import Model.Pic;

public class COMF extends Command {

    public COMF(Pic pic) {
        super(0b001001, pic);
    }
    @Override
    public void execute(int command) {

        int f = (command & 0b1111111);
        int val = pic.ram.getReg(f);
        val++;
        writeD(command, val);
    }
}
