package ao.agt.pos.model;

public class Terminal {

    private final String code;
    private boolean active;

    public Terminal(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
