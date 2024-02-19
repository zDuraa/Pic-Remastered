package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class SUBLWTest {
    @Test
    public void testErg() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SUBLW(pic);

        pic.w = 100;
        cmd.execute(50);

        assertEquals(50, pic.w);
    }

    @Test
    public void testZ() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SUBLW(pic);

        pic.w = 12;
        cmd.execute(12);

        int z = (pic.ram.getReg(3) & 0b100) >> 2;
        assertEquals(1, z);
        assertEquals(0, pic.w);
    }

    @Test
    public void zestC() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SUBLW(pic);

        pic.w = 10;
        cmd.execute(40);

        int c = pic.ram.getReg(3) & 0b1;
        assertEquals(1, c);
        assertEquals(30, pic.w);
    }

    @Test
    public void zestDC() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SUBLW(pic);

        pic.w = 29;
        cmd.execute(61);

        int dc = (pic.ram.getReg(3) & 0b10) >> 1;
        assertEquals(1, dc);
        assertEquals(32, pic.w);
    }

}
