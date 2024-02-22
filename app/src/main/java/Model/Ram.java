package Model;

public class Ram {
    private int[] buffer;

    public Ram() {
        buffer = new int[256];
    }

    public int getReg(int pos) {
        int ret;
        if (pos == 0) {
            ret = getReg(getReg(4));
        } else {
            ret = buffer[pos];
        }

        return ret;
    }

    public void setReg(int pos, int val) {
        if (pos == 0) {
            setReg(getReg(4), val);
            return;
        }

        // status register (pos3) should be mirrored
        // check if pr0 (03h 00100000) is set case: (bank 1 not active)
        if ((getReg(3) & 0b00100000) == 0 || pos == 3) {
            buffer[pos] = val;
        }

        // check if pr0 (03h 00100000) is set case: (bank 1 active)
        if ((getReg(3) & 0b00100000) > 0 || pos == 3) {
            buffer[pos + 128] = val;
        }
    }
}
