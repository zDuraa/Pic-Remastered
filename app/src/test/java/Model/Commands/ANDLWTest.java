package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ANDLWTest {
    @Test
    public void TestErg() {
        Pic pic = new Pic(new ArrayList<>());
        ANDLW cmd = new ANDLW(pic);

        pic.w = 0b1010101;
        cmd.execute(0b1);

        assertEquals(1, pic.w);
    }

    @Test
    public void TestZ() {
        Pic pic = new Pic(new ArrayList<>());
        ANDLW cmd = new ANDLW(pic);

        pic.w = 0b11101;
        cmd.execute(0b0);

        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(1, z);
    }
}
