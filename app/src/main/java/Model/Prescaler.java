package Model;

public class Prescaler {
    private int buffer;
    private Pic pic;

    public Prescaler(Pic pic) {
        this.pic = pic;
        buffer = 0;
    }

    public void inc(int val) {
        if ((pic.ram.getOpt() & 0b1000) == 0) {

            pic.watchdog.incWTD(val);
            buffer += val;

            if (buffer >= checkOps()) {
                buffer -= checkOps();
                pic.ram.incTmr0();
            }
        } else {
            pic.ram.incTmr0();
            buffer += val;
            if (buffer >= pic.watchdog.checkOps()) {
                buffer -= pic.watchdog.checkOps();
                pic.watchdog.incWTD(1);
            }
        }
    }

    public int checkOps() {
        int ret;

        switch (pic.ram.getOpt() & 0b111) {
            case 0b000:
                ret = 2;
                break;
            case 0b001:
                ret = 4;
                break;
            case 0b010:
                ret = 8;
                break;
            case 0b011:
                ret = 16;
                break;
            case 0b100:
                ret = 32;
                break;
            case 0b101:
                ret = 64;
                break;
            case 0b110:
                ret = 128;
                break;
            case 0b111:
                ret = 256;
                break;
            default:
                ret = 0; // Hier kannst du einen Standardwert setzen, falls keine der Bedingungen erfüllt
                         // ist
        }

        return ret;
    }

    public void reset() {
        buffer = 0;
    }
}
