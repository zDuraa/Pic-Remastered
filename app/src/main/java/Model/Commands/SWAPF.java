package Model.Commands;

import Model.Command;
import Model.Pic;

public class SWAPF extends Command {

    public SWAPF(Pic pic) {
        super(0b001110, pic, i);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        int upper = pic.ram.getReg(f) & 0b11110000;
        int lower = pic.ram.getReg(f) & 0b00001111;
        int val = (upper >> 4) | (lower << 4);

        writeD(command, val);

        incPrescaler();
    }
}
