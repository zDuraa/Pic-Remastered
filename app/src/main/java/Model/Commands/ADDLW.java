package Model.Commands;

import Model.*;

public class ADDLW extends Command {

    public ADDLW(Pic pic) {
        super(0b111110, pic);
    }

    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);

        this.pic.w += l;
    }
}
