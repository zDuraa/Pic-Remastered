package Model.Commands;

import Model.Command;
import Model.Pic;

public class BCF extends Command {
    public BCF(Pic pic) {
        super(0b0100, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b0001111111);
        int b = (command & 0b1110000000) >> 7;

        int mask = ~(1 << b);
        int val = pic.ram.getReg(f) & mask;
        pic.ram.setReg(f, val);
    }
}
