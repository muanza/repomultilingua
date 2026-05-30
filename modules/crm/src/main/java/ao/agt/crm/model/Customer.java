package ao.agt.crm.model;

import ao.agt.common.model.Entity;

public class Customer extends Entity {

    private final String code;
    private final String name;
    private final String email;

    public Customer(String code, String name, String email) {
        this.code = code;
        this.name = name;
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
