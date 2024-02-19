package Model.Commands;

import Model.*;
import Model.Commands.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ADDLWTest {

    @Test
    public void TestErg() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDLW(pic);

        pic.w = 120;
        cmd.execute(120);

        assertEquals(240, pic.w);

    }

    @Test
    public void TestDC() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDLW(pic);

        pic.w = 0b1111;
        cmd.execute(0b1);

        int dc = (pic.ram.getReg(3) & 0b10) >> 1;

        assertEquals(1, dc);
    }

    @Test
    public void TestC() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDLW(pic);

        pic.w = 0b11111111;
        cmd.execute(0b1);

        int c = (pic.ram.getReg(3) & 0b1);

        assertEquals(1, c);
    }

    @Test
    public void TestZ() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDLW(pic);

        pic.w = 0b11111111;
        cmd.execute(0b1);

        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(1, z);
    }

    @Test
    public void overflow() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDLW(pic);

        pic.w = 240;
        cmd.execute(27);

        assertEquals(11, pic.w);
    }

}
