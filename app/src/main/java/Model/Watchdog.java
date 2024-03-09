package Model;

public class Watchdog {

    private Pic pic;

    public boolean WTD;
    private int quarzfrequenz;
    private int WTDVal;
    private int Zeit = 0;
    private boolean triggert = false;

    public Watchdog(Pic pic) {
        quarzfrequenz = 1000000;
        this.pic = pic;
        WTD = false;
    }

    public boolean getTriggert() {
        return triggert;
    }

    public int getZeit() {
        int ret = 1000000 / quarzfrequenz;
        return ret;
    }



    public void incWTD(int var) {
        if (WTD == false)
            return;

        WTDVal += getZeit() * var;
        if (18000 <= WTDVal) {
            triggert = true;
            pic.reset();
        }

    }


    public int checkOps() {
        int ret;

        switch (pic.ram.getOpt() & 0b111) {
            case 0b000:
                ret = 1;
                break;
            case 0b001:
                ret = 2;
                break;
            case 0b010:
                ret = 4;
                break;
            case 0b011:
                ret = 8;
                break;
            case 0b100:
                ret = 16;
                break;
            case 0b101:
                ret = 32;
                break;
            case 0b110:
                ret = 65;
                break;
            case 0b111:
                ret = 128;
                break;
            default:
                ret = 0; // Hier kannst du einen Standardwert setzen, falls keine der Bedingungen erfüllt
                // ist
        }

        return ret;
    }

    public void start() {
        WTD = true;
    }

    public void stop() {
        WTD = false;
    }

    public int get() {
        return WTDVal;
    }
}
