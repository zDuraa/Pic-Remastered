package Model;

import java.util.ArrayList;
import Model.Commands.*;

public class Decoder {
    ArrayList<Command> commands;

    public Decoder(Pic pic) {
        commands = new ArrayList<Command>();
        commands.add(new ADDWF(pic));
        commands.add(new ANDWF(pic));
        commands.add(new CLRF(pic));
        commands.add(new CLRF(pic));
        commands.add(new CLRW(pic));
        commands.add(new COMF(pic));
        commands.add(new DECF(pic));
        commands.add(new DECFSZ(pic));
        commands.add(new INCF(pic));
        commands.add(new INCFSZ(pic));
        commands.add(new IORWF(pic));
        commands.add(new MOVF(pic));
        commands.add(new MOVWF(pic));
        commands.add(new NOP(pic));
        commands.add(new RLF(pic));
        commands.add(new RRF(pic));
        commands.add(new SUBWF(pic));
        commands.add(new SWAPF(pic));
        commands.add(new XORWF(pic));

        commands.add(new BCF(pic));
        commands.add(new BSF(pic));
        commands.add(new BTFSC(pic));
        commands.add(new BTFSS(pic));

        commands.add(new ADDLW(pic));
        commands.add(new ANDLW(pic));
        commands.add(new CALL(pic));
        commands.add(new CLRWDT(pic));
        commands.add(new GOTO(pic));
        commands.add(new IORLW(pic));
        commands.add(new MOVLW(pic));
        commands.add(new RETFIE(pic));
        commands.add(new RETLW(pic));
        commands.add(new RETURN(pic));
        commands.add(new SLEEP(pic));
        commands.add(new SUBLW(pic));
        commands.add(new XORLW(pic));
    }

    public void decode(String instruction) {
        int iinst = Integer.parseInt(instruction, 16);

        for (var command : commands) {
            if (command.is(iinst)) {
                command.execute(iinst);
                break;
            }

        }
    }

    public void addCmd(Command cmd) {
        commands.add(cmd);
    }
}
