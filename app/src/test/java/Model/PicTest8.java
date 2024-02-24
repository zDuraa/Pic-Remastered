package Model;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import utils.*;

public class PicTest8 {
    @Test
    public void Test8() {
        FileManager.ladeDatei("./../Testprogramme/TPicSim8.LST");
        ArrayList<String> commands = FileManager.getCommands();

        Pic pic = new Pic(commands);

        // start
        pic.next();// go to main

        // main
        pic.next();// movlw 00000001B ;Option-Register entsp. initialisieren
        pic.next();// bsf status,5 ;Bank umschalten
        pic.next();// movwf 1 ;Option-Register
        pic.next();// bcf status,5
        pic.next();// movlw 20h ;nur Timer-Interrupt erlauben
        pic.next();// movwf intcon
        pic.next();// bsf intcon,7
        assertEquals(1, pic.ram.getReg(11) >> 7); // GIE
        assertEquals(1, (pic.ram.getReg(11) & 0b000100000) >> 5);// T0IE
        assertEquals(3, pic.ram.getReg(1));// TMR0
        // loop1
        do {
            pic.next();// btfsc intcon,5 ;fertig, wenn ISR dieses Bit zur�cksetzt
                       // goto loop1 ;bis Timer �berl�uft
                       // btfss from SRI in last rotation
            // System.out.println(pic.ram.GetTmr0());
        } while (pic.ram.GetTmr0() != 0);

        assertEquals(0, pic.ram.getReg(1));// TMR0
        assertEquals(4, pic.pCounter.get());// PCounter = 4
        assertEquals(35, pic.stack.getStack()[0]);

        // ISR
        pic.next();// btfss
        pic.next();// movlw 54h ;ja, 54h = T in 20h schreiben
        pic.next();// movwf 20h
        assertEquals(84, pic.ram.getReg(32));
        pic.next();// bcf intcon,5 ;Timer-Interrupt sperren
        pic.next();// bcf intcon,2 ;Interrupt-Flag zur�cksetzen
        assertEquals(0b10000000, pic.ram.getReg(11));
        pic.next();// goto isrend
        assertEquals(27, pic.pCounter.get());

        pic.next();// isrend
        assertEquals(35, pic.pCounter.get());
        pic.next();// btfsc

        // ;zuerst die wirksame Flanke f�r RB0 festlegen (hier die fallende Flanke)
        pic.next();// movlw 00001111b
        assertEquals(0b1111, pic.w);
        pic.next();// bsf status,5
        pic.next();// movwf 1
        pic.next();// bcf status,5
        // ;nun den RB0-Interrupt freigeben, Rest sperren
        pic.next();// movlw 10h
        pic.next();// movwf intcon
        pic.next();// bsf intcon,7

        // ISR1
        pic.next();// btfss intcon,1 ;war es ein RB0-Interrupt?
        pic.next();// goto isr2 ;nein
        pic.next();// movlw 'I' ;schreibe ein I an 21h
        pic.next();// movwf 21h
        pic.next();// bcf intcon,4 ;RB0-Interrupt sperren
        pic.next();// bcf intcon,1 ;RB0-Interrupt-Flag l�schen
        pic.next();// goto isrend

        // ISR2
        pic.next();// btfss intcon,0 ;war es ein RB4-7 Interrupt?
        pic.next();// goto isr3 ;d�rfte nie passieren
        pic.next();// movlw 'R' ;schreibe ein R nach 22h
        pic.next();// movwf 22h
        pic.next();// bcf intcon,3 ;keine RB4-7 Interrupts erlauben
        pic.next();// bcf intcon,0 ;auch das Flag l�schen
        pic.next();// goto isrend

        // ISR3
        pic.next();// movlw 'F' ;Fehlermeldung
        pic.next();// movwf 23h

        // isrend
        pic.next();// retfie

        // loop2
        for (int i = 0; i < 1; i++) {
            pic.next();// btfsc intcon,4
            pic.next();// goto loop2
            // ;Beim RB4-7 Interrupts wird ein Flankenwechsel nur an den Pins die als
            // Eingang arbeiten erkannt
            pic.next();// movlw 08h ;RB4-7 Interrupt freigeben
            pic.next();// movwf intcon
            pic.next();// bsf intcon,7 ;GIE
        }

        // loop3
        for (int i = 0; i < 1; i++) {
            pic.next();// btfsc intcon,3 ;wird in ISR zur�ckgesetzt
            pic.next();// goto loop3
            //
            // ;das gleiche nochmals, nur sind RB4-6 Ausg�nge und werden deshalb ignoriert
            pic.next();// movlw 10001111B
            pic.next();// bsf status,5
            pic.next();// movwf 6 ;TRIS RB
            pic.next();// bcf status,5
            pic.next();// movlw 08h ;RB4-7 Interrupt freigeben
            pic.next();// movwf intcon ;aber nur RB7 ist aktiv
            pic.next();// bsf intcon,7 ;GIE
        }

        // loop4
        for (int i = 0; i < 1; i++) {
            // btfsc intcon,3 ;wird in ISR zur�ckgesetzt
            // goto loop4
        }
        // wenn alles richtig, dann
        // assertEquals(84, pic.ram.getReg(32)); // ;in 20h steht 54H
        // assertEquals(73, pic.ram.getReg(33)); // ;in 21H ein 49H
        // assertEquals(82, pic.ram.getReg(34)); // ;in 22H ein 52H

    }
}
