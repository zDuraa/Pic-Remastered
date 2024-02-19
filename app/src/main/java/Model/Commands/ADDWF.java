package Model.Commands;

import Model.*;

public class ADDWF extends Command {
    public ADDWF() {
        super(0b000111);
    }
    @Override
    public void execute(Pic pic, int command) {
        int val = 0;
        int f = (command & 0b1111111);

        if(isD(command)){
            val = pic.w + pic.ram.getReg(f);
            pic.w = val;
        }else{
            val = pic.w + pic.ram.getReg(f);
            pic.ram.setReg(f, val);
        }
    }
}
