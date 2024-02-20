package Model.Commands;

import Model.Command;
import Model.Pic;

public class CLRWDT extends Command {
    public CLRWDT(Pic pic) {
        super(0b00000001100100, pic);
    }
    @Override
    public void execute(int command) {

    }
}
