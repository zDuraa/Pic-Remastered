package Model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import utils.*;

public class PicTest9 {
    @Test
    public void test9() {
        FileManager.ladeDatei("./../Testprogramme/TPicSim9.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        pic.next();//clrf  var
        assertEquals(0, pic.ram.getReg(12));
        pic.next();//swapf status,w
        assertEquals(64, pic.w);
        pic.next();//movwf liste1              ;T0=1 , PD=1
        assertEquals(1, (pic.ram.getReg(3) & 0b00001000) >> 3);//T0
        assertEquals(1, (pic.ram.getReg(3) & 0b00010000) >> 4);//PD
        pic.next();//sleep                     ;warte bis Watchdogtimer anspricht
        pic.next();//swapf status,w
        pic.next();//movwf liste2              ;T0=0 , PD=0 bei Watchdog-Timeout
        //                                     ;T0=1 , PD=0 bei RESET oder Interrupt


    }

}
