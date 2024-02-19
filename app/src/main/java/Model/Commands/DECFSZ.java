package Model.Commands;

import Model.Command;
import Model.Pic;

public class DECFSZ extends Command {

    public DECFSZ(Pic pic) {
        super(0b001011, pic);
    }

    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);

        //The contents of register ’f’ are decremented.
        int val = pic.ram.getReg(f);
        val--;

        //If ’d’ is 1 the result is placed
        //back in register ’f’.
        writeD(command, val);

        //If the result is not 0, the next instruction, is
        //executed. If the result is 0, then a NOP is
        //executed instead making it a 2TCY instruction.
        if(val == 0){
            //next instruction
        }else{
            //NOP
        }
    }
}
