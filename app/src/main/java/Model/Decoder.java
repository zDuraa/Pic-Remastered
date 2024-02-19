package Model;

import java.util.ArrayList;

public class Decoder {
    ArrayList<Command> commands;

    public Decoder() {
        commands = new ArrayList<Command>();
    }

    public void decode(String instruction, Pic pic) {
        int iinst = Integer.parseInt(instruction, 16);

        for (var command : commands) {
            if (command.is(iinst)) {
                command.execute(iinst);
            }
        }
    }

    public void addCmd(Command cmd) {
        commands.add(cmd);
    }
}
