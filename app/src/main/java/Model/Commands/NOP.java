package Model.Commands;

import Model.Command;
import Model.Pic;

public class NOP extends Command {

    public NOP(Pic pic) {
        super(0b000000, pic);
    }
    @Override
    public void execute(int command) {

        System.out.println("NOP");
    }
}
