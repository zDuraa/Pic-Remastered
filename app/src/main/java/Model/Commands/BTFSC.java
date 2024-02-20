package Model.Commands;

import Model.Command;
import Model.Pic;

public class BTFSC extends Command {

    public BTFSC(Pic pic) {
        super(0b0110, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b0001111111);
        int b = (command & 0b111);

        if((pic.ram.getReg(f) & b) == 1){
            pic.pCounter.inc();
        }
    }
}
