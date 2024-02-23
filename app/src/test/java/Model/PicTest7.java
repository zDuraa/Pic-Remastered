package Model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import utils.*;

public class PicTest7 {
    @Test
    public void Test7() {
        FileManager.ladeDatei("./../Testprogramme/TPicSim7.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        // loop
        pic.next();// movlw
        pic.next();// bsf
        pic.next();// movwf
        assertEquals(1, pic.ram.getReg(1));
        pic.next();// bcf
        pic.next();// movlw
        pic.next();// movwf
        pic.next();// clrf

        // loop1
        for (int i = 0; i < 128; i++) {
            pic.next();// NOP
            pic.next();// NOP
            pic.next();// NOP
            pic.next();// incf
            assertEquals(i + 1, pic.ram.getReg(16));

            pic.next();// movf
            if (pic.ram.getReg(1) == 0) {
                assertEquals(0b100, pic.ram.getReg(3) & 0b100);
            }

            pic.next();// btfss
            pic.next(); // goto loop1
        }
        // movlw (goto get skiped last clall)
        assertEquals(128, pic.ram.getReg(16)); // num muss 80H in 10h stehen
        assertEquals(15, pic.pCounter.get());

        pic.next(); // bsf
        pic.next(); // movwf
        assertEquals(0b011, pic.ram.getOpt() & 0b111);
        pic.next(); // bcf
        pic.next(); // movlw
        pic.next(); // movwf
        assertEquals(1, pic.ram.GetTmr0());
        pic.next(); // clrf
        assertEquals(0, pic.ram.getReg(16));

        // loop2
        // 308
        for (int i = 0; i < 817; i++) {
            pic.next();// incf
            assertEquals((i + 1) % 256, pic.ram.getReg(16));
            pic.next();// movf
            System.out.println(pic.ram.GetTmr0());
            if (pic.ram.GetTmr0() == 0) {
                assertEquals(0b100, pic.ram.getReg(3) & 0b100);
            }
            pic.next();// btfss
            pic.next();// goto loop2
        }
        // movlw if goto gets skiped
        assertEquals(49, pic.ram.getReg(16)); // num muss 31H in 10h stehen
        assertEquals(26, pic.pCounter.get());

        assertEquals(0b111000, pic.w);
        pic.next(); // bsf
        pic.next(); // movwf
        pic.next(); // bcf
        pic.next(); // clrf
        assertEquals(30, pic.pCounter.get());

        // loop3
        for (int i = 0; i < 10; i++) {
            pic.next();// btfss
        }
        pic.RA4();
        assertEquals(1, pic.ram.GetTmr0());

        for (int i = 0; i < 5; i++) {
            pic.next();
        }
        pic.RA4();
        assertEquals(2, pic.ram.GetTmr0());

        for (int i = 0; i < 17; i++) {
            pic.next();
        }
        pic.RA4();
        assertEquals(3, pic.ram.GetTmr0());

        for (int i = 0; i < 3; i++) {
            pic.next();
        }
        pic.RA4();
        assertEquals(4, pic.ram.GetTmr0());

        for (int i = 0; i < 12; i++) {
            pic.RA4();
        }
        assertEquals(16, pic.ram.GetTmr0());

        pic.next();
        pic.next();
        assertEquals(32, pic.pCounter.get());

        pic.next();// movlw
        pic.next();// bsf
        pic.next();// movwf
        pic.next();// bcf
        pic.next();// clrf

        // loop4
        for (int i = 0; i < 16; i++) {
            pic.next();// goto loop4
        }

        for (int i = 0; i < 31; i++) {
            pic.RA4();
        }

        for (int i = 0; i < 29; i++) {
            pic.next();
        }

        pic.RA4();

        pic.next();
        pic.next();

        assertEquals(8, pic.ram.GetTmr0());
        assertEquals(39, pic.pCounter.get());

    }
}
