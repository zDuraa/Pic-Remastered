package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CommandsTest {
    @Test
    public void addwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ADDWF(pic);

        pic.w = 10;
        cmd.execute(148);

        assertEquals(10, pic.ram.getReg(20));

        pic.w = 255;
        cmd.execute(20);

        assertEquals(9, pic.ram.getReg(20));
        int c = pic.ram.getReg(3) & 0b1;
        assertEquals(1, c);

    }

    @Test
    public void andwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new ANDWF(pic);

        pic.w = 0b1100;
        cmd.execute(20);

        assertEquals(0, pic.w);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;
        assertEquals(1, z);
    }

    @Test
    public void clrfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new CLRF(pic);

        pic.ram.setReg(20, 15);
        cmd.execute(20);

        assertEquals(0, pic.ram.getReg(20));
        int z = (pic.ram.getReg(3) & 0b100) >> 2;
        assertEquals(1, z);
    }

    @Test
    public void clrwTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new CLRW(pic);

        pic.w = 34;
        cmd.execute(0);

        assertEquals(0, pic.w);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;
        assertEquals(1, z);
    }

    @Test
    public void comfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new COMF(pic);

        pic.ram.setReg(20, 0b010101);
        cmd.execute(20);
        cmd.execute(148);

        assertEquals(0b101010, pic.w);
        assertEquals(0b101010, pic.ram.getReg(20));
    }

}
