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
        pic.next();//movlw
        pic.next();//bsf
        pic.next();//movwf
        pic.next();//bcf
        pic.next();//movlw
        pic.next();//movwf
        pic.next();//clrf


        // loop1
        for (int i = 0; i < 128; i++) {
            pic.next();//NOP
            pic.next();//NOP
            pic.next();//NOP
            pic.next();//incf
            pic.next();//movf
            pic.next();//btfss


            pic.next(); // goto loop1
        }
        assertEquals(128, pic.ram.getReg(16)); //num muss 80H in 10h stehen

        pic.next(); //movlw
        pic.next(); //bsf
        pic.next(); //movwf
        pic.next(); //bcf
        pic.next(); //movlw
        pic.next(); //movwf
        pic.next(); //clrf

        // loop2
        //308
        for (int i = 0; i < 308; i++) {
            pic.next();//incf
            pic.next();//movf
            pic.next();//btfss
            pic.next();//goto loop2
        }
        assertEquals(49, pic.ram.getReg(16)); //num muss 31H in 10h stehen

        pic.next(); //movlw
        pic.next(); //bsf
        pic.next(); //movwf
        pic.next(); //bcf
        pic.next(); //clrf

        // loop3
        for (int i = 0; i < 16; i++) {
            pic.next();//btfss

            pic.next();//goto loop3
        }

        pic.next();//movlw
        pic.next();//bsf
        pic.next();//movwf
        pic.next();//bcf
        pic.next();//clrf

        // loop4
        for (int i = 0; i < 16; i++) {
            pic.next();//btfss

            pic.next();//goto loop4
        }

    }
}
