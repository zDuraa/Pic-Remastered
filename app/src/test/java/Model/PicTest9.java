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

        pic.watchdog.start();

        pic.next();// clrf var
        assertEquals(0, pic.ram.getReg(12));
        pic.next();// swapf status,w
        assertEquals(64, pic.w);
        pic.next();// movwf liste1 ;T0=1 , PD=1
        assertEquals(0, (pic.ram.getReg(3) & 0b00001000) >> 3);// T0
        assertEquals(0, (pic.ram.getReg(3) & 0b00010000) >> 4);// PD

        do {
            pic.next();// sleep ;warte bis Watchdogtimer anspricht
        } while (pic.pCounter.get() > 1);

        // pic.next();//swapf status,w
        // pic.next();//movwf liste2 ;T0=0 , PD=0 bei Watchdog-Timeout
        // ;T0=1 , PD=0 bei RESET oder Interrupt
        assertEquals(0, pic.pCounter.get());

    }

    // Test 11 ist grundlegend für das TPicSim9-Testprogramm.
    @Test
    public void test11() {
        FileManager.ladeDatei("./../Testprogramme/TPicSim11.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        // ;zuerst wird der VT dem Timer zugewiesen. Damit bekommt der Watchdog
        // ;die kleinste Zeit (ca. 18 ms) zugewiesen
        pic.next();// 3000 movlw 00000000B ;Option-Register entsp. initialisieren
        pic.next();// 1683 bsf status,5 ;Bank umschalten
        pic.next();// 0081 movwf 1 ;Option-Register
        pic.next();// 1283 bcf status,5
        pic.next();// 01A0 clrf 20h
        pic.next();// 01A1 clrf 21h
        pic.next();// 01A2 clrf 22h
        pic.next();// 01A3 clrf 23h
        assertEquals(0, pic.ram.getReg(32));
        assertEquals(0, pic.ram.getReg(33));
        assertEquals(0, pic.ram.getReg(34));
        assertEquals(0, pic.ram.getReg(35));

        pic.watchdog.start();
        // loop1
        do {
            pic.next();// 0FA0 incfsz 20h

        } while (pic.pCounter.get() > 1);

        assertEquals(0, pic.pCounter.get());

        /*
         * pic.next();// 2808 goto loop1 ;kein �berlauf
         * 
         * pic.next();// 0FA1 incfsz 21h ;n�chste Stelle
         * pic.next();// 2808 goto loop1 ;kein �berlauf
         * 
         * pic.next();// 0FA2 incfsz 22h ;ja
         * pic.next();// 2808 goto loop1
         * 
         * pic.next();// 0FA3 incfsz 23h
         * pic.next();// 2808 goto loop1
         */

        // ;******************************************************************
        // ;in 20h steht ca. 5fH
        // ;in 21H ca. 17H
        // ;Z�hlerstand 16-Bit ca. 175FH = 5983d

    }

}
