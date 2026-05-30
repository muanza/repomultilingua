package ao.agt.pos.service;

import ao.agt.pos.model.Terminal;

public class TerminalService {

    public Terminal activate(String code) {
        Terminal terminal = new Terminal(code);
        terminal.setActive(true);
        return terminal;
    }

    public void deactivate(Terminal terminal) {
        terminal.setActive(false);
    }
}
