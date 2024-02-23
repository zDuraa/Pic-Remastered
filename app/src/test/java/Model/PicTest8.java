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

        //start
        pic.next();//go to main

        //ISR
        pic.next();//btfss intcon,2      ;war es ein Timer-Interrupt?
        pic.next();//goto isr1           ;nein
        pic.next();//movlw 54h           ;ja, 54h = T in 20h schreiben
        pic.next();//movwf 20h
        pic.next();//bcf intcon,5        ;Timer-Interrupt sperren
        pic.next();//bcf intcon,2        ;Interrupt-Flag zur�cksetzen
        pic.next();//goto isrend

        //ISR1
        //btfss intcon,1      ;war es ein RB0-Interrupt?
        //goto isr2           ;nein
        //movlw 'I'           ;schreibe ein I an 21h
        //movwf 21h
        //bcf intcon,4        ;RB0-Interrupt sperren
        //bcf intcon,1        ;RB0-Interrupt-Flag l�schen
        //goto isrend
    }
}

