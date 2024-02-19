package Model;

public abstract class Command {
    private int bitmask;
    protected Pic pic;

    public Command(int Bitmask, Pic pic) {
        bitmask = Bitmask;
        this.pic = pic;
    }

    public abstract void execute(int command);

    public boolean is(int command) {
        boolean ret = false;

        if ((command >> 8) == bitmask) {
            ret = true;
        }

        return ret;
    }

    protected void writeD(int command, int value) {
        int d = (command & 0b10000000) >> 7;
        int f = (command & 0b01111111);

        if (d == 0) {
            pic.w = value;
        } else {
            pic.ram.setReg(f, value);
        }
    }

    // the result musst be uncorrected (example -15 or 333)
    protected void checkZ(int val) {
        int reg = pic.ram.getReg(3);
        int bit = 0b100;
        int erg;

        if (val == 0 || val == 256) {
            erg = reg | bit;
        } else {
            erg = reg ^ bit;
        }

        pic.ram.setReg(3, erg);
    }

    // the result musst be uncorrected (example -15 or 333)
    protected void checkC(int val) {
        int reg = pic.ram.getReg(3);
        int bit = 0b10;
        int erg;

        if (val > 255 || val < 0) {
            erg = reg | bit;
        } else {
            erg = reg ^ bit;
        }

        pic.ram.setReg(3, erg);
    }
}
