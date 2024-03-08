package Model;

public abstract class Command {
    protected int bitmask;
    protected Pic pic;
    protected int zyc;

    public Command(int Bitmask, Pic pic, int Zyc) {
        zyc = Zyc;
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
            if(f == 2){
                int temp = pic.pCounter.get() & 0b1100000000;
                pic.pCounter.set(value | temp);
            }
        }
    }

    // the result musst be uncorrected (example -15 or 333)
    protected void checkZ(int val) {
        int reg = pic.ram.getReg(3);
        int bit = 0b100;
        int erg = reg;

        if (val == 0 || val == 256) {
            erg = reg | bit;
        } else if ((reg & 0b100) > 0) {
            erg = reg ^ bit;
        }

        pic.ram.setReg(3, erg);
    }

    // the result musst be uncorrected (example -15 or 333)
    protected void checkC(int val) {
        int reg = pic.ram.getReg(3);
        int bit = 0b1;
        int erg = reg;

        if (val > 255 || val < 0) {
            erg = reg | bit;
        } else if ((reg & 0b1) > 0) {
            erg = reg ^ bit;
        }

        pic.ram.setReg(3, erg);
    }

    // val = l or value of f
    // w = w register
    // operation = '+' or '-'
    protected void checkDC(int val, int w, char operation) {
        int reg = pic.ram.getReg(3);
        int bit = 0b10;
        int lshort = 0b1111 & val;
        int wshort = 0b1111 & w;
        int ret = 0;

        switch (operation) {
            case '+':
                ret = lshort + wshort;
                break;

            case '-':
                wshort ^= 0b1111;
                wshort += 0b1;
                ret = lshort + wshort;
                break;
        }

        if (ret >= 0b10000) {
            reg |= bit;
        } else if ((reg & 0b010) > 0) {
            reg ^= bit;
        }

        pic.ram.setReg(3, reg);
    }

    public void incPrescaler() {
        if ((pic.ram.getOpt() & 0b100000) == 0 || (pic.ram.getOpt() & 0b100) == 0b100)
            pic.prescaler.inc(zyc);
    }

}
