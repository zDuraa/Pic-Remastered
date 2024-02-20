package Model.Commands;

import Model.Command;
import Model.Pic;

public class BTFSS extends Command {
    public BTFSS(Pic pic) {
        super(0b0111, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b0001111111);
        int b = (command & 0b111);

        int mask = (1 << b);
        if((pic.ram.getReg(f) & mask) == 0){
            pic.pCounter.inc();
        }
    }
}
