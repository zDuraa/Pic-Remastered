package Model.Commands;

import Model.Command;
import Model.Pic;

public class RLF extends Command {
    public RLF(Pic pic) {
        super(0b001101, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        int temp = pic.ram.getReg(3);

        int carryFlag = (pic.ram.getReg(f) & 0b10000000) >> 7;
        pic.ram.setReg(3, carryFlag);

        int val = (pic.ram.getReg(f) << 1) | temp;

        writeD(command, val);
        checkC(val);
    }
}
