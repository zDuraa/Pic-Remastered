package Model.Commands;

import Model.*;

public class ADDWF extends Command {
    public ADDWF(Pic pic) {
        super(0b000111, pic);
    }
    @Override
    public void execute(int command) {
        int val = 0;
        int f = (command & 0b1111111);

        val = pic.w + pic.ram.getReg(f);
        writeD(command, val);
    }


}
