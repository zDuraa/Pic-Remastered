package Model;

public class Interrupt {
    private Pic pic;
    private int oldTimer = 0;

    public Interrupt(Pic pic) {
        this.pic = pic;
    }

    public void checkIe() {
        checkT0();
    }

    public void checkT0() {
        int gie = (pic.ram.getReg(11) & 0b10000000) >> 7;
        int t0ie = (pic.ram.getReg(11) & 0b00100000) >> 5;

        if (gie != 1 || t0ie != 1)
            return;

        if (oldTimer == 255 && pic.ram.GetTmr0() == 0) {
            var oldIntcon = pic.ram.getReg(11);
            // set INTF (timer overflow flag)
            var newIntcon = oldIntcon | 0b100;
            pic.ram.setReg(11, newIntcon);
            pic.stack.push(pic.pCounter.get());
            pic.pCounter.set(4);
        }

        oldTimer = pic.ram.GetTmr0();
    }

}
