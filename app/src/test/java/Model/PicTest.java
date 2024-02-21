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
        pic.decoder.addCmd(new ADDLW(pic));
        pic.decoder.addCmd(new ANDLW(pic));
        pic.decoder.addCmd(new IORLW(pic));
        pic.decoder.addCmd(new SUBLW(pic));
        pic.decoder.addCmd(new XORLW(pic));
        pic.decoder.addCmd(new MOVLW(pic));

        for (int i = 0; i < 6; i++) {
            pic.next();
        }

        assertEquals(25, pic.w);
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
        // missing a lot
    }
}
