package Model.Commands;

import Model.Command;
import Model.Pic;

public class RRF extends Command {
    public RRF(Pic pic) {
        super(0b001100, pic);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        int status = pic.ram.getReg(3);
        int reg = pic.ram.getReg(f);
        int carry = status & 0b1;

        int newCarry = reg & 0b1;
        int newStatus = (status & 0b11111110) | newCarry;

        int ret = (reg >> 1) | (carry << 7);

        pic.ram.setReg(3, newStatus);
        writeD(command, ret);

    }
}
