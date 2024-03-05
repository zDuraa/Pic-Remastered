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

        assertEquals(10, pic.ram.getReg(20));
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

        assertEquals(0b11101010, pic.w);
        assertEquals(0b11101010, pic.ram.getReg(20));
    }

    @Test
    public void decfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new DECF(pic);

        cmd.execute(20);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(255, pic.w);
        assertEquals(0, z);
    }

    public void decfsz() {
    }

    @Test
    public void incfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new INCF(pic);

        pic.ram.setReg(20, 255);
        cmd.execute(20);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(0, pic.w);
        assertEquals(1, z);
    }

    @Test
    public void iorwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new IORWF(pic);

        pic.w = 0b1001;
        pic.ram.setReg(29, 0b0101);
        cmd.execute(29);

        assertEquals(0b1101, pic.w);

        pic.w = 0b0;
        pic.ram.setReg(12, 0);
        cmd.execute(12);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(0, pic.w);
        assertEquals(1, z);
    }

    @Test
    public void movfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new MOVF(pic);

        pic.w = 20;
        cmd.execute(45);
        int z = (pic.ram.getReg(3) & 0b100) >> 2;

        assertEquals(0, pic.w);
        assertEquals(z, 1);

    }

    @Test
    public void movwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new MOVWF(pic);

        pic.w = 0;
        pic.ram.setReg(65, 20);
        cmd.execute(65);

        assertEquals(0, pic.ram.getReg(65));
    }

    @Test
    public void subwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SUBWF(pic);

        pic.w = 20;
        pic.ram.setReg(72, 30);
        cmd.execute(72);
        int c = pic.ram.getReg(3) & 0b1;

        assertEquals(10, pic.w);
        assertEquals(1, c);
    }

    @Test
    public void xorwfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new XORWF(pic);

        pic.w = 0b10101;
        pic.ram.setReg(15, 0b01011);
        cmd.execute(15);

        assertEquals(0b11110, pic.w);
    }

    @Test
    public void swapfTest() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new SWAPF(pic);

        pic.ram.setReg(30, 0b00111100);
        cmd.execute(30);

        assertEquals(0b11000011, pic.w);
    }

}
