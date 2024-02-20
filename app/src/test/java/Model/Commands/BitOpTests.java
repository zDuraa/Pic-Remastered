package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class BitOpTests {
    @Test
    public void bcfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new BCF(pic);

        pic.ram.setReg(33, 0b010);
        cmd.execute(0b100000000 + 33);

        assertEquals(0, pic.ram.getReg(33));
    }

    @Test
    public void bsfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new BSF(pic);

        pic.ram.setReg(33, 0);
        cmd.execute(0b100000000 + 33);

        assertEquals(2, pic.ram.getReg(30));
    }

    @Test
    public void btfscTestIf0() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new BTFSC(pic);

        pic.pCounter.set(15);
        cmd.execute(33);

        assertEquals(16, pic.pCounter.get());
    }

    @Test
    public void btfscTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new BTFSC(pic);

        pic.pCounter.set(15);
        pic.ram.setReg(33, 1);
        cmd.execute(33);

        assertEquals(15, pic.pCounter.get());
    }

    // @Test
    // public void

}
