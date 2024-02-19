package Model.Commands;

import Model.*;

public class ADDLW extends Command {

    public ADDLW() {
        super(0b111110);
    }

    @Override
    public void execute(Pic pic, int command) {
        int l = (command & 0b11111111);

        pic.w += l;
    }
}
