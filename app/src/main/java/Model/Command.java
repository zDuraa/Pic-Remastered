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
}
