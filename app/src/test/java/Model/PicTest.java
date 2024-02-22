package Model;

import Model.Commands.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class PicTest {
    @Test
    public void test1() {
        ArrayList<String> list = new ArrayList<>();
        list.add("3011");
        list.add("3930");
        list.add("380D");
        list.add("3C3D");
        list.add("3A20");
        list.add("3E25");

        Pic pic = new Pic(list);

        pic.next();
        assertEquals(17, pic.w);

        pic.next();
        assertEquals(16, pic.w);
        assertEquals(0b000, pic.ram.getReg(3) & 0b100);

        pic.next();
        assertEquals(29, pic.w);
        assertEquals(0b000, pic.ram.getReg(3) & 0b100);

        pic.next();
        assertEquals(32, pic.w);
        assertEquals(0b011, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(0, pic.w);
        assertEquals(0b111, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(37, pic.w);
        assertEquals(0b000, pic.ram.getReg(3) & 0b111);
    }

    @Test
    public void test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("3011");
        list.add("2006");
        list.add("0000");
        list.add("2008");
        list.add("0000");
        list.add("2800");
        list.add("3E25");
        list.add("0008");
        list.add("3477");

        Pic pic = new Pic(list);

        pic.next();
        assertEquals(17, pic.w);

        pic.next();
        assertEquals(2, pic.stack.getStack()[0]);
        assertEquals(6, pic.pCounter.get());

        pic.next();
        assertEquals(54, pic.w);

        pic.next();
        assertEquals(2, pic.pCounter.get());

        pic.next();
        assertEquals(54, pic.w);

        pic.next();
        assertEquals(4, pic.stack.getStack()[0]);
        assertEquals(8, pic.pCounter.get());

        pic.next();
        assertEquals(119, pic.w);
        assertEquals(4, pic.stack.getStack()[0]);

        pic.next();
        assertEquals(119, pic.w);
    }

    // 0000 3011 00035 movlw 11h ;in W steht nun 11h, DC=?, C=?, Z=?
    // 0001 008C 00036 movwf wert1 ;diesen Wert abspeichern, DC=?, C=?, Z=?
    // 0002 3014 00037 movlw 14h ;W = 14h, DC=?, C=?, Z=?
    // 0003 070C 00038 addwf wert1,w ;W = 25h, DC=0, C=0, Z=0
    // 0004 078C 00039 addwf wert1 ;W = 25h, wert1 = 36h, DC=0, C=0, Z=0
    // 0005 050C 00040 andwf wert1,w ;W = 24h, wert1 = 36h, DC=0, C=0, Z=0
    // 0006 008D 00041 movwf wert2 ;W=24h, wert1=36, wert2=24h
    // 0007 018C 00042 clrf wert1 ;W=24h, wert1=0, wert2=24h, DC=0, C=0, Z=1
    // 0008 090D 00043 comf wert2,w ;W=DBh, wert1=0, wert2=24h, DC=0, C=0, Z=0

    // 0009 030C 00044 decf wert1,w ;W=FFh, wert1=0, wert2=24h, DC=0, C=0, Z=0
    // 000A 0A8D 00045 incf wert2 ;W=FFh, wert1=0, wert2=25h, DC=0, C=0, Z=0
    // 000B 088C 00046 movf wert1 ;W=FFh, wert1=0, wert2=25h, DC=0, C=0, Z=1
    // 000C 048C 00047 iorwf wert1 ;W=FFh, wert1=FFh, wert2=25h, DC=0, C=0, Z=0
    // 000D 020D 00048 subwf wert2,w ;W=26h, wert1=FFh, wert2=25h, DC=0, C=0, Z=0
    // 000E 0E8D 00049 swapf wert2 ;W=26h, wert1=FFh, wert2=52h, DC=0, C=0, Z=0
    // 000F 068C 00050 xorwf wert1 ;W=26h, wert1=D9h, wert2=52h, DC=0, C=0, Z=0
    // 0010 0100 00051 clrw ;W=00h, wert1=D9h, wert2=52h, DC=0, C=0, Z=1
    // 00052
    // 0011 020C 00053 subwf wert1,w ;W=D9h, wert1=D9h, wert2=52h, DC=1, C=1, Z=0
    // 0012 020D 00054 subwf wert2,w ;W=79h, wert1=D9h, wert2=52h, DC=0, C=0, Z=0
    // 0013 028D 00055 subwf wert2 ;W=79h, wert1=D9h, wert2=D9h, DC=0, C=0, Z=0
    // 0014 028D 00056 subwf wert2 ;W=79h, wert1=D9h, wert2=60h, DC=1, C=1, Z=0
    // 00057
    // 00058
    // 00059 ende
    @Test
    public void test3() {
        ArrayList<String> list = new ArrayList<>();

        list.add("3011"); // movlw
        list.add("008C"); // movwf
        list.add("3014"); // movlw
        list.add("070C"); // addwf
        list.add("078C"); // addwf
        list.add("050C"); // andwf
        list.add("008D"); // movwf
        list.add("018C"); // clrf
        list.add("090D"); // comf
        list.add("030C"); // decf
        list.add("0A8D"); // incf
        list.add("088C"); // movf
        list.add("048C"); // iorwf
        list.add("020D"); // subwf
        list.add("0E8D"); // swapf
        list.add("068C"); // xorwf
        list.add("0100"); // clearw
        list.add("020C"); // subwf
        list.add("020D"); // subwf
        list.add("028D"); // subwf
        list.add("028D"); // subwf

        Pic pic = new Pic(list);

        pic.next();
        assertEquals(17, pic.w);
        pic.next();

        pic.next();
        assertEquals(20, pic.w);
        pic.next();
        assertEquals(37, pic.w);
        pic.next();
        assertEquals(37, pic.w);
        pic.next();
        assertEquals(36, pic.w);
        pic.next();
        assertEquals(36, pic.w);
        pic.next();
        assertEquals(36, pic.w);
        pic.next();
        assertEquals(219, pic.w);
        pic.next();
        assertEquals(255, pic.w);
        pic.next();
        assertEquals(255, pic.w);
        pic.next();
        assertEquals(255, pic.w);
        pic.next();
        assertEquals(255, pic.w);
        pic.next();
        assertEquals(38, pic.w);
        pic.next();
        assertEquals(38, pic.w);
        pic.next();
        assertEquals(38, pic.w);
        pic.next();
        assertEquals(0, pic.w);
        pic.next();
        assertEquals(217, pic.w);
        pic.next();
        assertEquals(121, pic.w);
        pic.next();
        assertEquals(121, pic.w);
        pic.next();
        assertEquals(121, pic.w);

    }
}
