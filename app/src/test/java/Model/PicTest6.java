package Model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import utils.*;

public class PicTest6 {
    @Test
    public void Test6() {
        FileManager.ladeDatei("./../Testprogramme/TPicSim6.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        // loop
        pic.next();
        assertEquals(32, pic.w);

        pic.next();
        assertEquals(32, pic.ram.getReg(12));

        pic.next();
        assertEquals(16, pic.w);

        pic.next();
        assertEquals(16, pic.ram.getReg(4));

        pic.next();
        assertEquals(16, pic.ram.getReg(13));

        pic.next();
        assertEquals(32, pic.w);

        // loop1
        for (int i = 0; i < 16; i++) {
            pic.next();
            assertEquals(32 + i, pic.ram.getReg(16 + i));

            pic.next();
            assertEquals(33 + i, pic.w);

            pic.next();
            assertEquals(17 + i, pic.ram.getReg(4));

            assertEquals(16 - i, pic.ram.getReg(13));
            pic.next();

            pic.next();
            // goto
        }
        assertEquals(12, pic.pCounter.get());
        assertEquals(31, pic.w);

        pic.next();
        assertEquals(31, pic.ram.getReg(4));

        pic.next();
        assertEquals(240, pic.w);

        pic.next();
        assertEquals(240, pic.ram.getReg(13));

        pic.next();
        assertEquals(0, pic.w);

        // loop2
        for (int i = 0; i < 16; i++) {
            pic.next();
            // addwf
            pic.next();
            // def fsr
            assertEquals(240 + i, pic.ram.getReg(13));
            pic.next();

            pic.next();
            // goto
        }
        assertEquals(120, pic.w);
        assertEquals(120, pic.ram.getReg(13));

        pic.next();
        assertEquals(16, pic.ram.getReg(4));

        pic.next();
        assertEquals(16, pic.ram.getReg(16));

        pic.next();
        assertEquals(17, pic.ram.getReg(16));

        pic.next();
        assertEquals(8, pic.ram.getReg(16));
        assertEquals(0b1, pic.ram.getReg(3) & 0b1);

        pic.next();
        assertEquals(136, pic.ram.getReg(16));

        pic.next();
        assertEquals(0b0, pic.ram.getReg(3) & 0b1);

        pic.next();
        assertEquals(16, pic.ram.getReg(16));
        assertEquals(0b1, pic.ram.getReg(3) & 0b1);

        pic.next();
        assertEquals(17, pic.ram.getReg(4));

        pic.next();
        assertEquals(67, pic.ram.getReg(17));
        assertEquals(0b0, pic.ram.getReg(3) & 0b1);

        pic.next();
        assertEquals(52, pic.ram.getReg(17));

        pic.next();
        assertEquals(76, pic.ram.getReg(17));
    }
}
