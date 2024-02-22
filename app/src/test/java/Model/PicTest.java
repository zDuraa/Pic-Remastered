package Model;

import Model.Commands.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import utils.*;

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

    @Test
    public void test4() {

        //FileManager.ladeDatei("/home/kai/code/Pic-Remastered/Testprogramme/TPicSim4.LST");
        FileManager.ladeDatei(".\\..\\Testprogramme\\TPicSim4.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        pic.next();
        assertEquals(17, pic.w);

        pic.next();
        assertEquals(17, pic.ram.getReg(12));

        pic.next();
        assertEquals(34, pic.w);

        pic.next();
        assertEquals(34, pic.ram.getReg(12));

        pic.next();
        assertEquals(68, pic.ram.getReg(12));

        pic.next();
        assertEquals(136, pic.ram.getReg(12));

        pic.next();
        assertEquals(16, pic.w);
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(17, pic.ram.getReg(12));
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(35, pic.w);
        assertEquals(0b000, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(8, pic.ram.getReg(12));
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(35, pic.ram.getReg(13));
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(145, pic.ram.getReg(13));
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(200, pic.w);
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(9, pic.w);
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(9, pic.ram.getReg(12));
        assertEquals(0b001, pic.ram.getReg(3) & 0b111);

        pic.next();
        assertEquals(0, pic.w);

        for (int i = 0; i < 9; i++) {
            pic.next();
            assertEquals(i + 1, pic.w);

            pic.next();
            // add w to f

            pic.next();
            assertEquals(9 - (i + 1), pic.ram.getReg(12));

            pic.next();
            // goto
        }
        assertEquals(0, pic.ram.getReg(12));
        assertEquals(240, pic.w);

        pic.next();
        assertEquals(240, pic.ram.getReg(12));

    }
    @Test
    public void test5() {
        FileManager.ladeDatei(".\\..\\Testprogramme\\TPicSim5.LST");
        ArrayList<String> commands = FileManager.getCommands();
        Pic pic = new Pic(commands);

        pic.next();//movlw
        assertEquals(17, pic.w);

        pic.next(); //movwf

        pic.next(); //clrf
        assertEquals(17, pic.w);
        assertEquals(17, pic.ram.getReg(12)); //wert1
        assertEquals(0, pic.ram.getReg(13)); //wert2
        assertEquals(1, (pic.ram.getReg(3) & 0b100) >> 2); //z
        pic.next(); //bsf
        assertEquals(17, pic.w);
        assertEquals(145, pic.ram.getReg(12));
        assertEquals(0, pic.ram.getReg(13));
        assertEquals(1, (pic.ram.getReg(3) & 0b100) >> 2);
        pic.next(); //bsf
        assertEquals(17, pic.w);
        assertEquals(153, pic.ram.getReg(12));
        assertEquals(0, pic.ram.getReg(13));
        assertEquals(1, (pic.ram.getReg(3) & 0b100) >> 2);
        pic.next(); //bcf
        assertEquals(17, pic.w);
        assertEquals(137, pic.ram.getReg(12));
        assertEquals(0, pic.ram.getReg(13));
        assertEquals(1, (pic.ram.getReg(3) & 0b100) >> 2);
        pic.next(); //bcf
        assertEquals(17, pic.w);
        assertEquals(136, pic.ram.getReg(12));
        assertEquals(0, pic.ram.getReg(13));
        assertEquals(1, (pic.ram.getReg(3) & 0b100) >> 2);
        pic.next(); //btfsc
        pic.next(); //incf
        pic.next(); //incf
        pic.next(); //btfsc
        pic.next(); //incf
        pic.next(); //incf
        pic.next(); //btfss
        pic.next(); //incf
        pic.next(); //incf
        pic.next(); //btfss
        pic.next(); //incf
        pic.next(); //decf in wert2 muss 04h stehen
        assertEquals(4, pic.ram.getReg(13)); //wert2
    }
}
