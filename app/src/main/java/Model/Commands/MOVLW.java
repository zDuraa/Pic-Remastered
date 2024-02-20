package Model.Commands;

import Model.Command;
import Model.Pic;

public class MOVLW extends Command {
    public MOVLW(Pic pic) {
        super(0b1100, pic);
    }
    @Override
    public void execute(int command) {
        int l = (command & 0b11111111);
        pic.w = l;
    }
}
