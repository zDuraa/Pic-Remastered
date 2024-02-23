package Model.Commands;

import Model.Command;
import Model.PCounter;
import Model.Pic;

public class RETURN extends Command {
    public RETURN(Pic pic) {
        super(0b00000000001000, pic, 2);
    }

    // Return from subroutine. The stack is
    // POPed and the top of the stack (TOS)
    // is loaded into the program counter. This
    // is a two cycle instruction.
    @Override
    public void execute(int command) {
        pic.pCounter.set(pic.stack.pop());
        pic.pCounter.dec();

        incPrescaler();
    }

    @Override
    public boolean is(int command) {
        boolean ret = false;

        if (command == bitmask) {
            ret = true;
        }

        return ret;
    }

}
