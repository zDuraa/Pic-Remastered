package Model.Commands;

import Model.Command;
import Model.Pic;

public class RETLW extends Command {

    public RETLW(Pic pic) {
        super(0b110100, pic, 2);
    }

    // The W register is loaded with the eight
    // bit literal ’k’. The program counter is
    // loaded from the top of the stack (the
    // return address). This is a two cycle
    // instruction.
    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);
        pic.w = l;

        pic.pCounter.set(pic.stack.pop());
        pic.pCounter.dec();
        incPrescaler();
    }
}
