package Model.Commands;

import Model.Command;
import Model.Pic;

public class RLF extends Command {
    public RLF(Pic pic) {
        super(0b001101, pic, 1);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        int oldCarryFlag = pic.ram.getReg(3);

        int newCarryFlag = (pic.ram.getReg(f) & 0b10000000) >> 7;

        int temp = ((oldCarryFlag) & 0b11111110) | newCarryFlag;

        pic.ram.setReg(3, temp);

        int test = oldCarryFlag & 0b1;
        int val = ((pic.ram.getReg(f) << 1) | test);

        if (val > 255) {
            val = val - 256;
        }
        writeD(command, val);
        incPrescaler();
    }

}
