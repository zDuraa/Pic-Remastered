package Model;

public class Ram {
    private int[] buffer;
    private Pic pic;

    public Ram(Pic pic) {
        this.pic = pic;
        buffer = new int[256];

        //Bank 0
        buffer[0] = 0;
        buffer[2] = 0;
        buffer[3] = 24;
        buffer[7] = 0;
        buffer[10] = 0;

        //Bank 1
        buffer[128] = 0;
        buffer[129] = 255;
        buffer[130] = 0;
        buffer[131] = 24;
        buffer[133] = 31;
        buffer[134] = 255;
        buffer[135] = 0;
        buffer[137] = 0;
        buffer[138] = 0;
    }

    public int getReg(int pos) {
        int ret = 0;
        if (pos == 0) {
            return getReg(getReg(4));
        }

        if ((buffer[3] & 0b00100000) == 0) {
            ret = buffer[pos];
        }

        if ((buffer[3] & 0b00100000) > 0) {
            ret = buffer[pos + 128];
        }

        return ret;
    }

    public void setReg(int pos, int val) {
        if (pos == 0) {
            setReg(getReg(4), val);
            return;
        }

        if (pos == 1) {
            pic.prescaler.reset();
        }

        // status register (pos3) should be mirrored
        // check if pr0 (03h 00100000) is set case: (bank 1 not active)
        if ((getReg(3) & 0b00100000) == 0 || pos == 3 || pos == 4 || pos == 11) {
            buffer[pos] = val;
        }

        // check if pr0 (03h 00100000) is set case: (bank 1 active)
        if ((getReg(3) & 0b00100000) > 0 || pos == 3 || pos == 4 || pos == 11) {
            buffer[pos + 128] = val;
        }
    }

    public int GetTmr0() {
        return buffer[1];
    }

    public void setTmr0(int val) {
        buffer[1] = val;
    }

    public void incTmr0() {
        int val = GetTmr0();
        val++;

        if (val > 255) {
            val = 0;
        }

        setTmr0(val);
    }

    public int getOpt() {
        return buffer[129];
    }

    public int getBuffer(int pos){
        return buffer[pos];
    }

}
