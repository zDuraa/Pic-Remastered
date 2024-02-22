package Model.Commands;

import Model.Command;
import Model.Pic;

public class RETFIE extends Command {

    public RETFIE(Pic pic) {
        super(0b00000000001001, pic);
    }

    // Return from Interrupt. Stack is POPed
    // and Top of Stack (TOS) is loaded in the
    // PC. Interrupts are enabled by setting
    // Global Interrupt Enable bit, GIE
    // (INTCON<7>). This is a two cycle
    // instruction.
    @Override
    public void execute(int command) {
        pic.pCounter.set(pic.stack.pop());
        pic.pCounter.dec();
    }
}
