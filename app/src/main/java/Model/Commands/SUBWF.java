package Model.Commands;

import Model.Command;
import Model.Pic;

public class SUBWF extends Command {
    public SUBWF(Pic pic) {
        super(0b000010, pic);
    }
    @Override
    public void execute(int command) {
        int f = (command & 0b1111111);
        int val =  pic.ram.getReg(f)-pic.w;
        writeD(command, val);

        checkZ(val);
        checkC(val);
        checkDC(val,pic.w, '-');
    }
}
