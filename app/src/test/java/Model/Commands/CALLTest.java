package Model.Commands;

import Model.*;
import Model.Commands.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CALLTest {

    @Test
    public void defaultCall() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new CALL(pic);

        pic.pCounter.set(187);
        cmd.execute(12);

        assertEquals(188, pic.stack.pop());
        assertEquals(11, pic.pCounter.get());
    }

    @Test
    public void withPCLath() {
        Pic pic = new Pic(new ArrayList<>());
        Command cmd = new CALL(pic);

        pic.ram.setReg(10, 1);
        pic.pCounter.set(13);
        cmd.execute(0b11000);

        assertEquals(14, pic.stack.pop());
        assertEquals(1047, pic.pCounter.get());
    }
}
