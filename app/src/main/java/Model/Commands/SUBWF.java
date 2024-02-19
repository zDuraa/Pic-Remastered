package Model.Commands;

import Model.Command;
import Model.Pic;

public class SUBWF extends Command {
    public SUBWF(Pic pic) {
        super(0b000010, pic);
    }
    @Override
    public void execute(int command) {

    }
}
