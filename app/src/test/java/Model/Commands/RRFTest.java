package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RRFTest {
    @Test
    public void RLFc0() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new RLF(pic);

        pic.ram.setReg(10, 0b001100);
        pic.ram.setReg(3, 0b11111110);
        cmd.execute(10);

        assertEquals(0b11111110, pic.ram.getReg(3));
        assertEquals(0b0110, pic.w);
    }

    @Test
    public void RLFc1() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new RLF(pic);

        pic.ram.setReg(10, 0b10000001);
        pic.ram.setReg(3, 0b1);
        cmd.execute(10);

        assertEquals(0b1, pic.ram.getReg(3));
        assertEquals(0b11000000, pic.w);
        assertEquals(0b10000001, pic.ram.getReg(10));
    }
}
