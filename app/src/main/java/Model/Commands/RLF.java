package Model.Commands;

import Model.Command;
import Model.Pic;

public class RLF extends Command {
    public RLF(Pic pic) {
        super(0b001101, pic);
    }
    @Override
    public void execute(int command) {

    }
}
