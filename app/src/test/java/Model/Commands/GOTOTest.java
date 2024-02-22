package Model.Commands;

import Model.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class GOTOTest {

    @Test
    public void defaultCall() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new GOTO(pic);

        pic.pCounter.set(187);
        cmd.execute(12);

        assertEquals(11, pic.pCounter.get());
    }

    @Test
    public void withPCLath() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new GOTO(pic);

        pic.ram.setReg(10, 0b11000);
        pic.pCounter.set(13);
        cmd.execute(0b11000);

        assertEquals(3095, pic.pCounter.get());
    }
}
