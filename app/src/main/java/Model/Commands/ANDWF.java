package Model.Commands;

import Model.*;

public class ANDWF extends Command {

    public ANDWF() {
        super(0b000101);
    }

    @Override
    public void execute(Pic pic, int command) {
        int val = 0;
        int f = (command & 0b1111111);

        if(isD(command)){
            val = pic.w & pic.ram.getReg(f);
            pic.w = val;
        }else{
            val = pic.w & pic.ram.getReg(f);
            pic.ram.setReg(f, val);
        }
    }
}
