package Model;

public abstract class Command {
    private int bitmask;

    public Command(int Bitmask) {
        bitmask = Bitmask;
    }

    public abstract void execute(Pic pic, int command);

    public boolean is(int command) {
        boolean ret = false;

        if ((command >> 8) == bitmask) {
            ret = true;
        }

        return ret;
    }
}
